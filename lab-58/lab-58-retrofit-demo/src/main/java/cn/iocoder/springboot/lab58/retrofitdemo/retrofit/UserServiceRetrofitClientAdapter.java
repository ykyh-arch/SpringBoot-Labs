package cn.iocoder.springboot.lab58.retrofitdemo.retrofit;

import cn.iocoder.springboot.lab58.retrofitdemo.interceptor.Sign;
import cn.iocoder.springboot.lab58.retrofitdemo.interceptor.TimeStampInterceptor;
import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.request.UserAddRequest;
import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.response.UserResponse;
import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.core.DefaultErrorDecoder;
import com.github.lianjiatech.retrofit.spring.boot.degrade.Degrade;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy;
import com.github.lianjiatech.retrofit.spring.boot.retry.Retry;
import com.github.lianjiatech.retrofit.spring.boot.retry.RetryRule;
import okhttp3.OkHttpClient;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceRetrofitClient 服务层，Retrofit 使用参考：https://square.github.io/retrofit/
 *
 * @author Jaquez
 * @date 2022/03/04 10:47
 */
@Component
@RetrofitClient(baseUrl="http://127.0.0.1:18080",logStrategy = LogStrategy.HEADERS)
public interface UserServiceRetrofitClientAdapter {


    /**
     * Call<T>，不执行适配处理，直接返回Call<T>对象
     *
     * @author Jaquez
     * @date 2022/03/06 18:21
     * @param id 
     * @return retrofit2.Call<cn.iocoder.springboot.lab58.retrofitdemo.retrofit.response.UserResponse> 
     */
    @GET("/user/get")
    Call<UserResponse> getCall(@Query("id") Integer id);

    /**
     * CompletableFuture<T>，将响应体内容适配成CompletableFuture<T>对象返回
     *
     * @param id
     * @return
     */
    @GET("/user/get")
    CompletableFuture<UserResponse> getCompletableFuture(@Query("id") Integer id);

    /**
     * Void，不关注返回类型可以使用Void。如果http状态码不是2xx，直接抛错！
     * @param id
     * @return
     */
    @GET("/user/get")
    Void getVoid(@Query("id") Integer id);

    /**
     * Response<T>，将响应内容适配成Response<T>对象返回
     *
     * @param id
     * @return
     */
    @GET("/user/get")
    Response<UserResponse> getResponse(@Query("id") Integer id);

    /**
     * 其他任意Java类型，将响应体内容适配成一个对应的Java类型对象返回，如果http状态码不是2xx，直接抛错！
     *
     * @author Jaquez
     * @date 2022/03/06 18:20
 *   * @param id
     * @return cn.iocoder.springboot.lab58.retrofitdemo.retrofit.response.UserResponse
     */
    @GET("/user/get")
    UserResponse get(@Query("id") Integer id);

}
