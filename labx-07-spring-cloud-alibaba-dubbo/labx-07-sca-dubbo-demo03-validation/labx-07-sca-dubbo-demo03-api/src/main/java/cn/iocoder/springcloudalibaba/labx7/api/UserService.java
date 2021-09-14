package cn.iocoder.springcloudalibaba.labx7.api;

import cn.iocoder.springcloudalibaba.labx7.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.dto.UserDTO;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

/**
 * 用户服务 RPC Service 接口
 */
public interface UserService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(@NotNull(message = "用户编号不能为空") Integer id)
            throws ConstraintViolationException;

    /**
     * 添加新用户，返回新添加的用户编号
     *
     * @param addDTO 添加的用户信息
     * @return 用户编号
     */
    Integer add(UserAddDTO addDTO)
            throws ConstraintViolationException;;

}
