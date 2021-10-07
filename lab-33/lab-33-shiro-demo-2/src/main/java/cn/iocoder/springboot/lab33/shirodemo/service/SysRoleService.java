/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.iocoder.springboot.lab33.shirodemo.service;

import cn.iocoder.springboot.lab33.shirodemo.dataobject.SysRole;
import cn.iocoder.springboot.lab33.shirodemo.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * SysRoleService 服务层
 * @author Jaquez
 * @date 2021/10/05 18:02
 */
public interface SysRoleService extends IService<SysRole> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRole role);

	void update(SysRole role);

	void deleteBatch(Long[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
