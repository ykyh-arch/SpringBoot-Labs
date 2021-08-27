package cn.iocoder.springboot.lab76.idempotent.controller;

import cn.iocoder.springboot.lab76.idempotent.annotation.AutoIdempotent;
import cn.iocoder.springboot.lab76.idempotent.service.ITestService;
import cn.iocoder.springboot.lab76.idempotent.service.ITokenService;
import cn.iocoder.springboot.lab76.idempotent.utils.Constant;
import cn.iocoder.springboot.lab76.idempotent.utils.ResultVo;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试控制器
 *
 * @author jaquez
 * @date 2021/08/27 16:15
 **/
@RestController
public class TestController {

    @Resource
    private ITokenService tokenService;

    @Resource
    private ITestService testService;

    @PostMapping("/get/token")
    public String  getToken(){
        String token = tokenService.createToken();
        if (!StringUtils.isEmpty(token)) {
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(Constant.CODE_SUCCESS);
            resultVo.setMessage(Constant.SUCCESS);
            resultVo.setData(token);
            return JSON.toJSONString(resultVo);
        }
        return "{\n" +
                "    \"code\": 0,\n" +
                "    \"message\": \"服务器异常，token 信息获取不到\"\n" +
                "}";
    }

    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() {
        String businessResult = testService.testIdempotence();
        if (!StringUtils.isEmpty(businessResult)) {
            ResultVo successResult = ResultVo.getSuccessResult(businessResult);
            return JSON.toJSONString(successResult);
        }
        return "{\n" +
                "    \"code\": 0,\n" +
                "    \"message\": \"重复操作\"\n" +
                "}";
    }

}
