package com.example.helloworld.controller;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping("/session")
    @ResponseBody
    public String session(HttpServletRequest request){
        return request.getSession().getId();
    }

    @RequestMapping("/session2")
    @ResponseBody
    public String session2(HttpServletRequest request){
        HttpSession session = request.getSession();
        DateTime nowTime = new DateTime(session.getCreationTime());
        return "session创建时间："+nowTime.toString("yyyy-MM-dd HH:mm:ss");
    }

}
