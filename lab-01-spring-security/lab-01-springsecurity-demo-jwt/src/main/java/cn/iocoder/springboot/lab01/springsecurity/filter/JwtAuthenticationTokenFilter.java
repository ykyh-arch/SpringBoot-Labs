package cn.iocoder.springboot.lab01.springsecurity.filter;

import cn.iocoder.springboot.lab01.springsecurity.userdetails.SelfUserDetailsService;
import cn.iocoder.springboot.lab01.springsecurity.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token 认证过滤器
 *
 * @author jaquez
 * @date 2021/09/25 20:03
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    // 从数据库拿到的用户信息
    @Autowired
    SelfUserDetailsService userDetailsService;

    @Value("${token.salt}")
    private String salt;

    /**
     * 从请求头中的 token 解析得的用户账户，回表查询用户信息存储到 spring secruity 环境里
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // token 是以 Bearer 认证
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String authToken = authHeader.substring("Bearer ".length());
            String username = JwtTokenUtil.parseToken(authToken, salt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
