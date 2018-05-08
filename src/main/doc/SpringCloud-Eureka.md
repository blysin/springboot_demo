# Spring Cloud Eureka

SpringCloud的基础，用于服务注册和服务发现

- 服务注册（Eureka服务端）

  每个服务想登记中心注册自己提供的服务信息，例如主机ip，端口，版本号，通讯协议等，登记中心按服务名分类管理。注册中心会以心跳方式区检测清单中服务是否可用。

  | 服务名  | 位置                                       |
  | ---- | ---------------------------------------- |
  | 服务A  | 192.168.1.22:8080、192.168.1.23:8080      |
  | 服务B  | 192.168.1.22:8080、192.168.1.23:8080、192.168.1.24:8080 |

  ​	Eureka服务端以集群部署时，会自动同步注册信息，当某一台宕机重启时，会自动同步注册信息。因此推荐每个可用的区域都运行一个Eureka服务端。

- 服务发现（Eureka客户端）

  当服务C需要调用服务A时，需要先向注册中心获取服务A的信息，此时服务C获取到了两个地址192.168.1.22:8080、192.168.1.23:8080，然后C根据客户端负载向其中一个服务发起请求。

  ​	Eureka客户端主要提供注册于发现，通过注解和参数配置的方式嵌入在客户端服务中。

## Eureka-Server

### 非集群环境

```yml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

### 集群环境

```properties
spring.application.name=eureka-server
server.port=1111

eureka.instance.hostname=t.cn
eureka.client.service-url.defaultZone=http://t1.cn:1112/eureka/
```

```properties
spring.application.name=eureka-server
server.port=1112

eureka.instance.hostname=t1.cn
eureka.client.service-url.defaultZone=http://t.cn:1111/eureka/
```

name一样，port、hostname不一样，defaultZone需要指向其他集群服务，注意

    register-with-eureka: true
    fetch-registry: true
这两个值必须设置为true（application中的配置不能为false）

## Eureka-Client

```properties
spring.application.name=helloworld

eureka.client.service-url.defaultZone=http://t.cn:1111/eureka/,http://t1.cn:1112/eureka/
```

defaultZone指向所有集群服务