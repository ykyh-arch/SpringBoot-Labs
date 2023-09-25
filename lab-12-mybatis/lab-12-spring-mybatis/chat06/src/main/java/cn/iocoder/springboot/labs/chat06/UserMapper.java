package cn.iocoder.springboot.labs.chat06;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    int insertBatch(List<UserModel> userModelList);

    List<UserModel> getList6(Map<String, Object> paramMap);

    List<UserModel> getList7(String orderSql);
}
