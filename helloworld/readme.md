说明

只使用web依赖，创建一个controller即可启动

创建的Service只是测试Junit

# Eureka-Client
`<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>`
启动类添加：@EnableDiscoveryClient
配置文件：
`spring.application.name=helloworld
 
 eureka.client.service-url.defaultZone=http://t.cn:1111/eureka/,http://t1.cn:1112/eureka/`
 当注册中心是集群环境时，defaultZone可以配置多个值