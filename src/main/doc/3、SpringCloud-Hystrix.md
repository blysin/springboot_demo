# SpringCloud-Hystrix

## 概述

​	基本组成：服务降级，断路器（熔断）

​	容错保护机制。微服务之间都是存在于不同的进程中，通过远程调用来执行，这会导致当网络异常或服务自身故障产生服务调用的延迟，此时若请求不断增加，调用方等待处理的响应越来越多，导致调用方服务瘫痪。

​	这样犹豫某一个服务故障，很容易因为依赖关系引发故障延伸，最终可能导致整个系统瘫痪，这样的架构相较于传统架构更加不稳定，因此需要断路器等一系类的服务保护机制。

​	电力学上的断路器功能是当某个元器件发生短路，断路器及时隔断该元器件，避免整个系统的元器件都发生损坏，例如一个保险丝就是一个断路器。分布式架构中的断路器功能类似，断路器监控某个服务故障，主动向调用方返回一个错误的响应，避免长时间等待，这个就避免了调用方线程阻塞造成的系统级故障。

​	Spring Cloud Hystrix实现了断路器和线程隔离等一系类服务保护功能，它基于Netfix开源的Hystrix，Hystrix提供了服务降级，服务熔断，线程和信号隔离，请求缓存，请求合并及服务监控等功能。

​	

​	Hystrix可以用到任意方法上，不一定要结合ribbon或feign使用



### 服务降级

​	接口调用失败、超时等情况加执行fallbackMethod备用方法，

### 断路器（熔断）

​	在依赖服务失效比例超过阈值时，手动或者自动地切断服务一段时间

​	

## 集成Hystrix

​	容错率机制是用来保护调用端的，因此是在调用端集成依赖和配置。

**pom：**

```xml
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
```



**启动类：**

```java
@EnableCircuitBreaker//开启断路由
@EnableDiscoveryClient
@SpringBootApplication
public class CloudRibbonApplication {
```

​	也可以用@SpringCloudApplication来替代上面三个注解，也就是说一个SpringCloud应用最基础的框架就是SpringBoot+服务发现+断路由组成！



**使用：**

```java
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
```

​	通过@HystrixCommand注解和fallbackMethod参数，在使用RestTemplate对象调用其他服务时，如果服务调用失败或者请求超时，会自动执行指定的fallbackMethod方法，fallbackMethod的形参必须和原方法形参一致。

​	执行fallbackMethod方法实际上称之为服务降级，发生降级的情况有“短路”状态，命令      线程池、请求队列被占满。该方法之中应该是从现有的数据或者缓存之中返回一些数据，fallbackMethod如果也涉及服务的调用，也需要加上@HystrixCommand注解，形成级联的降级策略，保证能够返回稳定的处理结果。

​	与@HystrixCommand对应的还有一个@HystrixObservableCommand注解，该注解通过观察者模式实现异步调用（不阻塞）fallbackMethod方法，也就是多个线程进入hello方法，会自动添加一个观察者，在发送请求去调用其他服务时，在其他服务返回失败的时候会直接通知所有的观察者去执行fallbackMethod回调。也就是说多条现场在等待调用次接口的时候，有一条线程返回失败了，所有线程都会去执行fallbackMethod回调。

## 同步请求和异步请求

```java
/**
 * 发送同步请求
 * 请求调用失败或者请求调用超时，则执行指定的错误回调方法
 *
 * @param orderId
 * @return
 */
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
```



## 服务降级

errorHandler回调方法必须在同一个类中，修饰符可以任意（public，protected，private）。

使用场景：

- 读操作。写入操作的时候一般讲错误信息放回给调用者，无需降级处理，只有读取的操作才需要降级去缓存或重发请求获取。
- 执行批处理或离线统计计算等命令

## 请求超时

​	ribbon和hystrix都可以设置请求超时。如果Hystrix与ribbon结合使用，Hystrix的超时时间大于为ribbon超时时间总和，一遍ribbon有足够的时间重试。

​	比如设置ribbon超时时间1s，重试3次，则Hystrix超时时间应该大于3s

## 异常处理

### 异常传递

HystrixCommand通过捕获异常来执行服务降级，他能捕获除了HystrixBadRequestException外的所有异常，如果想要忽略某些异常，可以进行配置：

```java
@HystrixCommand(fallbackMethod = "errorHandler",ignoreExceptions = {RuntimeException.class})
```



### 异常捕获

在回调方法传入一个Throwable对象即可捕获到原请求抛出的异常信息：

```java
public String errorHandler(int orderId,Throwable throwable){
    return "something wrong with this service";
}
```



## Feign-Hystrix整合

### 配置

```properties
feign.hystrix.enabled=true
```

所有方法都将被Hystrix包装

### 服务降级

```java
@FeignClient(name = "hello", fallback = HystrixClientFallback.class)
protected interface HystrixClient {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    Hello iFailSometimes();
}

static class HystrixClientFallback implements HystrixClient {
    @Override
    public Hello iFailSometimes() {
        return new Hello("fallback");
    }
}
```

创建一个类，继承并实现对应的方法，改方法将自动设置为fallbackMethod。

### 获取异常对象

```java
@FeignClient(name = "hello", fallbackFactory = HystrixClientFallbackFactory.class)
protected interface HystrixClient {
	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	Hello iFailSometimes();
}

@Component
static class HystrixClientFallbackFactory implements FallbackFactory<HystrixClient> {
	@Override
	public HystrixClient create(Throwable cause) {
		return new HystrixClientWithFallBackFactory() {
			@Override
			public Hello iFailSometimes() {
				return new Hello("fallback; reason was: " + cause.getMessage());
			}
		};
	}
}
```

通过FallbackFactory类来实现









