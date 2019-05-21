# SpringMVC

### 请求参数自动封装成对象

```java
@GetMapping("/incret-age")
public User incretAge(User user) {
    user.setAge(user.getAge() + 1);
    return user;
}
```

此时请求参数为：

*http://localhost:8013/order/incret-age?userUuId=123&age=18&address=厦门市*

所有参数将自动封装成User对象





# Maven

## module分层思想

将所有的公共config类，domain类，**feign接口类**全部定义到基础module中，方便所有服务引入。



# SpringCloud

# serverId

serverId就是instance-id