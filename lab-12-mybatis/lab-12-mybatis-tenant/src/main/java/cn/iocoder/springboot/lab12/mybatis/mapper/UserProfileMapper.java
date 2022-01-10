package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserProfileDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * UserProfileMapper
 *
 * @author jaquez
 * @date 2022/01/07 17:31
 **/
@Repository
public interface UserProfileMapper extends BaseMapper<UserProfileDO> {
}
