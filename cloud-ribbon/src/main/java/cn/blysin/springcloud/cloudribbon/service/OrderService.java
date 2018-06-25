package cn.blysin.springcloud.cloudribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author Blysin
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

    /**
     * 发送异步请求
     *
     * Future在并发的时候不会阻塞，当前线程会等待返回结果
     *
     * @param orderId
     * @return
     */
    @HystrixCommand(fallbackMethod = "errorHandler")
    public Future<String> helloAsync(int orderId){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://orderserver/order/{1}", String.class, orderId + 100);
            }
        };
    }

    public String errorHandler(int orderId){
        return "something wrong with this service";
    }

    public String ping() {
        return restTemplate.getForObject("http://orderserver/order/ping", String.class);
    }
}
