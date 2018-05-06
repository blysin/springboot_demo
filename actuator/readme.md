# Actuator

用于监控服务状态，和druid的监视器类似。会自动创建一些端点，通过对应的路径访问，用来查看系统运行状态。

可以分为三类：应用配置类、度量指标类、操作空值类

## 应用配置类

返回的是项目启动前设定好一些配置信息，相当于静态报告

1. /autoconfig 

   获取自动化配置的信息，可以帮助我们方便的找到一些自动化配置为什么没有生效的原因，包括两部分：

   - positiveMatches:返回的是条件匹配成功的自动化配置
   - negativeMathces:返回的是条件匹配不成功的

2. /beans

   获取IOC中管理的所有bean。包含的信息：名称、scope作用域、type类型、resource class文件的具体路径、dependencies依赖的bean名称

3. /configprops

   获取配置的属性信息报告，只显示已经生效的配置

4. /env

   与/configprops不同，它用来获取所有可用的环境属性报告。

5. /mappings

   获取SpringMVC中所有路径映射

6. /info

   返回一些项目自定义的信息，默认情况下只返回空json。可以在application.properties中配置以info开头的属性，则调用这个接口时会将这些配置返回

## 度量指标

返回的是动态报告，应用在运行时的一些快照信息，例如内存、http请求统计、外部资源指标等。

1. /metrics

   返回各项数据的简要信息

   - 系统信息：处理器数量processors、运行时间uptime和instance.uptime，系统平均负载systemload.average
   - Men.*内存概要
   - Heap.*堆内存使用情况，
   - Nonheap.*非堆内存使用情况*
   - *Threads.*线程使用情况，包括线程数，守护线程数，线程峰值等
   - Classes.*应用加载和卸载的类统计*
   - *gc.*垃圾收集器的详细信息，包括回收次数gc.ps_scavenge.count、垃圾回收消耗时间
   - gc.ps_scavenge.time、标记-清除算法消耗时间gc.ps_marksweep.time
   - httpsessions.* Tomcat会话使用情况，包括最大回话max和活跃回话数active，
   - gauge.* HTTP请求的性能指标致意，主要用来反映一个绝对数值，例gauge.response.hello:5表示上一次hello请求的延迟时间为5毫秒
   - counter.* HTTP请求的性能指标之一，主要作为计数器，例如counter.status.200.hello:11表示hello请求返回200的次数为11次
   - gauge.*和counter.* 有一个特殊的请求star-star表示静态资源的访问统计

2. /heath

   获取应用各项健康指标信息

3. /dump

   用来暴露程序运行中的线程信息

4. /trace

   返回基本的HTTP跟踪信息，

## 操作控制类

可以对项目直接进行操作，例如/shutdown就可以直接关闭应用（需要配置endpoints.shutdown.enabled=true）