package cn.iocoder.springboot.lab85.demo.controller;

import cn.iocoder.springboot.lab85.demo.dataobject.CustomerInfoListVo;
import cn.iocoder.springboot.lab85.demo.response.Page;
import cn.iocoder.springboot.lab85.demo.response.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2022/08/31 17:18
 **/
@RestController
public class TestController {

    @GetMapping("test")
    public R<CustomerInfoListVo> test() {
        List list = new ArrayList<>();
        CustomerInfoListVo customerInfoListVo = new CustomerInfoListVo();
        customerInfoListVo.setId(10);
        customerInfoListVo.setCreateTime(new Date());
        customerInfoListVo.setRealName("张三丰");
        customerInfoListVo.setPhone("18225526606");
        customerInfoListVo.setIdCarNumber("342425198907117213");
        customerInfoListVo.setGender("男");
        customerInfoListVo.setBirthDate(new Date());
        customerInfoListVo.setCustomerCode("20113099019");
        customerInfoListVo.setEmail("2371114852@qq.com");
        customerInfoListVo.setCustomerManager("张三丰");
        customerInfoListVo.setWeChat("648526456");
        customerInfoListVo.setBlockingTime(new Date());
        customerInfoListVo.setBlockInstructions("");
        customerInfoListVo.setInterestingGrade("");
        list.add(customerInfoListVo);

        return new R<>().setCode(200).setMsg("查询成功").setObj(new Page<>().setSize(list.size()).setRecords(list));
    }
}
