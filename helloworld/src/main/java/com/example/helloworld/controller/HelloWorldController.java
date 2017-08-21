package com.example.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Blysin on 2017/7/25.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    @RequestMapping("")
    @ResponseBody
    public String hello(String name){
        return "hello spring boot "+ name;
    }
}
