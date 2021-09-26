package cn.iocoder.springboot.lab01.springsecurity.handler;

import cn.iocoder.springboot.lab01.springsecurity.userdetails.SelfUserDetails;
import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import cn.iocoder.springboot.lab01.springsecurity.utils.JwtTokenUtil;
import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理类
 *
 * @author jaquez
 * @date 2021/09/25 19:30
 **/
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${token.expiration-seconds}")
    private Integer expirationSeconds;

    @Value("${token.salt}")
    private String salt;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        CommonResponseBodyWrapper responseBody = new CommonResponseBodyWrapper();

        responseBody.setCode(String.valueOf(SysResponseCodeAndMsgEnum.LOGIN_SUCCESS.getCode()));
        responseBody.setMsg(SysResponseCodeAndMsgEnum.LOGIN_SUCCESS.getMsg());
        // 登录成功处理，生成 token 信息，在 JwtAuthenticationTokenFilter#doFilterInternal() 存储了用户信息
        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();
        // 过期时间 5 分钟
        String token = JwtTokenUtil.generateToken(userDetails.getUsername(), expirationSeconds, salt);
        responseBody.setToken(token);

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}