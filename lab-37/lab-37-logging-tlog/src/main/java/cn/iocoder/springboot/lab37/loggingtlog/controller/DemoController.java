package cn.iocoder.springboot.lab37.loggingtlog.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.iocoder.springboot.lab37.loggingtlog.producer.DemoProducer;
import com.yomahub.tlog.core.annotation.TLogAspect;
import com.yomahub.tlog.hutoolhttp.TLogHutoolhttpInterceptor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private DemoProducer producer;

    @Resource
    private TLogHutoolhttpInterceptor tLogHutoolhttpInterceptor;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/info")
    public void info() {
        logger.info("手动打印输出日志信息");
    }

    @TLogAspect(value = {"param1","param2"},joint = "_",pattern = "<{}>")
    @GetMapping("/tag")
    public void testTag(String param1,String param2) throws InterruptedException {
        logger.info("手动添加业务标签，可以记录业务参数");
        // 一般线程
        new Thread(()->{
            logger.info("手动添加线程测试一下效果");
        }).start();
        // 线程池
        threadPoolExecutor.execute(()->{
            logger.info("手动添加线程池测试一下效果");
        });
        // 同步发送消息
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id);
        logger.info("手动测试一下同步发送消息，编号：{} 发送成功", id);
        // TimeUnit.SECONDS.sleep(2);
        // 异步发送消息
        producer.asyncSend(id).addCallback(
                (aVoid) ->{logger.info("发送编号：{} 发送成功]", id);},
                (e) ->{logger.info("发送编号：{} 发送异常，异常信息：{}", id,
                        ExceptionUtils.getRootCauseMessage(e));});
        logger.info("手动测试一下异步发送消息，编号：{} 发送成功", id);
        // 测试远程调用 hutool-http
        String result = HttpRequest.get("https://www.baidu.com/")
                .header(Header.ACCEPT_LANGUAGE, "zh-CN,en-US;") // 头信息，多个头信息多次调用此方法即可
                .addInterceptor(tLogHutoolhttpInterceptor)
                // .form(null) //表单内容
                .timeout(5000) //超时，毫秒
                .execute().body();
        // logger.info("手动测试 hutool-http 远程调用，调用结果：{} ", result);

    }

}
