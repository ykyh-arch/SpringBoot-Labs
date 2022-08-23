package cn.iocoder.springboot.lab04.rabbitmqdemo.controller;

import cn.iocoder.springboot.lab04.rabbitmqdemo.producer.MqttGateway;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * MqttController
 *
 * @author jaquez
 * @date 2022/08/23 10:55
 **/
@Controller
@RequestMapping("/fun")
public class MqttController {

    // @Autowired
    @Resource
    private MqttGateway mqttGateway;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMqtt(@RequestParam(value = "topic") String topic, @RequestParam(value = "message") String message) {
        mqttGateway.sendMessage2Mqtt(message, topic);
        return "SUCCESS";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
