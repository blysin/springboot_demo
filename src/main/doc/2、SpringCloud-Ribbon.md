# SpringCloud-Ribbon

用于服务的消费者发现服务和实现客户端负载

![Ribbon-Eureka关系图.png](image/Ribbon-Eureka关系图.png)

## 使用

依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

配置：

```java
@EnableDiscoveryClient//eureka客户端
@SpringBootApplication
public class CloudRibbonApplication {

    /**
     * RestTemplate是服务发现和客户端负载的实现类
     *
     * @return
     */
    @Bean
    @LoadBalanced//开启负载
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

使用：

```java
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/order/{orderId}")
    public String getOrder(@PathVariable Integer orderId){
        //第一个参数是消费服务的URL，域名写服务名，而不是真正的域名
        String result =  restTemplate.getForObject("http://orderserver/order/{1}",String.class,orderId+1);
        result =  restTemplate.getForObject("http://orderserver/order/{1}",String.class,orderId+1);
        return result;
    }
```



