package cn.iocoder.springboot.lab21.cache.mapper;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
// 统一配置 UserMapper 的缓存名称为 users
@CacheConfig(cacheNames = "users")
public interface UserMapper extends BaseMapper<UserDO> {

    // 被动写 优先读缓存
    @Cacheable(key = "#id")
    UserDO selectById(Integer id);

    // 主动写 写缓存
    @CachePut(key = "#user.id")
    default UserDO insert0(UserDO user) {
        // 插入记录
        this.insert(user);
        // 返回用户
        return user;
    }

    @CacheEvict(key = "#id")
    int deleteById(Integer id);

}
