package cn.iocoder.springboot.lab33.shirodemo.dto;

import lombok.Data;

/**
 * SysLoginDto
 *
 * @author jaquez
 * @date 2021/10/05 16:52
 **/
@Data
public class SysLoginDto {

    private String username;

    private String password;

    private String captcha;

    private String uuid;

}
