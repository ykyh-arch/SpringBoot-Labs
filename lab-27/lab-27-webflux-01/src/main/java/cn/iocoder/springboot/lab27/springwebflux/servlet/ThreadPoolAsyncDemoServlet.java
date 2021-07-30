package cn.iocoder.springboot.lab27.springwebflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 异步自定义线程池Demo
 *  Servlet 3.0对请求的处理虽然是异步的，但是对IO操作却依然是阻塞的，对于数据量大的请求体或者返回体，阻塞IO也将导致不必要的等待。
 *
 * @author jaquez
 * @date 2021/07/28 17:45
 **/
@WebServlet(value = "/threadPoolAsync", asyncSupported = true)
public class ThreadPoolAsyncDemoServlet extends HttpServlet {

    // 自定义线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.startAsync();

        // 执行任务
        executor.execute(() -> {

            // 耗时操作
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
