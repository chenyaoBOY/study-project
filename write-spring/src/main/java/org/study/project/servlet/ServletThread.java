package org.study.project.servlet;

import javax.servlet.*;
import java.io.IOException;

public class ServletThread implements Servlet{
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("thread=="+Thread.currentThread().getName());
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
