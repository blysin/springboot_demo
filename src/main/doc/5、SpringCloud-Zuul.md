# Spring Cloud Zuul

网关：将外部的请求转发到微服务实例上。对外提供统一的入口，实现请求过滤，校验，服务聚合等功能。前后代人分离时前端就是通过zuul路由来访问

与Eureka整合：Zuul自身需要注册到Eureka中，同时从Eureka中获取其他服务的消息，之后访问微服务都是通过zuul跳转获得。

主要实现三个功能：代理+路由+过滤



当部署了一个服务提供者（`microservices-order`），端口`8111`，接口地址`/get/{id}`，时，对外的访问地址为：

> http://192.168.1.1:8111/get/123

启用zuul路由（端口9000）后，访问地址为：

> http://192.168.1.1:9000/microservices-orders/get/123



## 使用

zuul跟Eureka-server一样，需要独立的服务。它的作用跟nginx类似

1. 监听域名和端口
2. 根据服务名反向代理到微服务架构内部。
3. 实现服务端的负载均衡。



## 配置

### 启动类配置

@EnableZuulProxy

### 路由配置

每个服务都可以重新配置路由，防止对外暴露真实的服务名称

```yaml
zuul:
	routes:
		route-name.serviceId: microservices-order
		route-name.path: /od/**
```

route-name可以任意取值，此时，就可以用一下路径来访问

> http://192.168.1.1:9000/od/get/123

### 隐藏真实服务名

配置好路由后，还是可以通过服务名`http://192.168.1.1:9000/microservices-orders/get/123`来访问接口，此时应该隐藏服务名来增强系统安全性。

```yaml
zuul:
	ignored-services: "*"
	routes:
		route-name.serviceId: microservices-order
		route-name.path: /od/**
```

ignored-services配置型号表示隐藏所有的服务名称，也可以配置单个服务名称来隐藏特定的服务。

### 配置统一前缀

类似配置一个项目名，为每个服务配置统一路径

```yaml
zuul:
	prefix: /micro
	ignored-services: "*"
	routes:
		route-name.serviceId: microservices-order
		route-name.path: /od/**
```

此时访问路径变为

> http://192.168.1.1:9000/micro/od/get/123







