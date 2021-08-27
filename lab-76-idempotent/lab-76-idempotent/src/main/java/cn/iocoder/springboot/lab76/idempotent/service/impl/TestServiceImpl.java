package cn.iocoder.springboot.lab76.idempotent.service.impl;

import cn.iocoder.springboot.lab76.idempotent.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * @author jaquez
 * @date 2021/08/27 16:24
 **/
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public String testIdempotence() {
        return "wo shi yitiao ceshi shuju";
    }
}
