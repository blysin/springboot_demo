package cn.blysin.springboot.controller;

import cn.blysin.springboot.domain.Dept;
import cn.blysin.springboot.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Blysin
 * @since 2017/8/23
 */
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public void dept(){
        Dept dept = Dept.builder().fullname("异常测试").num(1).pid(12).simplename("ll").tips("1").version(1).build();
        try {
            deptService.insert(dept);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }
}
