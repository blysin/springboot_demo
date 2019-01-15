需要去掉所有starter中的spring-boot-starter-logging，通常web和actuator会带这两个

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```



yml中指定配置文件：

```yml
logging:
  config: classpath:log4j2.xml
```



添加log4j2.xml