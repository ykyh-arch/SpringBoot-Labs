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
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceRetrofitClient 服务层，Retrofit 使用参考：https://square.github.io/retrofit/
 *
 * @author Jaquez
 * @date 2022/03/04 10:47
 */
@Component
@RetrofitClient(baseUrl="http://127.0.0.1:18080",poolName="test1",logStrategy = LogStrategy.HEADERS,logLevel = Level.INFO,errorDecoder = DefaultErrorDecoder.class,fallbackFactory = UserServiceRetrofitClientCallbackFactory.class)
@Intercept(handler = TimeStampInterceptor.class, include = {"/user/**"}, exclude = "/user/add") // 拦截器使用方式
@Sign(accessKeyId = "jaquez@123", accessKeySecret = "jaquez@123@secrect", exclude = {"/user/add"}) // 自定义拦截器注解使用
public interface UserServiceRetrofitClient {


    // 自定义 OkHttpClient，可选配置
    @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS);

    }

    // 获得用户详情，请求方法 请求地址
    @GET("/user/get")
    @Retry(maxRetries = 2,intervalMs = 100,retryRules = {RetryRule.RESPONSE_STATUS_NOT_2XX, RetryRule.OCCUR_IO_EXCEPTION}) // 重试策略
    @Degrade(count=3,timeWindow = 5) // 服务降级配置
    UserResponse get(@Query("id") Integer id);

    @GET("/user/list")
    List<UserResponse> list(@Query("name") String name,
                            @Query("gender") Integer gender);

    @GET("/user/list")
    List<UserResponse> list(@QueryMap Map<String, Object> queryMap);

    @POST("/user/add")
    @Headers("Content-Type: application/json")
    Integer add(@Body UserAddRequest request);

}
