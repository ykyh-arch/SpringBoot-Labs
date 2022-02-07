package cn.iocoder.springboot.lab67.nettyclientdemo.messagehandler.heartbeat;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandler;
import cn.iocoder.springboot.lab67.nettyclientdemo.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 心跳响应控制器类
 *
 * @author Jaquez
 * @date 2022/02/07 15:48
 */
@Component
public class HeartbeatResponseHandler implements MessageHandler<HeartbeatResponse> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, HeartbeatResponse message) {
        logger.info("[execute][收到连接({}) 的心跳响应]", channel.id());
    }

    @Override
    public String getType() {
        return HeartbeatResponse.TYPE;
    }

}
