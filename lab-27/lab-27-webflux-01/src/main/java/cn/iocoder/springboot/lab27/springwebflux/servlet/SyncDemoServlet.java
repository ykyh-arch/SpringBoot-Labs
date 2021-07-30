package cn.iocoder.springboot.lab27.springwebflux.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 同步Demo
 * servlet 3.0
 * 参考：https://www.cnblogs.com/davenkin/p/async-servlet.html
 *
 * @author jaquez
 * @date 2021/07/28 16:52
 **/
@WebServlet("/syncDemo")
public class SyncDemoServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        new LongRunningProcess().run();
        response.getWriter().write("Hello World!");
    }

}
