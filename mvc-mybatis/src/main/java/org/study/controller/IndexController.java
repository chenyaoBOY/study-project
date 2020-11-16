package org.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.dao.mapper.IndexMapper;
import spring.validate.transactiontest.xml.TransactionService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/23:44
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/anno")
    @ResponseBody
    public String getAnnotation() throws NoSuchMethodException {
        Method method = IndexController.class.getMethod("getAnnotation");
        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
        return mapping.value()[0];
    }
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private TransactionService service;

    @RequestMapping("/index")
    @ResponseBody
    public String indexMapper() {
        Integer count = indexMapper.getCount();
        return count.toString();
    }

    @RequestMapping("/tran")
    @ResponseBody
    public String ifAbleRepeatReadInOneTransaction() {
        service.ifAbleRepeatReadInOneTransaction();
        return "ok";
    }

    /**
     * rest风格
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/rest/{name}/{age}")
    @ResponseBody
    public Map<String, Object> rest(@PathVariable String name,
                                    @PathVariable Integer age) {

        Map<String, Object> map = new HashMap<>();

        map.put("name", name);
        map.put("age", age);

        return map;
    }

    @RequestMapping("/exception")
    @ResponseBody
    public String exceptionTest() {
        try {
            System.out.println(1 / 0);
            return "ok";
        } catch (Exception e) {
            System.out.println(Integer.parseInt(""));
            return "error";
        }
    }
}
