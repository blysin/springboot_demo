# SpringBoot注册解析

## SpringBoot/SpringMVC

1. @SpringBootApplication

   包含@Configuration、@EnableAutoConfiguration、@ComponentScan，用于启动类上。

2. @Repository

   用于dao层组件

3. @Service

   用于Service层组件

4. @RestController、@Controller

   用于Controller层组件

5. @Component

   组件不好归类时统一用这个

6. @ResponseBody

   将返回的数据直接传递到前端，而不是实现路径跳转

7. @RequestBody

   表示将接收的json参数转为对象或List，该参数为必填参数

8. @ComponentScan

   扫描当前类下面的所有@Component等类，注册为bean交给IOC管理

9. @Configuration

   bean容器，进行bean对象的配置
   0

10. @Bean

  直接创建一个bean交给IOC管理

11. @EnableAutoConfiguration

    表示可以实现自动配置，结合一些@Enablexxx注解使用实现框架自动整合

    - @EnableConfigurationProperties开启对@ConfigurationProperties注解配置bean的支持
    - @EnableJpaRepositories开启对Spring Data JPA Repository的支持
    - @EnableTransactionManagement开启声明式事务
    - @EnableCaching开启缓存支持

12. @AutoWired

    byType方式注入依赖，加上(required=false)时，就算找不到bean也不会报错。

13. @Qualifier

    当生命了多个同类型的bean时，可以用@Qualifier(“name”)来指定，与@AutoWired结合使用

14. @Resource(name=”name”,type=”type”)

    若不声明name和type，默认byName来注入bean

15. @RequestMapping

    请求地址映射

16. @RequestParam

    必填的请求参数。该注解有6个可配置属性：

    1. params:指定request中必须包含某些参数值是，才让该方法处理。
    2. headers:指定request中必须包含某些指定的header值，才让该方法处理。
    3. value:指定请求的实际地址，指定的地址可以是URI Template 模式
    4. method:指定请求的method类型， GET、POST、PUT、DELETE等 
    5. consumes:指定处理请求的提交内容类型（Content-Type），如application/json,text/html; 
    6. produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回。

17. @GetMapping、@PostMapping

    相当于@RequestMapping（value=”/”，method=RequestMethod.Get\Post\Put\Delete等）

18. @PathVariable

    获取路径变量@RequestMapping(“user/get/mac/{macAddress}”) ；

19. @Import

    导入其他Configuration类

20. @ImportResource

    导入xml配置文件

21. @Value

    注入application.properties配置的值，如：@Value(value = “#{message}”) 

22. @RepositoryRestResource(collectionResourceRel = "people", path = "people")

    表示可以直接已rest风格的请求对某个javaBean对象进行crud操作，

    ```java
    @RepositoryRestResource(collectionResourceRel = "people", path = "people")
    public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
        List<Person> findByLastName(@Param("name") String name);
    }
    ```

    

    

## JPA

1. @Entity、@Table(name=)

   表明这是一个实体类，这两个注解一般一起使用，如果表名和实体类名一样可以省略@Table

2. @MappedSuperClass

   用于父类中，表示该类中的属性可以继承

3. @NoRepositoryBean

   一般用作父类的repository，有这个注解，spring不会去实例化该repository；

4. @Column

   属性和字段关联，同名科省略

5. @Id

   主键

6. @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = “repair_seq”)

   主键生成策略是sequence（可以为Auto、IDENTITY、native等，Auto表示可在多个数据库间切换），指定sequence的名字是repair_seq；

7. @SequenceGenerator(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1)

   name为sequence的名称，以便使用，sequenceName为数据库的sequence名称，两个名称可以一致；

8. @Transient

   表示该属性不用与数据库中的字段想映射，ORM框架会自动忽略该字段，如果没有添加这个字段，ORM框架会自动配置一个@Basic注解

9. @Basic(fetch=FetchType.LAZY)

   标记可以指定实体属性的加载方式

10. @JsonIgnore

  JSON序列化是忽略改属性

11. @JoinColumn(name="")

    关联映射：一对一：本表中指向另一个表的外键。

    一对多：另一个表指向本表的外键。

12. @OneToOne、@OneToMany、@ManyToOne

    对应Hibernate配置文件中的一对一，一对多，多对一。

    

## 异常处理

1. @ControllerAdvice

   包含@Component，可以被IOC管理，统一处理异常

2. @ExceptionHandler(Exception.class)

   处理指定异常

## SpringCloud

1. @EnableEurekaServer

   用于启动类，表示这是一个eureka服务注册中心

