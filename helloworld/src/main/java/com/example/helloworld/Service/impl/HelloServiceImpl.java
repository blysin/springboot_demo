package com.example.helloworld.Service.impl;

import com.example.helloworld.Service.HelloSerivce;
import org.springframework.stereotype.Service;

/**
 * Created by Blysin on 2017/7/25.
 */
@Service
public class HelloServiceImpl implements HelloSerivce{
    @Override
    public String hello() {
        return "hello world service";
    }
}
