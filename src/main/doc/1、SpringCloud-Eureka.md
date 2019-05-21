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

## Eureka简单配置

#### Eureka-Server

##### 非集群环境

```yml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
```

##### 集群环境

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



##### 集群环境并制定密码

```properties
spring.application.name=eureka-server
server.port=1111

eureka.instance.hostname=t.cn
eureka.client.service-url.defaultZone=http://<username>:<password>@t1.cn:1112/eureka/,http://<username>:<password>@t1.cn:1113/eureka/

# 开启密码
security.basic.enabled=true
security.user.name=user
security.user.password=password
```

```properties
spring.application.name=eureka-server
server.port=1112

eureka.instance.hostname=t1.cn
eureka.client.service-url.defaultZone=http://<username>:<password>@t.cn:1111/eureka/,http://<username>:<password>@t1.cn:1113/eureka/

# 开启密码
security.basic.enabled=true
security.user.name=user
security.user.password=password

```

```properties
spring.application.name=eureka-server
server.port=1113

eureka.instance.hostname=t1.cn
eureka.client.service-url.defaultZone=http://<username>:<password>@t.cn:1111/eureka/,http://<username>:<password>@t1.cn:1112/eureka/

# 开启密码
security.basic.enabled=true
security.user.name=user
security.user.password=password
```

name一样，port、hostname不一样，defaultZone需要指向其他集群服务，注意





#### Eureka-Client

```properties
spring.application.name=helloworld

eureka.client.service-url.defaultZone=http://t.cn:1111/eureka/,http://t1.cn:1112/eureka/
```

defaultZone指向所有集群服务

defaultZone也可以配置用户名和密码：

```properties
eureka.client.service-url.defaultZone=http://<username>:<password>@t.cn:1111/eureka/
```



## Eureka详解

### 基础构架

- **服务注册中心**：Eureka-Server
- **服务提供方**：服务注册到注册中心后就是一个提供者，通过Eureka-Client注册
- **服务消费者**：从服务注册中心获取并使用其他服务，通常使用Ribbon或Feign方式来消费
  - 注册服务
  - 服务租约
  - 取消租约
  - 获取服务列表

------

- 当服务注册中心已集群方式部署时，服务提供者只需要向其中一个注册中心注册，其注册信息会自动同步到集群的其他注册中心中。

- 服务注册中心会已心跳方式去校验服务的可用性，称之为服务续约，通过配置可以修改心跳时间

  ```properties
  eureka.instance.lease-renewal-in-seconds=30
  # 心跳间隔
  eureka.instance.lease-expiration-duration-in-seconds=90
  # 服务时效时间，注册中心每60秒清理一次
  ```

  如果出现网络问题，心跳可能失败，如果在15分钟后心跳失败次数超过85%，注册中心会将改服务保护起来，但是这种情况下消费者可能出现服务调用失败的情况，此时就需要容错机制，比如**请求重试**，**断路由**等机制。

  ```properties
  eureka.server.enable-self-preservation=false 	
  # 关闭保护机制
  ```

  

- 服务启动后会定期去注册中心获取服务列表

  ```properties
  eureka.client.registry-fetch-interval-seconds=30
  # 获取服务时间间隔
  ```




### Client端配置列表

| 参数名                                      | 说明                                       | 默认值   |
| ---------------------------------------- | ---------------------------------------- | ----- |
| enable                                   | 是否启用Eureka客户端                            | true  |
| registryFetchIntervalSeconds             | 从注册中心获取服务列表的时间间隔                         | 30    |
| instanceInfoReplicationIntervalSeconds   | 续租时间间隔                                   | 30    |
| initialInstanceInfoReplicationIntervalSecond | 初始化实例信息到注册中心的间隔                          | 40    |
| eurekaService UrlPollIntervalSeconds     | 轮询Eureka注册中心地址更改的时间间隔，当使用spring boot config时动态刷新注册中心服务地址时使用 | 300   |
| eurekaServerReadTimeoutSeconds           | 读取注册中心信息的超市时间                            | 8     |
| eurekaServerConnectTimeoutSeconds        | 连接超时时间                                   | 5     |
| eurekaServerTotalConnections             | 客户端与服务端的连接总数                             | 200   |
| eurekaServerTotalConnectionsPerHost      | 客户端到每个服务端主机的连接总数                         | 50    |
| eurekaConnectionIdleTimeoutSeconds       | 注册中心连接的空闲关闭时间                            | 30    |
| heartbeatExecutorThreadPoolSize          | 心跳连接的初始化线程数                              | 2     |
| heartbeatExecutorExponentialBackOffBound | 心跳超市重试延迟时间最大倍数值                          | 10    |
| CacheReFreshExecutorThreadPoolSize       | 缓存刷新线程池的初始化线程数                           | 2     |
| useDnsForFetchingServiceUrls             | 使用DNS来获取注册中心的serviceUrl                  | false |
| registerWithEureka                       | 是否将自身注册到注册中心                             | true  |
| perferSameZoneEureka                     | 是否偏好使用同一zone的eureka服务端                   | true  |
| filterOnlyUpInstances                    | 获取实例时是否过滤，仅保留UP状态的实例                     | true  |
| fetchRegistry                            | 是否从eureka服务端获取注册信息                       | true  |
|                                          |                                          |       |

### 实例名配置

​	每个微服务想注册中心注册时都需要提供一个实例ID，服务端根据命名规则：

```
hostname:application.name:application.instanceId:post
```

来为每个服务实例命名。同一台服务器上做开发时，需要同时启动多个服务，有可能出现端口冲突的问题，可以使用随机端口来解决：

```properties
server.port=${radom.init[10000,19999]}
```

但是此时会导致注册中心的每个相同服务的不同实例的命名都是相同的，因此需要修改客户端的instanceId的生成规则：

```properties
eureka.intance.instanceId=${spring.application.name}:${random.int}
```





### 健康状态监测

​	注册中心和客户端直接都会想彼此发送心跳来监测服务是否可用，但是通常情况下，心跳成功的服务并不一定可用。比如某个服务A依赖于服务B，但是服务B如果宕机，服务A实际上也是不可用的状态，但是心跳监测无法检测出这个情况，因此需要借助actuator模块来向注册中心提供更加具体的健康信息，做法：添加actuator模块并添加配置：

```properties
eureka.client.healthcheck.enabled=true
```











1



### 



