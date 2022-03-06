package cn.iocoder.springboot.lab58.retrofitdemo.retrofit;

import com.github.lianjiatech.retrofit.spring.boot.degrade.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Jaquez
 * @date 2022/03/04 10:47
 */
@Slf4j
@Component
public class UserServiceRetrofitClientCallbackFactory implements FallbackFactory<UserServiceRetrofitClient> {


    @Override
    public UserServiceRetrofitClient create(Throwable cause) {
        log.error("触发熔断了! ", cause.getMessage(), cause);
        return null;
    }
}
