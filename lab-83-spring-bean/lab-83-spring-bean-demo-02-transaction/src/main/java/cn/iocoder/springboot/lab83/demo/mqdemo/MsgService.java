package cn.iocoder.springboot.lab83.demo.mqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * MsgService
 *
 * @author fw001
 * @date 2023/09/11 17:48
 **/
@Component
public class MsgService {

    // 添加一条消息（独立的事务中执行）
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long addMsg(String msg, Long msg_order_id, boolean isSend) {
        MsgModel msgModel = MsgModel.builder().msg(msg).msg_order_id(msg_order_id).status(0).build();
        // 先插入消息
        Long msg_id = this.insert(msgModel).getId();
        if (isSend) {
            // 如果需要投递，则调用投递的方法
            this.confirmSendMsg(msg_id);
        }
        return msg_id;
    }

    /**
     * 确认消息投递（不需要事务）
     *
     * @param msg_id 消息id
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void confirmSendMsg(Long msg_id) {
        MsgModel msgModel = this.getById(msg_id);
        // 向mq中投递消息
        System.out.println(String.format("投递消息：%s", msgModel));
        // 将消息状态置为已投递
        this.updateStatus(msg_id, 1);
    }

    /**
     * 取消消息投递（不需要事务）
     *
     * @param msg_id 消息id
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void cancelSendMsg(Long msg_id) {
        MsgModel msgModel = this.getById(msg_id);
        System.out.println(String.format("取消投递消息：%s", msgModel));
        // 将消息状态置为取消投递
        this.updateStatus(msg_id, 2);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入消息
     *
     * @param msgModel
     * @return
     */
    private MsgModel insert(MsgModel msgModel) {
        //插入消息
        this.jdbcTemplate.update("insert into t_msg (msg,msg_order_id,status) values (?,?,?)",
                msgModel.getMsg(),
                msgModel.getMsg_order_id(),
                msgModel.getStatus());
        //获取消息id
        msgModel.setId(this.jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
        System.out.println("插入消息：" + msgModel);
        return msgModel;
    }

    /**
     * 根据消息id获取消息
     *
     * @param id
     * @return
     */
    private MsgModel getById(Long id) {
        List<MsgModel> list = this.jdbcTemplate.query("select * from t_msg where id = ? limit 1", new BeanPropertyRowMapper<MsgModel>(MsgModel.class), id);
        return Objects.nonNull(list) && !list.isEmpty() ? list.get(0) : null;
    }

    /**
     * 更新消息状态
     *
     * @param id
     * @param status
     */
    private void updateStatus(long id, int status) {
        this.jdbcTemplate.update("update t_msg set status = ? where id = ?", status, id);
    }
}
