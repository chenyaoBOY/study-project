package com.storm.apigw.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalException implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object o, Exception e)
    {
        ModelAndView model = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> map = new HashMap<String, Object>();
        if(e instanceof ApiException){
            String message = ((ApiException) e).getMessage();
            map.put("message",message);
            map.put("status",0);
            map.put("success",false);
        }else{
            map.put("message","系统故障！"+e.getMessage());
            map.put("status",0);
            map.put("success",false);
        }
        view.setAttributesMap(map);
        model.setView(view);
        e.printStackTrace();
        return model;
    }
}
