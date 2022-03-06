package cn.iocoder.springboot.lab58.retrofitdemo.retrofit;

import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.request.UserAddRequest;
import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Jaquez
 * @date 2022/03/04 10:47
 */
@Component
public class UserServiceRetrofitClientCallback implements UserServiceRetrofitClient{

    @Override
    public UserResponse get(Integer id) {
        // 降级逻辑
        return null;
    }

    @Override
    public List<UserResponse> list(String name, Integer gender) {
        return null;
    }

    @Override
    public List<UserResponse> list(Map<String, Object> queryMap) {
        return null;
    }

    @Override
    public Integer add(UserAddRequest request) {
        return null;
    }
}
