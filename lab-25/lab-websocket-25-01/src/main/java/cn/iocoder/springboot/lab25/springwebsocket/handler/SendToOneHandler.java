package cn.iocoder.springboot.lab25.springwebsocket.handler;

import cn.iocoder.springboot.lab25.springwebsocket.message.SendResponse;
import cn.iocoder.springboot.lab25.springwebsocket.message.SendToOneRequest;
import cn.iocoder.springboot.lab25.springwebsocket.message.SendToUserRequest;
import cn.iocoder.springboot.lab25.springwebsocket.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * 私发消息
 * @author Jaquez
 * @date 2021/07/26 14:28
 */
@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {

    @Override
    public void execute(Session session, SendToOneRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtil.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }

}
