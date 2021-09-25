package cn.iocoder.springboot.lab01.springsecurity.handler;

import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功处理类
 *
 * @author jaquez
 * @date 2021/09/25 19:57
 **/
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        CommonResponseBodyWrapper responseBody = new CommonResponseBodyWrapper();

        responseBody.setCode(String.valueOf(SysResponseCodeAndMsgEnum.LOGOUT_SUCCESS.getCode()));
        responseBody.setMsg(SysResponseCodeAndMsgEnum.LOGOUT_SUCCESS.getMsg());

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
