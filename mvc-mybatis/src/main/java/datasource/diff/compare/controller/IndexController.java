package datasource.diff.compare.controller;

import datasource.diff.compare.service.c3p0.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyao
 * @date 2020/1/2 10:26
 * @description
 */
@RestController
@RequestMapping("/c3p0")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    DataService dataService;

    @RequestMapping("/home")
    public String home() {
        try {
            int i =1 /0;
        } catch (Exception e) {
            logger.error("a={} {}{}","1","2","3",e);
        }
        return dataService.getUser();
    }
    @RequestMapping("/insert1")
    public String insert1() {

        return dataService.insert1();
    }
    @RequestMapping("/insert2")
    public String insert2() {

        return dataService.insert2();
    }


    @PostMapping(value = "/post",consumes = "application/json")
    public String post(@RequestBody String json){
        return "ok";
    }


}
