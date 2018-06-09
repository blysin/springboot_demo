package cn.blysin.springcloud.cloudribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author blysin
 * @since 2018/6/9
 */
@Service
public class OrderService {
    @Autowired
    RestTemplate restTemplate;

    //restTemplate请求调用失败或者请求调用超市，则执行指定的错误回调方法
    @HystrixCommand(fallbackMethod = "errorHandler")
    public String hello(int orderId){
        return restTemplate.getForObject("http://orderserver/order/{1}", String.class, orderId + 1);
    }

    public String errorHandler(int orderId){
        return "something wrong with this service";
    }
}
