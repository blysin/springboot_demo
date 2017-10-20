package cn.blysin.springboot.controller;

import cn.blysin.springboot.domain.Dept;
import cn.blysin.springboot.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Blysin
 * @since 2017/8/21
 */
@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("")
    public List<Dept> writeLog() {
        return deptService.findAll();
    }
}
