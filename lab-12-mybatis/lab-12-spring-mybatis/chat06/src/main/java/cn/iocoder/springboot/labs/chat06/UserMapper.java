package cn.iocoder.springboot.labs.chat06;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<UserModel> getList1(Map<String, Object> paramMap);

    int insertBatch(List<UserModel> userModelList);

    List<UserModel> getList6(Map<String, Object> paramMap);

    List<UserModel> getList7(String orderSql);

    void insert(UserModel userModel);

    List<UserModel> getList8(Map<String, Object> paramMap);

    List<UserModel> getList9(Map<String, Object> paramMap);

}
