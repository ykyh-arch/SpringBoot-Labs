package cn.iocoder.springboot.lab01.springsecurity.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * rbac 权限服务层
 *
 * @author jaquez
 * @date 2021/09/25 20:19
 **/
@Component("rbacauthorityservice")
public class RbacAuthorityService {
    /**
     * 判断是否拥有权限
     * @author Jaquez
     * @date 2021/09/25 20:20
     * @param request
     * @param authentication
     * @return boolean
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission  = false;
        if (userInfo instanceof UserDetails) {
            String username = ((UserDetails) userInfo).getUsername();
            // 获取资源，这些 url 都是要登录后才能访问，且其他的 url 都不能访问！
            Set<String> urls = new HashSet();
            urls.add("/api/common/**");
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
            return hasPermission;
        } else {
            return false;
        }
    }
}
