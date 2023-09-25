package cn.iocoder.springboot.labs.chat07;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<UserModel> getList1(Map<String, Object> paramMap);

    int insert1(Map<String, Object> map);
}
