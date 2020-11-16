package com.storm.apigw.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiServlet extends HttpServlet {
    final static private String METHOD = "method";
    final static private String PARAMS = "params";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("gbk");
        String methodName = req.getParameter(METHOD);
        if(methodName == null || methodName.equals("")){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status",0);
            map.put("success",false);
            map.put("message","方法名为空！");
            resp.getWriter().print(map.toString());
            return;
        }
        String params = req.getParameter(PARAMS);
        System.out.println("method:"+methodName+"---params:"+params);
        resp.getWriter().print("成功访问！");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
