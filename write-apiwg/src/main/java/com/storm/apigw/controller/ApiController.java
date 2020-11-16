package com.storm.apigw.controller;

import com.storm.apigw.exception.ApiException;
import com.storm.apigw.service.ThreadService;
import com.storm.apigw.service.UserService;
import com.storm.apigw.utils.ApiUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * API网关demo
 *  通过一个接口，根据接口传入参数不同，调用不同服务
 */
@Controller
public class ApiController {
    final static private String METHOD = "method";
    final static private String PARAMS = "params";

    /**
     * maven启动方式路径：url:http://localhost:8080/storm/cloud/apiwg.do?method=?&params=?
     * @param request
     * @return {message=接口调用成功！, status=1, success=true}
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @RequestMapping("apiwg.do")
    @ResponseBody
    public String interfaceInvoke(HttpServletRequest request) throws Exception {
        String methodName = request.getParameter(METHOD);
        int i = 1/0;
        if(methodName == null || methodName.equals("")){
            throw new ApiException("必须传递method参数!");
        }
        String params = request.getParameter(PARAMS);
        if(params == null){
            throw new ApiException("必须传递params参数，值可为空!");
        }

        String result = ApiUtils.invokeApi(methodName, params);
        return result;

    }

}
