package cn.iocoder.springboot.labs.chat04.mapper;

import cn.iocoder.springboot.labs.chat04.model.UserModel;

/**
 * Mybatis将jdbc返回的int类型转换为对应类型源码参考：{@link org.apache.ibatis.binding.MapperMethod#rowCountResult(int rowCount) }
 *
 */
public interface UserMapper {

    /**
     * 插入用户信息，返回影响行数
     *
     * @param model
     * @return
     */
    int insertUser(UserModel model);

    /**
     * 更新用户信息，返回影响行数
     *
     * @param model
     * @return
     */
    long updateUser(UserModel model);

    /**
     * 根据用户id删除用户信息，返回删除是否成功
     *
     * @param userId
     * @return
     */
    boolean deleteUser(Long userId);

    int insertUser1(UserModel userModel);

    int insertUser2(UserModel userModel);

}
