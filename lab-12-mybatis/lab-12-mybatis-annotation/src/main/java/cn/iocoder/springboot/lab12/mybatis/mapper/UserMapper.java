package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 基于注解的使用方式
 * 基于OGNL 编写动态 SQL（Java中的一个开源的表达式语言），参考：https://www.cnblogs.com/EasonJim/p/7449699.html
 * @author Jaquez
 * @date 2021/08/09 10:47
 */
@Repository
public interface UserMapper {

    // 新增
    @Insert("INSERT INTO users(username, password, create_time) VALUES(#{username}, #{password}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(UserDO user);

    @Update(value = {
            "<script>",
            "UPDATE users",
            "<set>",
            "<if test='username != null'>, username = #{username}</if>",
            "<if test='password != null'>, password = #{password}</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    int updateById(UserDO user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    // 生产请使用标记删除，除非有点想不开，嘿嘿。
    int deleteById(@Param("id") Integer id);

    @Select("SELECT username, password, create_time FROM users WHERE id = #{id}")
    UserDO selectById(@Param("id") Integer id);

    @Select("SELECT username, password, create_time FROM users WHERE username = #{username}")
    UserDO selectByUsername(@Param("username") String username);

    @Select(value = {
            "<script>",
            "SELECT username, password, create_time FROM users",
            "WHERE id IN",
            "<foreach item='id' collection='ids' separator=',' open='(' close=')' index=''>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

}
