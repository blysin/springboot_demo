# Spring Cloud Feign

声明式的RestFul API调用，将http请求封装类似成WebService接口。

SpringCloud对Feign进行增强，可以整合SpringMVC，Ribbon，Eureka，使用更加方便。

优点：对接口一次封装后调用放可以在任意位置多次调用，简化对Ribbon的使用

## 使用

### 1、添加依赖

```xml
 <!--添加feign依赖-->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-feign</artifactId>
    </dependency>
```



### 2、启动类添加

​	@EnableFeignClients

### 3、创建接口，Restful风格

创建一个Feign接口，并添加@FeignClient注解，name为服务提供方的服务名，RequestMapping与服务调用路径一致

```java

package com.itmuch.cloud.study.user.feign;
 
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.itmuch.cloud.study.user.entity.User;
 
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @GetMapping(value = "/{id}")
  public User findById(@PathVariable("id") Long id);
}
```



用来代替之前的RestTemplate

```java

package com.itmuch.cloud.study.user.feign;
 
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.itmuch.cloud.study.user.entity.User;
 
public class UserController {
    
    @Autoware
    private RestTemplate restTemplate;
    
      @GetMapping(value = "/test", method = RequestMethod.GET)
      public User test(){
restTemplate.getForObject("http://microservice-provider-user/{1}",User.class,orderId);
      }
}
```

### 4、传递普通参数

例如访问服务提供者的`/user?id=123&name=text`接口

```java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @GetMapping(value = "/user")
  public User findById(Long id,String name);
}
```

### 5、传递复杂对象参数

如果服务提供者是以对象形式接受参数，则feign的使用如下：

服务提供者：

```java
@GetMapping("/incret-age")//get无法调用，只能post请求
public User incretAge(User user) {
    user.setAge(user.getAge() + 1);
    return user;
}
```

feign定义：

```java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @GetMapping(value = "/incret-age")//不论配置get或post，统一都会变成post请求
  public User findById(User user);
}
```

feign在传递复杂参数时默认使用post请求，此时提供者必须将接口声明为post请求，否则调用会报错。



## 其他

### feign接口不支持继承

### feign请求GZIP压缩

```properties
feign.compression.request.enabled=true
feign.compression.request.mime-types=text/xml,application/xml,application/json
feign.compression.request.min-request-size=2048
feign.compression.response.enabled=true
```

### feign日志级别

```yaml
logging.level.project.user.UserClient: DEBUG
```

- `NONE`，无记录（**DEFAULT**）。
- `BASIC`，只记录请求方法和URL以及响应状态代码和执行时间。
- `HEADERS`，记录基本信息以及请求和响应标头。
- `FULL`，记录请求和响应的头文件，正文和元数据。





