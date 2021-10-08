package cn.iocoder.springboot.lab33.shirodemo.dao;

import cn.iocoder.springboot.lab33.shirodemo.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 通过用户ID查询角色集合
     * @author Jaquez
     * @date 2021/10/07 15:48
     * @param userId
     * @return java.util.List<cn.iocoder.springboot.lab33.shirodemo.entity.SysRoleEntity>
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
	
}
