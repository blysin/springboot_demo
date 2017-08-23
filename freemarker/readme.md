配置静态资源路径：
`mvc:
     static-path-pattern: /static/**`

配置devtools实现对freemarker的热部署：
`  devtools:
     remote:
       context-path: classpath:/views/`
       
actuator状态监控：
http://www.jianshu.com/p/734519d3c383