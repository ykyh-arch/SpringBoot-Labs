package cn.iocoder.springboot.lab33.shirodemo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 token 表
 *
 * @author jaquez
 * @date 2021/10/05 12:12
 **/
@Data
@TableName("sys_user_token")
public class SysUserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID，插入数据库前自行设置主键值
    @TableId(type = IdType.INPUT)
    private Long userId;
    //token
    private String token;
    //过期时间
    private Date expireTime;
    //更新时间
    private Date updateTime;
}
