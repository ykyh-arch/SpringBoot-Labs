package cn.iocoder.springcloudalibaba.labx7.consumerdemo.feign;

import cn.iocoder.springcloudalibaba.labx7.consumerdemo.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.consumerdemo.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用 Feign + Ribbon 调用服务
 * @author Jaquez
 * @date 2021/09/14 13:59
 */
@FeignClient(name = "demo-provider")
public interface UserFeignClient02 {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    @GetMapping("/user/get")
    UserDTO get(@RequestParam("id") Integer id);

    /**
     * 添加新用户，返回新添加的用户编号
     *
     * @param addDTO 添加的用户信息
     * @return 用户编号
     */
    @PostMapping("/user/add")
    Integer add(@RequestBody UserAddDTO addDTO);

}
