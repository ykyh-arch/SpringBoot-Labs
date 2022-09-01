package cn.iocoder.springboot.lab85.demo.controller;

import cn.iocoder.springboot.lab85.demo.dataobject.CustomerExpandInfoVo;
import cn.iocoder.springboot.lab85.demo.dataobject.CustomerInfoVo;
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
    public R<CustomerInfoVo> test() {
        List list = new ArrayList<>();
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        CustomerExpandInfoVo customerExpandInfoVo = new CustomerExpandInfoVo();
        customerInfoVo.setId(10);
        customerInfoVo.setCreateTime(new Date());
        customerInfoVo.setRealName("张三丰");
        customerInfoVo.setPhone("18225526606");
        customerInfoVo.setIdCarNumber("342425198907117213");
        customerExpandInfoVo.setCustomerInfoId(10);
        customerExpandInfoVo.setGender("男");
        customerExpandInfoVo.setBirthDate(new Date());
        customerExpandInfoVo.setCustomerCode("20113099019");
        customerExpandInfoVo.setEmail("2371114852@qq.com");
        customerExpandInfoVo.setCustomerManager("张三丰");
        customerExpandInfoVo.setWeChat("648526456");
        customerExpandInfoVo.setBlockingTime(new Date());
        customerExpandInfoVo.setBlockInstructions("");
        customerExpandInfoVo.setInterestingGrade("");
        customerExpandInfoVo.setCustomerAddr("武当山武当山武当山");
        customerInfoVo.setCustomerExpandInfoVo(customerExpandInfoVo);
        list.add(customerInfoVo);

        return new R<>().setCode(200).setMsg("查询成功").setObj(new Page<>().setSize(list.size()).setRecords(list));
    }
}
