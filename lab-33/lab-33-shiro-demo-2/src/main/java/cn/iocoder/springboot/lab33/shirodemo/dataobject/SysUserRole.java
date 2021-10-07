package cn.iocoder.springboot.lab33.shirodemo.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色关联表
 *
 * @author jaquez
 * @date 2021/10/05 11:57
 **/
@Data
@TableName("sys_user_role")
public class SysUserRole {

    @TableId
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

}
