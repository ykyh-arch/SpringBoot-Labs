
package cn.iocoder.springboot.lab33.shirodemo.service.impl;


import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysCaptcha;
import cn.iocoder.springboot.lab33.shirodemo.exception.MyException;
import cn.iocoder.springboot.lab33.shirodemo.mapper.SysCaptchaMapper;
import cn.iocoder.springboot.lab33.shirodemo.service.SysCaptchaService;
import cn.iocoder.springboot.lab33.shirodemo.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

@Service("sysCaptchaService")
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements SysCaptchaService {

    // google.code.kaptcha 验证码工具类
    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new MyException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();

        SysCaptcha captchaEntity = new SysCaptcha();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        // 5分钟后过期
        captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        this.save(captchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {

        SysCaptcha sysCaptcha = this.getOne(new QueryWrapper<SysCaptcha>().eq("uuid", uuid));
        if(sysCaptcha == null){
            return false;
        }
        // 删除验证码，验证码只能使用一次
        this.removeById(uuid);
        if(sysCaptcha.getCode().equalsIgnoreCase(code) && sysCaptcha.getExpireTime().getTime() >= System.currentTimeMillis()){
            return true;
        }else if(sysCaptcha.getCode().equalsIgnoreCase(code) && sysCaptcha.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new MyException("验证码已失效");
        }
        return false;
    }
}
