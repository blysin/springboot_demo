package cn.blysin.springcloud.cloudrebbonserver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/{orderId}")
    public String get(@PathVariable Integer orderId){
        Map<String, Object> order = new HashMap<>();
        order.put("orderId", orderId);
        order.put("orderName", "商品一号");
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order.toString();
    }

    @RequestMapping("/ping")
    public String ping(){
        return "pong pong pong";
    }
}
