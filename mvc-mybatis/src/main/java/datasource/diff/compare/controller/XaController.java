package datasource.diff.compare.controller;

import datasource.diff.compare.service.c3p0.DataService;
import datasource.diff.compare.service.xa.XAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyao
 * @date 2020/1/6 15:56
 * @description
 */
//@RestController
public class XaController {
    @Autowired
    XAService xaService;

    @RequestMapping("/xa/post")
    public String xa1() {
        xaService.update();
        return "ok";
    }

    @RequestMapping("/xa/get")
    public String xa2() {
        return xaService.get();
    }

    @RequestMapping("/xa/test")
    public String testTryCatchFinally() {
        xaService.testTryCatchFinally();
        return "ok,";
    }
}
