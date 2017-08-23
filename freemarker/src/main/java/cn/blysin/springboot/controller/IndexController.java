package cn.blysin.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Blysin
 * @since 2017/8/23
 */
@Controller
@Slf4j
@RequestMapping("")
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index/index";
    }
}
