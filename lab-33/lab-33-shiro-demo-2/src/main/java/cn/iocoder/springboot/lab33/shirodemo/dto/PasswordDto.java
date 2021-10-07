package cn.iocoder.springboot.lab33.shirodemo.dto;

import lombok.Data;

/**
 * 密码
 *
 * @author jaquez
 * @date 2021/10/06 11:44
 **/
@Data
public class PasswordDto {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;
}
