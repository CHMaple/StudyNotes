# Dubbo
Apache Dubbo是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。其核心部分包含：
远程通讯: 提供对多种基于长连接的NIO框架抽象封装，包括多种线程模型，序列化，以及“请求-响应”模式的信息交换方式。
集群容错: 提供基于接口方法的透明远程过程调用，包括多协议支持，以及软负载均衡，失败容错，地址路由，动态配置等集群支持。
自动发现: 基于注册中心目录服务，使服务消费方能动态的查找服务提供方，使地址透明，使服务提供方可以平滑增加或减少机器。

#Dubbo的架构模式
Dubbo的架构包括：Provider(服务提供方)、Container（服务运行的容器）、Registry（注册中心）、Consumer（服务消费方）、Monitor（监控中心）。
标准的调用流程如下：
1、服务容器启动、加载，运行服务提供方；
2、服务提供方在启动时向注册中心注册自己提供的服务；
3、服务消费方在启动时向注册中心订阅自己需要的服务；
4、注册中心返回给服务消费方以服务提供方的地址列表，如果有变更，注册中心将以长连接推送变更数据给服务消费方；
5、服务消费方根据负载均衡的算法，挑选一个服务提供方的地址进行调用；
6、服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

#Dubbo in Docker
为了做一个Dubbo的基础样例，我需要构建以下几个环境：
1、基于SpringBoot+Dubbo的基础项目，包含consumer和provider
2、Zookeeper service in Docker

#Dubbo Demo
springboot-dubbo-provider 模拟服务提供方
    提供方法ProviderService#toUpper
    使用注解@Service(interfaceClass = ProviderService.class),注意，该注解的依赖来自于import com.alibaba.dubbo.config.annotation.Service而不是常规的org.springframework.stereotype.Service
    在Application上添加@EnableDubboConfiguration注解，并在application.yml中配置注册中心和扫描服务的位置
springboot-dubbo-comsumer 模拟服务消费方
    使用@Reference注解注入ProviderService的bean实例，然后直接调用，在provider的console中能看到服务被成功调用
两者均配置了spring.dubbo.register.address: zookeeper://127.0.0.1:2181(本地docker环境中的zk节点)
