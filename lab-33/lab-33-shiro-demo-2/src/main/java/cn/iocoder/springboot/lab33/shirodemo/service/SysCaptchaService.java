
package cn.iocoder.springboot.lab33.shirodemo.service;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysCaptcha;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.image.BufferedImage;

/**
 * SysCaptchaService 服务层
 * @author Jaquez
 * @date 2021/10/05 19:08
 */
public interface SysCaptchaService extends IService<SysCaptcha> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
