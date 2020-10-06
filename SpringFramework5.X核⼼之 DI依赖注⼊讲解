

spring的依赖注⼊讲解
什么是DI Dependency Injection ,依赖注⼊
IOC容器在运⾏期间，动态地将对象某种依赖关系注⼊到对象之中，⽐如视频订单对象，依赖⽤视
频对象
案例实操




<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">


    <!--bean标签 id属性：指定Bean的名称，在Bean被别的类依赖时使⽤
    name属性：⽤于指定Bean的别名，如果没有id，也可以⽤name
    class属性：⽤于指定Bean的来源，要创建的Bean的class类，需要全限定名-->
    <bean id="videoService" class="net.xdclass.spring.service.impl.VideoServiceImpl">

    </bean>
     <!--配置订单对象-->
     <bean id="videoOrder" class="net.xdclass.spring.domain.VideoOrder">
           <property name="id" value="1"/>
           <property name="outTradeNo" value="aagaga123"/>
           <!--注入视频video对象-->
           <property name="video" ref="video"/>  //video就是下边video对象的id
     </bean>

   <!--配置视频video对象-->
     <bean id="video" class="net.xdclass.spring.domain.Video">
          <property name="id" value="6"/>
          <property name="title" value="微信教程"/>
     </bean>

</beans>
