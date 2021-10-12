package cn.iocoder.springboot.lab01.springsecurity.components.security.service;

import cn.iocoder.springboot.lab01.springsecurity.common.Constants;
import cn.iocoder.springboot.lab01.springsecurity.components.cache.RedisCache;
import cn.iocoder.springboot.lab01.springsecurity.components.security.entity.LoginUser;
import cn.iocoder.springboot.lab01.springsecurity.exception.CustomException;
import cn.iocoder.springboot.lab01.springsecurity.exception.module.user.CaptchaException;
import cn.iocoder.springboot.lab01.springsecurity.exception.module.user.CaptchaExpireException;
import cn.iocoder.springboot.lab01.springsecurity.exception.module.user.UserPasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author Jaquez
 * @date 2021/10/09 15:57
 */
@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        // 验证图片验证码的正确性
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid; // uuid 的作用，是获得对应的图片验证码
        String captcha = redisCache.getCacheObject(verifyKey); // 从 Redis 中，获得图片验证码
        redisCache.deleteObject(verifyKey); // 从 Redis 中，删除图片验证码
        if (captcha == null) { // 图片验证码不存在
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) { // 图片验证码不正确
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication;
        try {
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername（）方法
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            // 发生异常，说明验证不通过，可以记录相应的登陆失败日志
            System.out.println(String.format("登陆失败，产生异常，异常信息：%s，异常堆栈信息：%s",
                    e.getMessage(), e));
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        // 登录成功，生成 Token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }

}
