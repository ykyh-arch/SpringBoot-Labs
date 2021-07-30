package cn.iocoder.springboot.lab27.springwebflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步Demo
 * servlet 3.0
 * @author jaquez
 * @date 2021/07/28 17:21
 **/
@WebServlet(value = "/asyncDemo", asyncSupported = true) // 开启异步支持
public class ASyncDemoServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取异步上下文对象
        AsyncContext asyncContext = request.startAsync();
        // 异步处理，会向Servlet容器另外申请一个新的线程（主线程池），在这个新的线程中继续处理请求，而原先的线程将被回收到主线程池中。
        asyncContext.start(() -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 异步处理完任务告知Servlet容器
            asyncContext.complete();
        });

    }
}
