什么是IOC Inverse of Control（控制反转）是⼀种设计思想 将原本在程序中⼿动创建对象的流
程，交由Spring框架来管理 核⼼：把创建对象的控制权反转给Spring框架，对象的⽣命周期由
Spring统⼀管理
把spring ioc 当成⼀个容器，⾥⾯存储管理的对象称为Bean,类实例
案例实操 配置⽂件⾥⾯定义⼀个bean，通过代码去获取


 <bean name="video" class="net.xdclass.sp.domain.Video">
 <property name="id" value="9"/>
 <property name="title" value="Spring 5.X课程" />
</bean>



ApplicationContext applicationContext = newClassPathXmlApplicationContext("applicationContext.xml");
    Video video = (Video)applicationContext.getBean("video");
     System.out.println(video.getTitle());
