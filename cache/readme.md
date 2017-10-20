### Redis

导如starter，默认连接：localhost:6379，注入内置模版`StringRedisTemplate`即可直接使用

```xml
<!--reids-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### Guava

1.  自己实现`Cache`和`GuavaCacheManager`

2.  注册`GuavaCacheManager`

    ```java
    @Configuration
    public class GuavaConfiguration {

        @Bean
        public CacheManager getCompositeCacheManager() {
            CompositeCacheManager cacheManager = new CompositeCacheManager();
            List<CacheManager> cacheManagers = new ArrayList<>();
            cacheManagers.add(getGuavaCacheManager());
            cacheManager.setCacheManagers(cacheManagers);
            cacheManager.setFallbackToNoOpCache(true);
            return cacheManager;
        }

        public GuavaCacheManager getGuavaCacheManager() {
            GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
            Map<String, CacheBuilder> configMap = new HashMap<>();
            configMap.put("guavaCache", CacheBuilder.from("maximumSize=500, expireAfterWrite=1h"));
            configMap.put("variedCache", CacheBuilder.from("maximumSize=500, expireAfterWrite=30m"));
            guavaCacheManager.setConfigMap(configMap);
            return guavaCacheManager;
        }
    }
    ```

3.  开启Spring缓存，在主入口添加`@EnableCaching`注解

4.  使用`@Cacheable(value = "guavaCache",key = "'dept'+#id")`启用缓存