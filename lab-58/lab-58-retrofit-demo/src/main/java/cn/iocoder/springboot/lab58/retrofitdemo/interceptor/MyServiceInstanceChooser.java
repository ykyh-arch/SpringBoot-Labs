package cn.iocoder.springboot.lab58.retrofitdemo.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.core.ServiceInstanceChooser;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * 自定义服务实例拾取器
 *
 * @author jaquez
 * @date 2022/03/06 17:52
 **/
@Component
public class MyServiceInstanceChooser implements ServiceInstanceChooser {

    @Override
    public URI choose(String serviceId) {
        return null;
    }
}
