package cn.iocoder.springboot.lab83.demo.mqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * MsgOrderService
 *
 * @author fw001
 * @date 2023/09/11 17:19
 **/
@Component
public class MsgOrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入消息订单
     *
     * @param ref_type
     * @param ref_id
     * @return
     */
    @Transactional
    public MsgOrderModel insert(Integer ref_type, String ref_id) {
        MsgOrderModel msgOrderModel = MsgOrderModel.builder().ref_type(ref_type).ref_id(ref_id).build();
        // 插入消息
        this.jdbcTemplate.update("insert into t_msg_order (ref_type,ref_id) values (?,?)",
                ref_type,
                ref_id
        );
        // 获取消息订单id
        msgOrderModel.setId(this.jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class));
        return msgOrderModel;
    }

    /**
     * 根据消息id获取消息
     *
     * @param id
     * @return
     */
    public MsgOrderModel getById(Long id) {
        List<MsgOrderModel> list = this.jdbcTemplate.query("select * from t_msg_order where id = ? limit 1", new BeanPropertyRowMapper<MsgOrderModel>(MsgOrderModel.class), id);
        return Objects.nonNull(list) && !list.isEmpty() ? list.get(0) : null;
    }

}
