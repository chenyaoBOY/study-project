package org.crazy.thread.advanced.threadsafe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chenyao
 * @Description: 建立一个多线程情况下不安全的服务
 * @date 2018/6/7/13:58
 */
public class UnsafeServlet extends HttpServlet {


    private AtomicLong factor = new AtomicLong();
    private AtomicReference<NoChangeClass> ref =  new AtomicReference<NoChangeClass>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {





    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
