package cn.iocoder.springboot.labs.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * DemoWithHttpClientTest - HttpClient
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithHttpClientTest {

    public static void main(String[] args) {
        // 使用 HttpClient.newBuilder() 实例创建 HttpClient 实例
        // 使用 HttpRequest.newBuilder() 实例创建 HttpRequest 实例
        // 使用 httpClient.send() 发出请求并获取响应对象。
        myMethod();

    }

    public static void myMethod(){
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://www.yiidian.com/"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers().allValues("content-type"));
            System.out.println("Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
