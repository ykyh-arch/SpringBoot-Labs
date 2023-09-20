package cn.iocoder.springboot.labs.chat03.demo1;

import java.util.List;

public interface UserMapper {

    int insertUser(UserModel model);

    int updateUser(UserModel model);

    int deleteUser(Long userId);

    List<UserModel> getUserList();
}
