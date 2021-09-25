package cn.iocoder.springboot.lab01.springsecurity.handler;

import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证入口类，是因为需要认证
 *
 * @author jaquez
 * @date 2021/09/25 19:19
 **/
@Component
public class AjaxAuthenticationEntryPointHanlder implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        CommonResponseBodyWrapper responseBody = new CommonResponseBodyWrapper();

        responseBody.setCode(String.valueOf(SysResponseCodeAndMsgEnum.NOT_AUTHENTICATION.getCode()));
        responseBody.setMsg(SysResponseCodeAndMsgEnum.NOT_AUTHENTICATION.getMsg());

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
