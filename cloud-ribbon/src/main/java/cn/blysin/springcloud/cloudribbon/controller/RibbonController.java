package cn.blysin.springcloud.cloudribbon.controller;

import cn.blysin.springcloud.cloudribbon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/ribbon")
    public String ribbon(){
        //第一个参数是消费服务的URL，域名写服务名，而不是真正的域名
//        return restTemplate.getForEntity("http://helloworld/hello",String.class).getBody();
        return restTemplate.getForObject("http://helloworld/hello",String.class);
    }


    @RequestMapping("/order/{orderId}")
    public String getOrder(@PathVariable Integer orderId){
        return orderService.hello(orderId);
    }


}
