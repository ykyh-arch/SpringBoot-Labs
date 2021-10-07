
package cn.iocoder.springboot.lab33.shirodemo.service;


import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUserToken;

import java.util.Set;

/**
 * shiro 服务层
 * @author Jaquez
 * @date 2021/10/05 15:49
 */
public interface ShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(Long userId);
}
