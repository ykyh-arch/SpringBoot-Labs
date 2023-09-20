package cn.iocoder.springboot.labs.chat03.demo3.mapper;

import cn.iocoder.springboot.labs.chat03.demo3.model.UserModel;

import java.util.List;

public interface UserMapper {

    List<UserModel> getList();
}
