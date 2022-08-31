package cn.iocoder.springboot.lab12.mybatis.controller;

import cn.iocoder.springboot.lab12.mybatis.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2022/05/31 16:48
 **/
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 数据脱敏测试，参考：https://mp.weixin.qq.com/s/_321dDDb_q-7EgkJ2lRIfQ，http://t.zoukankan.com/kuangdaoyizhimei-p-15472352.html（使用jackson解决部分序列化字段加密的功能），https://lizhou.blog.csdn.net/article/details/125367694
     * \基于 Aop 数据脱敏参考：https://blog.csdn.net/Ming13416908424/article/details/125441637
     *
     * @return
     */
    @GetMapping("/test")
    public String test() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userMapper.selectById(50004));
    }

}
