
package cn.iocoder.springboot.lab33.shirodemo.oauth2;

import cn.iocoder.springboot.lab33.shirodemo.utils.HttpContextUtils;
import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OAuth2Filter 过滤器，实现了基于 Token 的认证，从请求头中获取 token 执行登录
 * @author Jaquez
 * @date 2021/10/05 13:57
 */
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        // 获取请求中的 token
        String token = getRequestToken((HttpServletRequest) request);
        // 如果不存在，则返回 null
        if (StringUtils.isBlank(token)) {
            return null;
        }
        // 创建 OAuth2Token 对象
        return new OAuth2Token(token);
    }

    /**
     * 预检请求允许访问，其他拒绝，执行 onAccessDenied（）方法认证
     * @author Jaquez
     * @date 2021/10/05 14:04
     * @param request
     * @param response
     * @param mappedValue
     * @return boolean
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    /**
     * 拒绝访问，未认证，缺少 token，携带 token 需要认证
     * @author Jaquez
     * @date 2021/10/06 16:02
     * @param request
     * @param response
     * @return boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求中的 token 。如果 token 不存在，直接返回 401 ，认证不通过
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            // 设置响应 Header
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            // httpResponse.setHeader("Content-Type","application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            // 返回认证不通过
            String json = new Gson().toJson(ResultWrapper.error(HttpStatus.SC_UNAUTHORIZED, "未认证，缺少 token"));
            httpResponse.getWriter().print(json);
            // 返回 false
            return false;
        }

        // 执行登陆逻辑，实际执行的是基于 Token 进行认证。底层执行的是 OAuth2Realm#doGetAuthenticationInfo(AuthenticationToken token)
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        // 设置响应 Header
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            // 处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            ResultWrapper r = ResultWrapper.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            // 返回认证不通过
            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);
        } catch (IOException ignored) {
        }
        // 返回 false
        return false;
    }

    /**
     * 从请求头中获取 token，优先从 header 中获取 token，次之，如果 header 中不存在 token ，则从参数中获取 token
     * @author Jaquez
     * @date 2021/10/05 13:58
     * @param httpRequest
     * @return java.lang.String
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }

}
