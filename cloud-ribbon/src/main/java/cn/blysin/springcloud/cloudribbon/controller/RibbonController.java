package cn.blysin.springcloud.cloudribbon.controller;

import cn.blysin.springcloud.cloudribbon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

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
        System.out.println("开始时间："+System.currentTimeMillis());
        String result = orderService.hello(orderId);
        System.out.println("结束时间："+System.currentTimeMillis());
        return result;
    }

    @RequestMapping("/orderAsync/{orderId}")
    public String getOrderAsync(@PathVariable Integer orderId){
        try {
            System.out.println("开始时间："+System.currentTimeMillis());
            return orderService.helloAsync(orderId).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            System.out.println("结束时间："+System.currentTimeMillis());
        }
        return null;
    }

    @RequestMapping("/ping")
    public String ping(){
        return orderService.ping();
    }


}
