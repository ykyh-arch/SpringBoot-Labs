package cn.iocoder.springboot.lab01.springsecurity.handler;

import cn.iocoder.springboot.lab01.springsecurity.utils.CommonResponseBodyWrapper;
import cn.iocoder.springboot.lab01.springsecurity.utils.SysResponseCodeAndMsgEnum;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问拒绝处理器，拒绝是因为没有授权
 * @author jaquez
 * @date 2021/09/25 18:58
 **/
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        CommonResponseBodyWrapper responseBody = new CommonResponseBodyWrapper();

        responseBody.setCode(String.valueOf(SysResponseCodeAndMsgEnum.NOT_AUTHORITIES.getCode()));
        responseBody.setMsg(SysResponseCodeAndMsgEnum.NOT_AUTHORITIES.getMsg());

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
