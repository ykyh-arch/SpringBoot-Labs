
package cn.iocoder.springboot.lab33.shirodemo.service.impl;

import cn.iocoder.springboot.lab33.shirodemo.common.Constant;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysMenu;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUserToken;
import cn.iocoder.springboot.lab33.shirodemo.mapper.SysMenuMapper;
import cn.iocoder.springboot.lab33.shirodemo.mapper.SysUserMapper;
import cn.iocoder.springboot.lab33.shirodemo.mapper.SysUserTokenMapper;
import cn.iocoder.springboot.lab33.shirodemo.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        // 系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            // 如果是管理员，则查询所有 SysMenu 数组
            List<SysMenu> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            // 如果是普通用户，则查询其拥有的 SysMenuEntity 数组
            permsList = sysUserMapper.queryAllPerms(userId);
        }
        // 用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            // 使用逗号分隔，每一个 perms
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenMapper.queryByToken(token);
    }

    @Override
    public SysUser queryUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
