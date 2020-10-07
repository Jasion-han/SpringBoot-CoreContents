bean标签 id属性：指定Bean的名称，在Bean被别的类依赖时使⽤
name属性：⽤于指定Bean的别名，如果没有id，也可以⽤name
class属性：⽤于指定Bean的来源，要创建的Bean的class类，需要全限定名


<bean id="videoService" class="net.xdclass.spring.service.impl.VideoServiceImpl">

    </bean>
     <!--配置订单对象-->
     <bean id="videoOrder" class="net.xdclass.spring.domain.VideoOrder">
           <property name="id" value="1"/>
           <property name="outTradeNo" value="aagaga123"/>
           <!--注入视频video对象-->
           <property name="video" ref="video"/>
     </bean>

   <!--配置视频video对象-->
     <bean id="video" class="net.xdclass.spring.domain.Video">
          <property name="id" value="6"/>
          <property name="title" value="微信教程"/>
     </bean>

</beans>
