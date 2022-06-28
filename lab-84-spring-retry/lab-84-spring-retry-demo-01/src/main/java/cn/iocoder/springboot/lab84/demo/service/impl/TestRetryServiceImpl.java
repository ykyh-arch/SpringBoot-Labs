package cn.iocoder.springboot.lab84.demo.service.impl;

import cn.iocoder.springboot.lab84.demo.service.TestRetryService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.time.LocalTime;

/**
 * 测试重试服务类
 *
 * @author jaquez
 * @date 2022/04/25 19:15
 **/
@Service
public class TestRetryServiceImpl implements TestRetryService {

    @Override
    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public int test(int code) throws Exception {
        System.out.println("test被调用，时间："+ LocalTime.now());

        // Spring boot 内置工具类测试，参考：https://mp.weixin.qq.com/s/NjTVxIVrYCp33ebFjpsdbA

        // String param = null;
        // Assert.notNull(param,"param 没有赋值！");

        // System.out.println(ObjectUtils.getDisplayString(new Object()));

        // System.out.println(ObjectUtils.containsElement(new String[]{"hello","world"},"hello"));

        // System.out.println(ObjectUtils.addObjectToArray(new String[]{"hello","world"},"jaquez"));

        // System.out.println(ObjectUtils.toObjectArray(new String[]{"hello","world"}));

        // System.out.println(StringUtils.substringMatch("jaquez",2,"que"));

        // System.out.println(StringUtils.replace("j，a，quez","，",""));

        // System.out.println(StringUtils.stripFilenameExtension("c:\\img/3.png"));

        // System.out.println(CollectionUtils.containsInstance());

        // System.out.println(CollectionUtils.hasUniqueObject(Arrays.asList("hello","world","hello")));
        // System.out.println(CollectionUtils.findCommonElementType(Arrays.asList("hello","world","hello")));

        System.out.println(ReflectionUtils.findMethod(Object.class,"hashCode"));

        if (code==0){
            throw new Exception("情况不对头！");
        }
        System.out.println("test被调用,情况对头了！");
        return 200;
    }

    @Recover
    public int recover(Exception e, int code){
        System.out.println("回调方法执行！！！！");
        // 记日志到数据库或者调用其余的方法
        return 400;
    }



}
