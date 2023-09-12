package cn.iocoder.springboot.lab83.demo.mqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService1
 *
 * @author fw001
 * @date 2023/09/12 11:06
 **/
@Component
public class UserService1 {

    @Autowired
    private UserService userService;

    @Autowired
    private MsgSender msgSender;

    // 嵌套事务案例，@1和@3事务消息会取消投递，@2事务消息会投递成功
    @Transactional
    public void nested() {
        this.msgSender.send("消息1", 2, "1"); // @1
        // registerRequiresNew事务传播属性是REQUIRES_NEW:会在一个新事务中运行
        this.userService.registerRequiresNew(1L, "张三"); // @2
        // registerFail事务传播属性是默认的，会在当前事务中运行，registerFail弹出异常会导致当前事务回滚
        this.userService.registerFail(2L, "李四"); // @3
    }

}
