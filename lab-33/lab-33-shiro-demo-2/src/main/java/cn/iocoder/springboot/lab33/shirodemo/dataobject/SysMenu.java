package cn.iocoder.springboot.lab33.shirodemo.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 *
 * @author jaquez
 * @date 2021/10/05 12:02
 **/
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    @TableId
    private Long menuId;

    /** 父菜单ID，一级菜单为0 */
    private Long parentId;

    /** 父菜单名称 */
    @TableField(exist = false)
    private String parentName;

    /** 菜单名称 */
    private String name;

    /** 菜单URL */
    private String url;

    /** 授权(多个用逗号分隔，如：user:list,user:create) */
    private String perms;

    /** 类型     0：目录   1：菜单   2：按钮 */
    private Integer type;

    /** 菜单图标 */
    private String icon;

    /** 排序 */
    private Integer orderNum;

    /** ztree 属性 */
    @TableField(exist = false)
    private Boolean open;

    @TableField(exist = false)
    private List<?> list;
}
