package cn.iocoder.springboot.lab27.springwebflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 非阻塞的异步Demo
 * servlet 3.1.0
 *
 * @author jaquez
 * @date 2021/07/28 18:36
 **/
@WebServlet(value = "/nonBlockingThreadPoolAsync", asyncSupported = true)
public class NonBlockingAsyncDemoServlet  extends HttpServlet {
    // 自定义线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 异步上下文
        AsyncContext asyncContext = request.startAsync();

        ServletInputStream inputStream = request.getInputStream();

        // 非阻塞 设置读监听器
        inputStream.setReadListener(new ReadListener() {
            @Override
            public void onDataAvailable() throws IOException {
            }

            // 数据准备完毕
            @Override
            public void onAllDataRead() throws IOException {
                executor.execute(() -> {
                    new LongRunningProcess().run();

                    try {
                        asyncContext.getResponse().getWriter().write("Hello World!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    asyncContext.complete();

                });
            }
            @Override
            public void onError(Throwable t) {
                asyncContext.complete();
            }
        });
    }

}
