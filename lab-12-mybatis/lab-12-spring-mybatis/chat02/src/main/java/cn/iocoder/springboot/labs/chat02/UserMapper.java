package cn.iocoder.springboot.labs.chat02;

import java.util.List;

public interface UserMapper {

    int insertUser(UserModel model);

    int updateUser(UserModel model);

    // Mapper接口中方法的参数、返回值可以不和Mapper xml中的一致
    int deleteUser(Long userId);

    List<UserModel> getUserList();
}
