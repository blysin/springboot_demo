package cn.blysin.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Blysin
 * @since 2017/8/23
 */
@Controller
@RequestMapping("")
@Slf4j
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        log.info("访问主页");
        return "index/index";
    }
}
