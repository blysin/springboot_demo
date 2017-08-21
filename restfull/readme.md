与mybatis的整合

1、配置

````thymeleafexpressions

spring
    profiles: dev
    datasource:
      url: jdbc:mysql://localhost:3306/guns?useUnicode=true&characterEncoding=utf-8&useSSL=true
      username: root
      password: root``
      
mybatis:
  mapper-locations: classpath:mapper/*/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
  type-aliases-package: cn.blysin.springboot.domain
````

dao层需要用@Mapper注解