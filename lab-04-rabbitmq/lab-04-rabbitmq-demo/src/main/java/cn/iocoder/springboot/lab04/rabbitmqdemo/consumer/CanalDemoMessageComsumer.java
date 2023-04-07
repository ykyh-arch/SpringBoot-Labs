package cn.iocoder.springboot.lab04.rabbitmqdemo.consumer;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import cn.iocoder.springboot.lab04.rabbitmqdemo.message.CanalDemoMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * CanalDemoMessageComsumer
 *
 * @author jaquez
 * @date 2023/04/07 14:35
 **/
@Component
@RabbitListener(queues = CanalDemoMessage.QUEUE)
public class CanalDemoMessageComsumer {

    @RabbitHandler(isDefault = true)
    public void onMessage(org.springframework.amqp.core.Message msg) {
        CanalDemoMessage message = JSONUtil.toBean(new String(msg.getBody()), CanalDemoMessage.class);
        System.out.println("收到 canal 消息：" + message);

        boolean isDdl = message.getDdl();

        // 不处理 DDL 事件
        if (isDdl) {
            return;
        }

        // TiCDC 的 id，应该具有唯一性，先保存再说
        int tid = message.getId();
        // TiCDC 生成该消息的时间戳，13位毫秒级
        long ts = message.getTs();
        // 数据库
        String database = message.getDatabase();
        // 表
        String table = message.getTable();
        // 类型：INSERT、UPDATE、DELETE
        String type = message.getType();
        // 每一列的数据值
        Collection data = message.getData();
        // 仅当 type 为 UPDATE 时才有值，记录每一列的名字和 UPDATE 之前的数据值
        Collection old = message.getOld();

        // 跳过 sys_backup，防止无限循环
        if ("sys_backup".equalsIgnoreCase(table)) {
            return;
        }

        // 只处理指定类型
        if (!"INSERT".equalsIgnoreCase(type)
                && !"UPDATE".equalsIgnoreCase(type)
                && !"DELETE".equalsIgnoreCase(type)) {
            return;
        }
        // 开始业务处理工作
        StaticLog.info(String.format("需要同步的数据库：%s、表：%s、操作类型为：%s、数据值：%s、原始值：%s",
                database,table,type,data,old));
    }
}
