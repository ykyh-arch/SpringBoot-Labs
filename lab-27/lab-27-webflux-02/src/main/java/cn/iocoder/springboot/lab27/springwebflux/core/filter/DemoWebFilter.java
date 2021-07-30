package cn.iocoder.springboot.lab27.springwebflux.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * WebFilter 相当于spring 中的MVC
 * @author Jaquez
 * @date 2021/07/30 15:50
 */
@Component
@Order(1) // 类加载顺序，值越小拥有越高的优先级，可为负数。
public class DemoWebFilter implements WebFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        // 继续执行请求
        return webFilterChain.filter(serverWebExchange)
                // 异步 + 事件回调
                .doOnSuccess(new Consumer<Void>() { // 执行成功后回调

                    @Override
                    public void accept(Void aVoid) {
                        logger.info("[accept][执行成功]");
                    }

                });
    }

}
