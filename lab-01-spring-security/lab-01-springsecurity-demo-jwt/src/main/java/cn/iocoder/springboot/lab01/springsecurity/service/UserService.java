package cn.iocoder.springboot.lab01.springsecurity.service;

import cn.hutool.crypto.SecureUtil;
import cn.iocoder.springboot.lab01.springsecurity.exception.BusinessException;
import cn.iocoder.springboot.lab01.springsecurity.userdetails.SelfUserDetails;
import cn.iocoder.springboot.lab01.springsecurity.userdetails.SelfUserDetailsService;
import cn.iocoder.springboot.lab01.springsecurity.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户服务层
 *
 * @author jaquez
 * @date 2021/09/26 14:21
 **/
@Service
public class UserService {
    @Autowired
    private SelfUserDetailsService selfUserDetailsService;
    @Value("${token.expiration-seconds}")
    private Integer expirationSeconds;

    @Value("${token.salt}")
    private String salt;

    @Value("${md5.salt}")
    private String md5Salt;

    public String login(String username, String password,
                        HttpServletRequest request, HttpServletResponse response) {

        UserDetails userDetails = selfUserDetailsService.loadUserByUsername(username);
        if(StringUtils.isEmpty(userDetails)){
            throw new BusinessException("user not exist");
        }

        if(!BCrypt.checkpw(SecureUtil.md5(password + md5Salt),userDetails.getPassword())){
            throw new BusinessException("password is error");
        }
        if (username != null) {
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        userDetails = (SelfUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 过期时间 5 分钟
        String token = JwtTokenUtil.generateToken(userDetails.getUsername(), expirationSeconds, salt);

        return token;
    }
}
