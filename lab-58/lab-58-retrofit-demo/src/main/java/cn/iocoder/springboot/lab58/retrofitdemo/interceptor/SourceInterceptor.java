package cn.iocoder.springboot.lab58.retrofitdemo.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BaseGlobalInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 来源拦截器，全局拦截器
 *
 * @author jaquez
 * @date 2022/03/06 16:56
 **/
@Component
public class SourceInterceptor extends BaseGlobalInterceptor {

    @Override
    public Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newReq = request.newBuilder()
                .addHeader("source", "jaquez")
                .build();
        return chain.proceed(newReq);
    }
}