package cn.iocoder.springboot.lab01.springsecurity.controller;

import cn.iocoder.springboot.lab01.springsecurity.common.AjaxResult;
import cn.iocoder.springboot.lab01.springsecurity.common.Constants;
import cn.iocoder.springboot.lab01.springsecurity.components.security.entity.LoginUser;
import cn.iocoder.springboot.lab01.springsecurity.components.security.service.SysPermissionService;
import cn.iocoder.springboot.lab01.springsecurity.dataobject.SysMenu;
import cn.iocoder.springboot.lab01.springsecurity.dataobject.SysUser;
import cn.iocoder.springboot.lab01.springsecurity.service.ISysMenuService;
import cn.iocoder.springboot.lab01.springsecurity.components.security.service.SysLoginService;
import cn.iocoder.springboot.lab01.springsecurity.components.security.service.TokenService;
import cn.iocoder.springboot.lab01.springsecurity.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author Jaquez
 * @date 2021/10/09 15:43
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        // 获得当前 LoginUser
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色标识的集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        // 返回结果
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        // 获得当前 LoginUser
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 获得用户的 SysMenu 数组
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        // 构建路由 RouterVo 数组。可用于 Vue 构建管理后台的左边菜单
        return AjaxResult.success(menuService.buildMenus(menus));
    }

}
