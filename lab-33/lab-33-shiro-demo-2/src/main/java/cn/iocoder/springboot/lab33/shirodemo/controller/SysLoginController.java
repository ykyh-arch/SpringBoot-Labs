package cn.iocoder.springboot.lab33.shirodemo.controller;

import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import cn.iocoder.springboot.lab33.shirodemo.dto.SysLoginDto;
import cn.iocoder.springboot.lab33.shirodemo.service.SysCaptchaService;
import cn.iocoder.springboot.lab33.shirodemo.service.SysUserService;
import cn.iocoder.springboot.lab33.shirodemo.service.SysUserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author jaquez
 * @date 2021/10/05 14:43
 **/
@RestController
public class SysLoginController extends AbstractController{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 生成验证码，生成图片
     * @author Jaquez
     * @date 2021/10/05 19:04
     * @param response
     * @param uuid
     * @return void
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 用户登录
     * @param sysLoginDto
     * @return
     */
    @PostMapping("/sys/login")
    public Map<String, Object> login(SysLoginDto sysLoginDto) {
        // 验证图片验证码的正确性
        boolean captcha = sysCaptchaService.validate(sysLoginDto.getUuid(), sysLoginDto.getCaptcha());
        if (!captcha) {
            return ResultWrapper.error("验证码不正确");
        }

        // 获得指定用户名的 SysUser
        SysUser user = sysUserService.queryByUserName(sysLoginDto.getUsername());
        if (user == null || !user.getPassword().equals(new Sha256Hash(sysLoginDto.getPassword(), user.getSalt()).toHex())) { // 账号不存在、密码错误
            return ResultWrapper.error("账号或密码不正确");
        }
        if (user.getStatus() == 0) { // 账号锁定
            return ResultWrapper.error("账号已被锁定,请联系管理员");
        }

        // 生成 Token ，并返回结果
        return sysUserTokenService.createToken(user.getUserId());
    }

    /**
     * 用户登出
     * @author Jaquez
     * @date 2021/10/05 19:05
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @PostMapping("/sys/logout")
    public ResultWrapper logout() {
        sysUserTokenService.logout(getUserId());
        return ResultWrapper.ok();
    }

}
