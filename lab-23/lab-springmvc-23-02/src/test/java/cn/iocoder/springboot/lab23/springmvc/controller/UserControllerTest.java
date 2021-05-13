package cn.iocoder.springboot.lab23.springmvc.controller;

import cn.iocoder.springboot.lab23.springmvc.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
/**
 * 集成测试
 * @author Jaquez
 * @date 2021/05/13 14:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest{

    @Autowired
    private MockMvc mvc;

}