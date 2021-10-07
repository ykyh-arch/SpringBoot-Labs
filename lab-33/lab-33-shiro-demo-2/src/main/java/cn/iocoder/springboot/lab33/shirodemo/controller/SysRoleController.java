
package cn.iocoder.springboot.lab33.shirodemo.controller;

import cn.iocoder.springboot.lab33.shirodemo.anno.SysLog;
import cn.iocoder.springboot.lab33.shirodemo.common.Constant;
import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysRole;
import cn.iocoder.springboot.lab33.shirodemo.service.SysRoleMenuService;
import cn.iocoder.springboot.lab33.shirodemo.service.SysRoleService;
import cn.iocoder.springboot.lab33.shirodemo.utils.PageUtils;
import cn.iocoder.springboot.lab33.shirodemo.validator.ValidatorHelp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * @author Jaquez
 * @date 2021/10/07 11:29
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 * @author Jaquez
	 * @date 2021/10/06 11:52
     * @param params 
	 * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper 
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:role:list") // 或校验权限调用 Realm
	public ResultWrapper list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}

		PageUtils page = sysRoleService.queryPage(params);

		return ResultWrapper.ok().put("page", page);
	}
	
	/**
	 * 角色列表
	 * @author Jaquez
	 * @date 2021/10/06 11:53
	 * @return com.baomidou.mybatisplus.extension.api.R 
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public ResultWrapper select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("create_user_id", getUserId());
		}
		List<SysRole> list = (List<SysRole>) sysRoleService.listByMap(map);
		
		return ResultWrapper.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 * @author Jaquez
	 * @date 2021/10/06 11:54
     * @param roleId
	 * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
	 */
	@GetMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public ResultWrapper info(@PathVariable("roleId") Long roleId){
		SysRole role = sysRoleService.getById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return ResultWrapper.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 * @author Jaquez
	 * @date 2021/10/06 11:54
     * @param role
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	@RequiresPermissions("sys:role:save")
	public ResultWrapper save(@RequestBody SysRole role){
		ValidatorHelp.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.saveRole(role);
		
		return ResultWrapper.ok();
	}
	
	/**
	 * 修改角色
	 * @author Jaquez
	 * @date 2021/10/06 11:54
     * @param role
	 * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	@RequiresPermissions("sys:role:update")
	public ResultWrapper update(@RequestBody SysRole role){
		ValidatorHelp.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return ResultWrapper.ok();
	}
	
	/**
	 * 删除角色
	 * @author Jaquez
	 * @date 2021/10/06 11:55
     * @param roleIds
	 * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public ResultWrapper delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return ResultWrapper.ok();
	}
}
