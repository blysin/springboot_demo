package com.example.helloworld.controller;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
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
    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Registration registration;

    @RequestMapping("")
    @ResponseBody
    public String hello(String name){
        logger.info("host:{} service_id:{}, ",registration.getHost(),registration.getServiceId());
        return "host:"+registration.getHost()+" service_id:"+ registration.getServiceId()+" ---> " +registration.getPort();
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
