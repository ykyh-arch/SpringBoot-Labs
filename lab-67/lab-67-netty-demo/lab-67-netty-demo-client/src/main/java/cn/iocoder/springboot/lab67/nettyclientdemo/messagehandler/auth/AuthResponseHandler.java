package cn.iocoder.springboot.lab67.nettyclientdemo.messagehandler.auth;

import cn.iocoder.springboot.lab67.nettyclientdemo.message.auth.AuthResponse;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 认证响应处理器类，为客户端处理服务端的认证响应。
 *
 * @author Jaquez
 * @date 2022/02/07 16:11
 */
@Component
public class AuthResponseHandler implements MessageHandler<AuthResponse> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, AuthResponse message) {
        logger.info("[execute][认证结果：{}]", message);
    }

    @Override
    public String getType() {
        return AuthResponse.TYPE;
    }

}
