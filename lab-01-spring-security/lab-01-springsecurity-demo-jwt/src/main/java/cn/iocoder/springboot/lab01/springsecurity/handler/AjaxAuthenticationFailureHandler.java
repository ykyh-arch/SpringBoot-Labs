package cn.iocoder.springboot.lab01.springsecurity.handler;

import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器类
 *
 * @author jaquez
 * @date 2021/09/25 19:25
 **/
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        CommonResponseBodyWrapper responseBody = new CommonResponseBodyWrapper();

        responseBody.setCode(String.valueOf(SysResponseCodeAndMsgEnum.LOGIN_FAILURE.getCode()));
        responseBody.setMsg(SysResponseCodeAndMsgEnum.LOGIN_FAILURE.getMsg());

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
