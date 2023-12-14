package cn.iocoder.springboot.labs.java17;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DemoWithZGCTest - ZGC 支持，Unicode 13 的支持，生成的代码的性能改进，HTTP 客户端改进
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithZGCTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, InterruptedException {
        // 卸载类：在不停机的情况下，卸载不需要的类，可以减少内存占用。
        // 日志记录：提供了更丰富的日志记录选项，方便进行调试和性能分析。
        // 启用 ZGC
        // java -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -jar MyApp.jar

        // 在代码中启用 ZGC
        // VMOption option1 = new VMOption("-XX:+UnlockExperimentalVMOptions");
        // VMOption option2 = new VMOption("-XX:+UseZGC");
        // List<VMOption> options = Arrays.asList(option1, option2);
        // Launcher launcher = Launcher.createLauncher(options.toArray(new VMOption[0]));
        // launcher.launch("com.example.MyApp", "arg1", "arg2");

        // 支持 Unicode 13 中的新增字符
        String emoji = "🙂";
        System.out.println(emoji);
        System.out.println(emoji.length()); // 输出 2

        // 反射
        Class<?> clazz = DemoWithZGCTest.class;
        Method method = clazz.getMethod("myMethod");
        Object obj = clazz.getDeclaredConstructor().newInstance();
        method.invoke(obj);

        // Lambda 表达式
        List<String> list = Arrays.asList("Java", "17", "新特性");
        list.stream().filter(s -> s.equals("Java")).collect(Collectors.toList());

        // HttpClient 客户端的改进
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com/"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

    public void myMethod(){
        System.out.println("反射执行了目标方法");
    }

}
