Spring boot多模块的整合学习。

1.  helloworld：

    包含一个Controller和Junit的测试类。

2.  config：

    主要在application.yml中配置开发环境和生产环境的不同配置。并用过@ConfigurationProperties注解来注入配置类的属性。

3.  restfull：

    Controller使用restful API，并整合了MyBatis+druid，并配置日记级别

4.  1