2. @EnableDiscoveryClient

   用于启动类，表示这是一个服务，可以被注册中心扫描到

3. @LoadBalanced

   开启负载均衡

4. @EnableCircuiBreaker

   用于启动类，开启断路由功能

5. @HystrixCommand(fallbackMethod="")

   用于方法上，fallbackMethod指定断路由回调方法

6. @EnableConfigServer

   用于启动类，表示这是一个配置中心，开启ConfigServer

7. @EnableZuulProxy

   用于启动类，开启zuul路由

8. @SpringCloudApplication

   包含@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuiBreaker三个注解，

   

## 其他

### 按条件初始化bean

| 注解                              | 说明                                       |
| ------------------------------- | ---------------------------------------- |
| @ConditionalOnBean              | 配置了某个bean                                |
| @ConditionalOnMissingBean       | 没有配置某个bean                               |
| @ConditionalOnClass             | ClassPath中存在某个类                          |
| @ConditionalOnMissingClass      | ClassPath中不存在某个类                         |
| @ConditionalOnExpression        | 给定的SpEL表达式计算结果为true时                     |
| @ConditionalOnJava              | JDK版本为指定值时                               |
| @ConditionalOnJndi              | 参数中给定的JNDI位置必须存在一个，如果没有给参数，则要有JNDI InitialContext |
| @ConditionalOnProperty          | 直接配置属性要有一个明确的值                           |
| @ConditionalOnResource          | ClassPath中有指定的资源文件                       |
| @ConditionalOnWebApplication    | 项目为Web项目时                                |
| @ConditionalOnNotWebApplication | 项目非Web项目时                                |



### 读取配置文件

```java
@Configuration
@PropertySource("classpath:blysin.properties")
public class BlysinProperties {
    @Value("This is value annotation")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double currnetTime;

//    @Value("#{demoService.another}")//import property from another bean
//    private String fromAnother;

    @Value("classpath:blysin.properties")
    private Resource blysinProperties;//注入配置文件

    @Value("http://www.baidu.com")
    private Resource baiduUrl;

    @Value("${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

    public void outPut() throws IOException {
        System.out.println(normal);
        System.out.println(osName);
        System.out.println(currnetTime);
        System.out.println(IOUtils.toString(baiduUrl.getInputStream()));
        System.out.println(bookName);
        System.out.println(environment.getProperty("book.author"));
        System.out.println(IOUtils.toString(blysinProperties.getInputStream()));
    }


}
```

### SpringEvent

1. 首先我们要实现ApplicationListener 实现我们自己的监听

2. 定义我们自己的事件 通过集成ApplicationEvent实现

3. 定义config启动

4. 通过applicationContext 发布事件

   ```java
   @Component
   public class DemoPublisher {
       @Autowired
       private ApplicationContext applicationContext;

       public void publish(String msg) {
           applicationContext.publishEvent(new DemoEvent(this, msg));
       }
   }
   ```

### SpringAvare

说明：bean和Spring是无耦合的，但是如果想用到Spring容器的功能资源，就要你的不饿按知道Spring的存在，这就是Spring aware



### 多线程

Spring通过TaskExecutor来实现多线程并发编程，使用ThreadPoolExecutor可以实现基于线程池的的TaskExecutor，使用@EnableAsync开启对异步任务的支持，并通过实际执行bean方法中使用@Async注解来声明一个异步任务

### 计划任务

通过配置注解@EenableScheduline来开启对计划任务的支持，然后在要执行的任务上加注解@Scheduled。Spring通过@Scheduled支持多种类型计划任务，包括cron，fixDelay，fixRate

### 条件注解@Conditional

@Conditional根据满足某一个特定条件创建一个特定的bean，比如，当一个jar包在一个类路径下的时候，自动配置一个或多个bena，或者只有摸个bean被创建时才会创建另外一个bean。总的来说，就是根据特定条件来空值bean的创建行为。这样我们可以利用这个特性进行一些自动的配置。

### 组合注解与源注解

类似继承效果，多个注解可以组合成一个注解，并实现多个功能

### @Enable***注解原理

### SpringMVC

通过实现WebApplicationInitializer，等同与在web.xml中进行配置。





# SpringCloud基础组件

基础的独立服务有三个，Eureka注册中心，zuul路由，config配置中心

## Eureka

服务注册和发现

## Ribon

客户端负载均衡

## Feign

将Restful接口封装成类似WebService接口调用方式

## Hystrix

断路器和服务降级

断路器：请求超时处理

服务降级：请求不可用时的替代处理方式

熔断：防止请求并发太多



