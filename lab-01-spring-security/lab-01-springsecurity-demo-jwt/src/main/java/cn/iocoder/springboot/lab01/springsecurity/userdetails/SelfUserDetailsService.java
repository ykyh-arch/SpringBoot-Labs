package cn.iocoder.springboot.lab01.springsecurity.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户对象服务层
 *
 * @author jaquez
 * @date 2021/09/25 20:05
 **/
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 构建用户信息的逻辑(数据库/LDAP)

        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username);
        // 从数据库中取出密码
        userInfo.setPassword(new BCryptPasswordEncoder().encode("123456"));

        // 权限-角色信息
        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authoritiesSet.add(authority);
        userInfo.setAuthorities(authoritiesSet);

        return userInfo;
    }
}
