package org.study.shiro.filter;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "GlobalFilter")
public class GlobalFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest)req;
        try{
            Object principal = SecurityUtils.getSubject().getPrincipal();
            if(principal==null) {
                if(request.getRequestURL().toString().contains("login.html")){
                    chain.doFilter(request, response);
                    return;
                }
                response.sendRedirect("/login.html");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            chain.doFilter(request, response);
        }
//        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
