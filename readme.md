### Spring boot多模块的整合学习。

1.  helloworld：

    包含一个Controller和Junit的测试类。

2.  config：

    主要在application.yml中配置开发环境和生产环境的不同配置。并用过@ConfigurationProperties注解来注入配置类的属性。

3.  restfull：

    Controller使用restful API，并整合了MyBatis+druid，并配置日记级别

4.  ​

### 将项目打包成jar文件：

1.  在项目跟目录执行：mvn package
2.  运行java -jar xxx.jar即可运行

### 注解说明

#### @EnableAutoConfiguration：

​	项目中只能有一个该注解，用于自动为没有配置的以来进行自动配置。通常用于main方法的类中。但是@SpringBootApplication注解已经包含了改注解，因此这个注解没什么用处。

​	通过配置文件中的**spring: autoconfigure:  exclude: Xxx.class**可以声明Xxx.class类不需要自动配置。

#### @ComponentScan

​	该注解表示目标类的子包会被spring自动扫描，已经包含在@SpringBootApplication注解中，因此主入口因外放在最外层包下。

#### @Value

​	为属性注入值，但是优先级比application.yml低

#### @PropertySource

​	注入properties配置文件

#### @ConfigurationProperties

​	表示可以在application.yml配置文件中为该类注入属性，用(prefix = "guava")来匹配配置前缀。该注解也可以和@Bean一起使用，为Spring管理的类注入属性。

​	yml的属性名比较灵活，如为“threadNumber”注入属性可以用:

| 属性                    | 说明                                  |
| --------------------- | ----------------------------------- |
| `guava.threadNumber`  | 标准驼峰规则                              |
| `guava.thread-number` | 中划线表示，推荐用于`.properties`和`.yml`文件中   |
| `guava.thread_number` | 下划线表示，用于`.properties`和`.yml`文件的可选格式 |
| `GUAVA_THREAD_NUMBER` | 大写形式，使用系统环境变量时推荐                    |

#### @Configuration

​	相当于Spring中的Beans配置，里面可以包含多个@Bean注解。

​	一个项目中只能有一个自动配置类@EnableAutoConfiguration，但是可以有多个配置类@Configuration

#### @Bean

​	相当于Spring中的Bean配置，将对象添加到Spring容器中

### 其他东西

#### ApplicationRunner

实现该接口的类可以在项目启动后被调用执行：

```java
@Component
public class Initer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("------------------项目启动了------------------");
    }
}
```

ExitCodeGenerator接口可以实现项目关闭前执行。