package cn.iocoder.springboot.lab57.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务演示示例
 *
 * @author Jaquez
 * @date 2021/11/15 11:01
 */
@SpringBootApplication
public class UserServiceApplication {

    @RestController
    @RequestMapping("/user")
    public class UserController {

        @GetMapping("/get")
        public String get(@RequestParam("id") Integer id) {
            return "User:" + id;
        }

        @GetMapping("/batch_get")
        public List<String> batchGet(@RequestParam("ids") List<Integer> ids) {
            return ids.stream().map(id -> "User:" + id).collect(Collectors.toList());
        }

    }

    public static void main(String[] args) {
        // 设置端口
        System.setProperty("server.port", "18080");

        // 应用启动
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
