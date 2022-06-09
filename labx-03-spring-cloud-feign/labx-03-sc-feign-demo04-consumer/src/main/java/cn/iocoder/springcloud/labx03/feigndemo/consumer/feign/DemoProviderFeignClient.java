package cn.iocoder.springcloud.labx03.feigndemo.consumer.feign;

import cn.iocoder.springcloud.labx03.feigndemo.consumer.dto.DemoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件上传参考：https://www.iocoder.cn/Fight/The-Spring-Cloud-Feign-interface-uploads-files/?self
 * 表单提交参考：https://www.iocoder.cn/Fight/Spring-Cloud-Feign-Post-form-request/?self
 *
 * @author Jaquez
 * @date 2022/06/09 14:45
 */
@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

    @GetMapping("/get_demo") // GET 方式一，最推荐
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    @GetMapping("/get_demo") // GET 方式二，相对推荐
    DemoDTO getDemo(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("/get_demo") // GET 方式三，不推荐
    DemoDTO getDemo(@RequestParam Map<String, Object> params);

    @PostMapping("/post_demo") // POST 方式
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);

    @PostMapping(value = "/upload_demo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadDemo(@RequestPart(value = "file") MultipartFile file);

}
