

bean继承：两个类之间⼤多数的属性都相同，避免重复配置，通过bean标签的parent属性重⽤已
有的Bean元素的配置信息 继承指的是配置信息的复⽤，和Java类的继承没有关系

<!-- 配置视频video对象-->
       <bean id="video" class="net.xdclass.spring.domain.Video" scope="singleton">
            <property name="id" value="6"/>
            <property name="title" value="微信教程"/>
      </bean>

    
    parent ="video":继承了上面video的配置信息,继承指的是配置信息的复⽤，和Java类的继承没有关系


   <!--配置视频video对象2-->
   <bean id="video2" class="net.xdclass.spring.domain.Video2" scope="singleton" parent="video">
         <property name="summary" val












属性依赖: 如果类A是作为类B的属性, 想要类A⽐类B先实例化，设置两个Bean的依赖关系,这样更安全

<bean id="video" class="net.xdclass.sp.domain.Video" scope="singleton">
 <property name="id" value="9"/>
 <property name="title" value="Spring 5.X课程" />
</bean>




<!--设置两个bean的关系，video要先于videoOrder订单实例化 dependson="video"-->
<bean id="videoOrder" class="net.xdclass.sp.domain.VideoOrder" depends-on="video">
 <property name="id" value="8" />
 <property name="outTradeNo" value="23432fnfwedwefqwef2"/>
 <property name="video" ref="video"/>
</bean>


