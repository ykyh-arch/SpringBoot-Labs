package cn.iocoder.springboot.lab04.rabbitmqdemo.canal;

import cn.hutool.log.StaticLog;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * CanalClient，需要将 canal.properties 中的 canal.serverMode = tcp 踩坑！！！
 * 注意：客户端的版本需要保持与服务端版本一致！！！
 * 数据同步神器 Canal 参考：https://blog.csdn.net/wxd772113786/article/details/119967306
 * 另一种简单客户端实现方式，可参考：{@link SimpleCanalClientPermanceTest}
 *
 * @author jaquez
 * @date 2023/04/06 14:43
 **/
@Component
public class CanalClient {

    private final static int BATCH_SIZE = 1000;
    private final static String HOSTNAME = "localhost";
    private final static int PORT = 11111;
    private final static String DESTINATION = "example";
    private final static String DBUSERNAME = "canal";
    private final static String DBPASSWORD = "canal";

    public void start() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(HOSTNAME, PORT), DESTINATION, DBUSERNAME, DBPASSWORD);
        try {
            // 打开连接
            connector.connect();
            // 订阅数据库表，全部表
            connector.subscribe(".*\\..*");
            // 订阅数据库指定库.表
            // connector.subscribe("ruoyi-vue-pro\\.system_menu");
            // 回滚到未进行 ack 的地方，下次 fetch 的时候，可以从最后一个没有 ack 的地方开始拿
            connector.rollback();
            // 自旋监听中
            while (true) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(BATCH_SIZE);
                // 获取批量 ID
                long batchId = message.getId();
                // 获取批量的数量
                int size = message.getEntries().size();
                // 如果没有数据
                if (batchId == -1 || size == 0) {
                    try {
                        // 线程休眠 1 秒
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 如果有数据,处理数据，踩坑：如果获取不到消息，检查服务端日志查看是否配置出现问题
                    printEntry(message.getEntries());
                }
                // 进行 batch id 的确认。确认之后，小于等于此 batchId 的 Message 都会被确认。
                connector.ack(batchId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 断开链接
            connector.disconnect();
        }
    }

    /**
     * 打印 canal server 解析 binlog 获得的实体类信息，控制台显示
     */
    public static void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                // 开启 OR 关闭事务的实体类型，跳过
                continue;
            }
            // RowChange 对象，包含了一行数据变化的所有特征
            // 比如 isDdl 是否是 ddl变更操作 sql 具体的 ddl sql beforeColumns afterColumns 变更前后的数据字段等等
            CanalEntry.RowChange rowChage;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR # parser of eromanga-event has an error , data:" + entry.toString(), e);
            }
            // 获取操作类型：insert、update、delete 类型
            CanalEntry.EventType eventType = rowChage.getEventType();
            // 打印 Header 信息
            StaticLog.info(String.format("================》; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            // 判断是否是 DDL 语句
            if (rowChage.getIsDdl()) {
                System.out.println("================》;isDdl: true,sql:" + rowChage.getSql());
            }
            // 获取 RowChange 对象里的每一行数据，打印出来
            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                // 如果是删除语句
                if (eventType == CanalEntry.EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                    // 如果是新增语句
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                    // 如果是更新的语句
                } else {
                    // 变更前的数据
                    StaticLog.info("------->; before");
                    printColumn(rowData.getBeforeColumnsList());
                    //变更后的数据
                    StaticLog.info("------->; after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            StaticLog.info(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated());
        }
    }
}
