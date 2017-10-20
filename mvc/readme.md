## SpringMVC主配置类

>   `WebMvcConfigurerAdapter`类可以对SpringMVC进行大多数配置，通过覆盖方法来实现对默认配置修改。

### 配置拦截器

1.  正常情况下写好一个拦截器

2.  创建一个配置类，用来配置SpringMVC的一些配置

    ```java
    @Configuration
    public class WebConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");//添加一个拦截器，并配置拦截uri
            super.addInterceptors(registry);
        }
    }
    ```



### 整合fastjson

1.  导入fastjson包

2.  在`WebConfig`中配置

    ```java
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
        super.configureMessageConverters(converters);
    }
    ```

### 解决跨域问题

```java
/**
 * 配置跨域请求，在对应的请求方法上添加@CrossOrigin注解即可
 *
 * @param registry
 */
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**");
    super.addCorsMappings(registry);
}
```







## 其他配置

### 项目监听器

可以实现在项目启动后自动执行。

```java
@Component
public class Initer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("------------------项目启动了------------------");
    }
}
```

### Servlet、filter、listener配置

1.  主入口添加`@ServletComponentScan`注解

2.  在对应的类上添加`@WebServlet(urlPatterns="/verifyCode")`、`@WebFilter`、`@WebListener`、`@WebInitParam(name="num",value = "3")`就可以实现被Spring扫描。

3.  Servlet全局配置：

    >   1.  网络设置：监听进入Http请求的端口（`server.port`），接口绑定地址`server.address`等。
    >   2.  Session设置：session是否持久化（`server.session.persistence`），session超时时间（`server.session.timeout`），session数据存放位置（`server.session.store-dir`），session-cookie配置（`server.session.cookie.*`）。
    >   3.  Error管理：错误页面的位置（`server.error.path`）等。

# log
#### 说明

​	只要引入starter，就会默认引入日志系统。SpringBoot底层的日志会将java.util.logging,Log4J,Slf4J,Logback全部引入，默认使用Logback，不需要额外导入其他以来就可以使用其他类型的日志。

​	根据日志文件的类型，Springboot将自动选择使用哪种类型的日志。

| 日志系统                    | 定制配置                                     |
| ----------------------- | ---------------------------------------- |
| Logback                 | `logback-spring.xml`,`logback-spring.groovy`,`logback.xml`或`logback.groovy` |
| Log4j                   | `log4j.properties`或`log4j.xml`           |
| Log4j2                  | `log4j2-spring.xml`或`log4j2.xml`         |
| JDK (Java Util Logging) | `logging.properties` *最好别用*              |

### logback日志配置示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->
    <property name="LOG_HOME" value="/log" />
    <!-- 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%clr表示使用彩色字体，%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%clr([%-5level]) %d{HH:mm:ss} %clr(%logger{50}){yellow} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- 按照每天生成日志文件 -->
    <appender name="FILE"  class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_HOME}/TestWeb.log.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <logger name="org.mybatis" level="INFO" additivity="false"/>
    <logger name="org.springframework.boot" level="INFO" additivity="false"/>
    <logger name="org.apache.ibatis" level="INFO" additivity="false"/>
    <logger name="org.springframework.web" level="WARN" additivity="false"/>
    <logger name="org.springframework.web.servlet" level="WARN" additivity="false"/>

    <!--其他包的日志-->
    <logger name="cn.blysin.springboot" level="DEBUG" additivity="false">
        <!--<appender-ref ref="CONSOLE"/>-->
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </logger>
</configuration>
```

支持的颜色如下：blue、cyan、faint、green、magenta、red、yellow

# mybatis
### 与mybatis的整合

1、配置

```properties
spring
    profiles: dev
    datasource:
      url: jdbc:mysql://localhost:3306/guns?useUnicode=true&characterEncoding=utf-8&useSSL=true
      username: root
      password: root
      
mybatis:
  mapper-locations: classpath:mapper/*/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
  type-aliases-package: cn.blysin.springboot.domain
```

### dao层需要用@Mapper注解

```properties
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/guns?useUnicode=true&characterEncoding=utf-8&useSSL=true
    username: root
    password: root

mybatis:
  mapper-locations: classpath:mapper/*/*.xml
  configuration:
      log-impl: org.apache.ibatis.logging.log4j.Log4jImpl
  type-aliases-package: cn.blysin.springboot.domain
```

### druid配置

```properties
spring:
  datasource:
    druid:
      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      filters: stat,wall,log4j
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
      # 合并多个DruidDataSource的监控数据
      use-global-data-source-stat: true
      # 登陆名和密码
      stat-view-servlet:
        login-username: blysin
        login-password: blysin
```

### 日记配置

```properties
mybatis:
  configuration:
      log-impl: org.apache.ibatis.logging.log4j.Log4jImpl
```

添加一个logback.xml文件，配置日记级别：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
  	<!--这里可以为不同包配置不同等级的日记输出-->
    <logger name="cn.blysin.springboot" level="DEBUG" additivity="false">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </logger>
</configuration>
```

 