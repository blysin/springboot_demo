配置静态资源路径：
`mvc:
     static-path-pattern: /static/**`

配置devtools实现对freemarker的热部署：
`  devtools:
     remote:
       context-path: classpath:/views/`
 
 [chrome插件]:http://livereload.com/extensions/
       
actuator状态监控：
http://www.jianshu.com/p/734519d3c383

freemarker无法触发更新，只能ctrl+f9(idea)手动触发更新