package cn.iocoder.springboot.lab27.springwebflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手动创建线程方式
 *
 * @author jaquez
 * @date 2021/07/28 17:28
 **/
@WebServlet(urlPatterns = "/newThreadAsync", asyncSupported = true)
public class NewThreadAsyncDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.startAsync();

        Runnable runnable = () -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        };
        // 开启线程处理
        new Thread(runnable).start();

    }
}
