package cn.iocoder.springboot.lab01.springsecurity.userdetails;

import cn.iocoder.springboot.lab01.springsecurity.dataobject.UserDO;
import cn.iocoder.springboot.lab01.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 构建用户信息
        UserDO userDO = userMapper.selectByUsername(username);
        SelfUserDetails userInfo = new SelfUserDetails();
        userInfo.setUsername(username);
        // 密码加密的话，需要重写 AuthenticationProvider#authenticate（）方法
        userInfo.setPassword(new BCryptPasswordEncoder().encode(userDO.getPassword()));
        // 权限信息，用户拥有哪些角色，真实环境从数据库取，这里先注释掉不考虑用户的权限
        // Set authoritiesSet = new HashSet();
        // GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        // authoritiesSet.add(authority);
        // userInfo.setAuthorities(authoritiesSet);
        return userInfo;
    }
}
