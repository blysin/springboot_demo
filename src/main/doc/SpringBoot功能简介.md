# SpringBoot功能简介

## 事务处理机制

spring事物机制提供了一个PlatformTransactionManager接口，不同的数据访问技术的事物使用不同的接口实现.

| 数据访问技术    | 实现                            |
| :-------- | :---------------------------- |
| JDBC      | DataSsourceTransactionManager |
| JPA       | JpaTraansactionManager        |
| Hibernate | HibernateTransactionManager   |
| JDO       | JdoTransactionManager         |
| 分布式事务     | JtaTransactionManager         |





## 缓存支持

不同的缓存技术需要不同cacheManager

| CacheManager              | 描述                                       |
| ------------------------- | ---------------------------------------- |
| SimpleCacheManager        | 使用简单的Colleaction来存储缓存，主要用于测试             |
| ConcurrentMapCacheManager | 使用ConcurrentMap来缓存数据                     |
| NoOpCacheManager          | 仅用于测试                                    |
| GuavaCacheManager         | 使用Guava                                  |
| EhcacheCacheManager       | 使用Ehcache                                |
| JCacheCacheManager        | 使用JCache(JSR-107) 标准的实现作为缓存技术，如Apache Commons JCS |
| RedisCacheManager         | 使用Redis                                  |
| HazelcastCacheManager     | 使用Hazelcast                              |





## 异步消息

spring 对JMS和AMQP的支持分别来自于spring-jms 和spring-rabbit

他们分布需要ConnectionFactory来实现连接消息代理，并分别提供了JmsTemplate、RabbitTemplate

spring为JMS 、AMQP提供了@JmsListener @RabbitListener 注解在方法上监听消息代理发布的消息。我们只需要分别通过@EnableJms @EnableRabbit开启支持





## 配置加载权重

命令行>path配置>JNDI>jar包外配置>jar包内配置>@PropertySource>默认配置