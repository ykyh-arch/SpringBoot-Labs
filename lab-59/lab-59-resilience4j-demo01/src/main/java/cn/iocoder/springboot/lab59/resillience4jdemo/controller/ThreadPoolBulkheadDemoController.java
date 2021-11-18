package cn.iocoder.springboot.lab59.resillience4jdemo.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 基于线程池（ ThreadPool）的流控隔离
 *
 * @author Jaquez
 * @date 2021/11/18 14:10
 */
@RestController
@RequestMapping("/thread-pool-bulkhead-demo")
public class ThreadPoolBulkheadDemoController {

    @Autowired
    private ThreadPoolBulkheadService threadPoolBulkheadService; // 为了使 Resilience4j 生效

    @GetMapping("/get_user")
    public String getUser(@RequestParam("id") Integer id) throws ExecutionException, InterruptedException {
        threadPoolBulkheadService.getUser0(id);
        return threadPoolBulkheadService.getUser0(id).get();
    }

    @Service
    public static class ThreadPoolBulkheadService {

        private Logger logger = LoggerFactory.getLogger(ThreadPoolBulkheadService.class);

        @Bulkhead(name = "backendD", fallbackMethod = "getUserFallback", type = Bulkhead.Type.THREADPOOL)
        public CompletableFuture<String> getUser0(Integer id) throws InterruptedException {
            logger.info("[getUser][id({})]", id);
            Thread.sleep(10 * 1000L); // sleep 10 秒
            return CompletableFuture.completedFuture("User:" + id);
        }

        public CompletableFuture<String> getUserFallback(Integer id, Throwable throwable) {
            logger.info("[getUserFallback][id({}) exception({})]", id, throwable.getClass().getSimpleName());
            return CompletableFuture.completedFuture("mock:User:" + id);
        }

    }


}
