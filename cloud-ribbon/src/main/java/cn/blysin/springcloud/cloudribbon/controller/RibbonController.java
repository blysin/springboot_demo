package cn.blysin.springcloud.cloudribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon")
    public String ribbon(){
        //第一个参数是消费服务的URL，域名写服务名，而不是真正的域名
        return restTemplate.getForEntity("http://helloworld/hello",String.class).getBody();
    }


    @RequestMapping("/order")
    public String getOrder(){
        //第一个参数是消费服务的URL，域名写服务名，而不是真正的域名
        return restTemplate.getForEntity("http://orderserver/order/{1}",String.class,121133).getBody();
    }


}
