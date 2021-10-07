
package cn.iocoder.springboot.lab33.shirodemo.controller;

import cn.iocoder.springboot.lab33.shirodemo.anno.SysLog;
import cn.iocoder.springboot.lab33.shirodemo.common.Constant;
import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysMenu;
import cn.iocoder.springboot.lab33.shirodemo.exception.MyException;
import cn.iocoder.springboot.lab33.shirodemo.service.ShiroService;
import cn.iocoder.springboot.lab33.shirodemo.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * SysMenuController 控制器
 * @author Jaquez
 * @date 2021/10/06 10:44
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 导航菜单，获得当前登录用户的菜单和权限
     */
    @GetMapping("/nav")
    public ResultWrapper nav() {
        // 获得用户的菜单数组
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
        // 获取用户的权限集合
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        // 返回
        return ResultWrapper.ok().put("menuList", menuList)
                .put("permissions", permissions);
    }

    /**
     * 用户菜单列表
     * @author Jaquez
     * @date 2021/10/06 11:11
     * @return java.util.List<cn.iocoder.springboot.lab33.shirodemo.dataobject.SysMenu>
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenu> list() {
        List<SysMenu> menuList = sysMenuService.list();
        for (SysMenu sysMenuEntity : menuList) {
            SysMenu parentMenuEntity = sysMenuService.getById(sysMenuEntity.getParentId());
            if (parentMenuEntity != null) {
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     * @author Jaquez
     * @date 2021/10/06 11:13
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public ResultWrapper select() {
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return ResultWrapper.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     * @author Jaquez
     * @date 2021/10/06 11:14
     * @param menuId
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public ResultWrapper info(@PathVariable("menuId") Long menuId) {
        SysMenu menu = sysMenuService.getById(menuId);
        return ResultWrapper.ok().put("menu", menu);
    }

    /**
     * 保存
     * @author Jaquez
     * @date 2021/10/06 11:14
     * @param menu
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @SysLog("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public ResultWrapper save(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return ResultWrapper.ok();
    }

    /**
     * 修改
     * @author Jaquez
     * @date 2021/10/06 11:14
     * @param menu
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @SysLog("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public ResultWrapper update(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return ResultWrapper.ok();
    }

    /**
     * 删除
     * @author Jaquez
     * @date 2021/10/06 11:14
     * @param menuId
     * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
     */
    @SysLog("删除菜单")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public ResultWrapper delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 31) {
            return ResultWrapper.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return ResultWrapper.error("请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return ResultWrapper.ok();
    }

    /**
     * 验证参数是否正确
     * @author Jaquez
     * @date 2021/10/06 11:15
     * @param menu
     * @return void
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new MyException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new MyException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new MyException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new MyException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new MyException("上级菜单只能为菜单类型");
            }
            return;
        }
    }

}
