package cn.iocoder.springboot.lab58.retrofitdemo.controller;

import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.UserServiceRetrofitClientAdapter;
import cn.iocoder.springboot.lab58.retrofitdemo.retrofit.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/demo02")
public class DemoController02 {

    @Autowired
    private UserServiceRetrofitClientAdapter userServiceRetrofitClientAdapter;

    @GetMapping("/test")
    public UserResponse test() {

        Call<UserResponse> call = userServiceRetrofitClientAdapter.getCall(1);
        CompletableFuture<UserResponse> completableFuture = userServiceRetrofitClientAdapter.getCompletableFuture(1);
        Response<UserResponse> response = userServiceRetrofitClientAdapter.getResponse(1);
        Void aVoid = userServiceRetrofitClientAdapter.getVoid(1);
        UserResponse userResponse = userServiceRetrofitClientAdapter.get(1);

        return userResponse;
    }



}
