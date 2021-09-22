package cn.iocoder.springboot.lab34.actuatordemo.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "demo") // 申明了一个自定义端点
public class DemoEndPoint {

    @ReadOperation // 读操作
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<>();
        result.put("作者", "yudaoyuanma");
        result.put("秃头", "true");
        return result;
    }

}
