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

    @RequestMapping("writelog")
    public Object writeLog() {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return deptService.findAll();
    }
}
