package cn.iocoder.springboot.labs.chat03.demo4.mapper;

import cn.iocoder.springboot.labs.chat03.demo4.dto.UserFindDto;
import cn.iocoder.springboot.labs.chat03.demo4.model.UserModel;
import org.apache.ibatis.session.ResultHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 通过name查询
     *
     * @param name
     * @return
     */
    UserModel getByName(String name);

    /**
     * 通过map查询
     * @param map
     * @return
     */
    List<UserModel> getByMap(Map<String,Object> map);

    /**
     * 通过UserFindDto进行查询
     * @param userFindDto
     * @return
     */
    List<UserModel> getListByUserFindDto(UserFindDto userFindDto);

    /**
     * 通过id或者name查询，源码位置：{@link org.apache.ibatis.reflection.ParamNameResolver#getNamedParams(Object[]) }
     *
     * @param id
     * @param name
     * @return
     */
    UserModel getByIdOrName(Long id, String name);
    // UserModel getByIdOrName(@Param("userId") Long id, @Param("userName") String name);

    /**
     * 查询用户id列表，源码位置：{@link org.apache.ibatis.session.defaults.DefaultSqlSession#wrapCollection(Object object)  }
     *
     * @param idCollection
     * @return
     */
    List<UserModel> getListByIdCollection(Collection<Long> idCollection);

    /**
     * 查询用户id列表
     *
     * @param idList
     * @return
     */
    List<UserModel> getListByIdList(List<Long> idList);

    /**
     * 查询用户id列表
     *
     * @param idList
     * @return
     */
    List<UserModel> getListByIdArray(Long[] idList);


    List<UserModel> getList(ResultHandler<UserModel> resultHandler);

}
