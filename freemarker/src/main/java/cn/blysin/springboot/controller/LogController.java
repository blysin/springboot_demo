package cn.blysin.springboot.controller;

import cn.blysin.springboot.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Blysin
 * @since 2017/8/21
 */
@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private DeptService deptService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public Object writeLog() {
        System.out.println(123);
        return deptService.findAll();
    }
}
