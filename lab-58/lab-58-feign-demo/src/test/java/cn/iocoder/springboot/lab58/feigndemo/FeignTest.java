package cn.iocoder.springboot.lab58.feigndemo;

import cn.iocoder.springboot.lab58.feigndemo.entity.Contributor;
import cn.iocoder.springboot.lab58.feigndemo.feign.GitHub;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2021/11/08 15:06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignTest {

    // 测试 Feign 基本使用
    @Test
    public void test01(){
        GitHub github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }
    }
}
