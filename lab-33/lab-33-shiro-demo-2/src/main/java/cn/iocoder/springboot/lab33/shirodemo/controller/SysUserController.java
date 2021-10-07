
package cn.iocoder.springboot.lab33.shirodemo.controller;

import cn.iocoder.springboot.lab33.shirodemo.anno.SysLog;
import cn.iocoder.springboot.lab33.shirodemo.common.Constant;
import cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper;
import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysUser;
import cn.iocoder.springboot.lab33.shirodemo.dto.PasswordDto;
import cn.iocoder.springboot.lab33.shirodemo.service.SysUserRoleService;
import cn.iocoder.springboot.lab33.shirodemo.service.SysUserService;
import cn.iocoder.springboot.lab33.shirodemo.utils.PageUtils;
import cn.iocoder.springboot.lab33.shirodemo.validator.Assert;
import cn.iocoder.springboot.lab33.shirodemo.validator.ValidatorHelp;
import cn.iocoder.springboot.lab33.shirodemo.validator.group.AddGroup;
import cn.iocoder.springboot.lab33.shirodemo.validator.group.UpdateGroup;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * SysUserController
 *
 * @author Jaquez
 * @date 2021/10/06 11:39
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 所有用户列表
	 * @author Jaquez
	 * @date 2021/10/06 11:39
     * @param params
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ResultWrapper list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return ResultWrapper.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 * @author Jaquez
	 * @date 2021/10/06 11:40
	 * @return cn.iocoder.springboot.lab33.shirodemo.common.ResultWrapper
	 */
	@GetMapping("/info")
	public ResultWrapper info(){
		return ResultWrapper.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 *
	 * @author Jaquez
	 * @date 2021/10/06 11:40
     * @param form
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public ResultWrapper password(PasswordDto form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		// sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		// sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
		// 更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return ResultWrapper.error("原密码不正确");
		}
		return ResultWrapper.ok();
	}
	
	/**
	 * 用户信息
	 * @author Jaquez
	 * @date 2021/10/06 11:41
     * @param userId
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public ResultWrapper info(@PathVariable("userId") Long userId){
		SysUser user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return ResultWrapper.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 * @author Jaquez
	 * @date 2021/10/06 11:41
     * @param user
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public ResultWrapper save(@RequestBody SysUser user){
		ValidatorHelp.validateEntity(user, AddGroup.class);
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		return ResultWrapper.ok();
	}
	
	/**
	 * 修改用户
	 * @author Jaquez
	 * @date 2021/10/06 11:42
     * @param user
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public ResultWrapper update(@RequestBody SysUser user){
		ValidatorHelp.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return ResultWrapper.ok();
	}
	
	/**
	 * 删除用户
	 *
	 * @author Jaquez
	 * @date 2021/10/06 11:42
     * @param userIds
	 * @return com.baomidou.mybatisplus.extension.api.R
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public ResultWrapper delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return ResultWrapper.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return ResultWrapper.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return ResultWrapper.ok();
	}
}
