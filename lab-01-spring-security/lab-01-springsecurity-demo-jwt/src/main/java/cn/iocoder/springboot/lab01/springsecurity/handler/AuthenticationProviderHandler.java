package cn.iocoder.springboot.lab01.springsecurity.handler;

import cn.hutool.crypto.SecureUtil;
import cn.iocoder.springboot.lab01.springsecurity.userdetails.SelfUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 自定义认证处理器
 *
 * @author jaquez
 * @date 2021/09/26 16:44
 **/

@Component
public class AuthenticationProviderHandler implements AuthenticationProvider {
    @Autowired
    private SelfUserDetailsService selfUserDetailsService;
    @Value("${md5.salt}")
    private String md5Salt;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String presentedPassword = (String)authentication.getCredentials();
        UserDetails userDetails = selfUserDetailsService.loadUserByUsername(username);
        // 根据用户名获取用户信息
        if (StringUtils.isEmpty(userDetails)) {
            throw new BadCredentialsException("user not exist");
        } else {
            // 自定义的加密规则
            if(!BCrypt.checkpw(SecureUtil.md5(presentedPassword + md5Salt),userDetails.getPassword())){
                throw new BadCredentialsException("password is error");
            }else {
                UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
                result.setDetails(authentication.getDetails());
                return result;
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
