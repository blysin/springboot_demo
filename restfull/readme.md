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

