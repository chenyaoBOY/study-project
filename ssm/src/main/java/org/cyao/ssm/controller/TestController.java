package org.cyao.ssm.controller;

import org.cyao.ssm.service.FeebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyao
 * @Description:
 * @date 2018/6/23/23:44
 */
@Controller
public class TestController {

    @Value("#{properties['jdbc.driver']}")
    private String driver;
    @Value("#{properties['log4j.rootLogger']}")
    private String drirootLoggerver;

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @Autowired
    private FeebackService feebackService;
    /**
     * rest风格
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/rest/{name}/{age}")
    @ResponseBody
    public Map<String, Object> rest(@PathVariable String name,
                                    @PathVariable Integer age){

        Map<String, Object> map = new HashMap<>();

        map.put("name",name);
        map.put("age",age);

        return map;
    }

    /**
     * 一个方法可以映射多个路径
     * @return
     */
    @RequestMapping(value = {"test.do","chenyao.do"})
    @ResponseBody
    public String test(){
        return driver+"---"+drirootLoggerver;
    }


    @RequestMapping("transaction.do")
    @ResponseBody
    public String transactionTest(){
        feebackService.saveTest4Transaction();
        return "132456";
    }

}
