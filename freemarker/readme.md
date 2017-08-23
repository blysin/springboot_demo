配置静态资源路径：
`mvc:
     static-path-pattern: /static/**`

配置devtools实现对freemarker的热部署：
`  devtools:
     remote:
       context-path: classpath:/views/`