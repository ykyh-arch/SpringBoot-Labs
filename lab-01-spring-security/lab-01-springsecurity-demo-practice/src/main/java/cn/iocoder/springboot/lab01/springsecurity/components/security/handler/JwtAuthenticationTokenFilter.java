package cn.iocoder.springboot.lab01.springsecurity.components.security.handler;

import cn.iocoder.springboot.lab01.springsecurity.components.security.entity.LoginUser;
import cn.iocoder.springboot.lab01.springsecurity.components.security.service.TokenService;
import cn.iocoder.springboot.lab01.springsecurity.utils.SecurityUtils;
import cn.iocoder.springboot.lab01.springsecurity.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token 过滤器 验证 token 有效性
 *
 * @author Jaquez
 * @date 2021/10/10 19:26
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 获得当前 LoginUser （从缓存中取得）确认身份
        LoginUser loginUser = tokenService.getLoginUser(request);
        // 如果存在 LoginUser ，并且未认证过
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
            // 校验 Token 有效性，验证令牌有效期，刷新缓存。
            tokenService.verifyToken(loginUser);
            // 创建 UsernamePasswordAuthenticationToken 对象，设置到 SecurityContextHolder 中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // 继续过滤器
        chain.doFilter(request, response);
    }

}
