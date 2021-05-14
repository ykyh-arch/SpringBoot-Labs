package cn.iocoder.springboot.lab23.springmvc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.iocoder.springboot.lab23.springmvc.domain.User;

@Mapper
public interface UserDao {
	
	@Select("select * from user where id = #{id}")
	public User getById(@Param("id")int id);

	@Insert("insert into user(id, name)values(#{id}, #{name})")
	public int insert(User user);
	
}
