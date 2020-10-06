!--singleton：单例 true 默认值，调⽤getBean⽅法返回是同⼀个对象,实例会被缓存起来，效率⽐较⾼
                             当⼀个bean被标识为singleton时候，spring的IOC容器中只会存在⼀个该bean
              prototype多列的 false，调⽤getBean⽅法创建不同的对象，会频繁的创建和销毁对象造成很⼤的开销-->

  <!--配置视频video对象-->
     <bean id="video" class="net.xdclass.spring.domain.Video" scope="singleton">
          <property name="id" value="6"/>
          <property name="title" value="微信教程"/>
     </bean>



//测试类

/*singleton：单例 默认值，调⽤getBean⽅法返回是同⼀个对象,实例会被缓存起来，效率⽐较⾼
           当⼀个bean被标识为singleton时候，spring的IOC容器中只会存在⼀个该bean
           prototype多列的，调⽤getBean⽅法创建不同的对象，会频繁的创建和销毁对象造成很⼤的开销*/
    private  static  void  testScope(ApplicationContext applicationContext){

        Video  video1 = (Video)applicationContext.getBean("video");

        Video  video2  = (Video)applicationContext.getBean("video");

        //靠匹配内存地址==是匹配内存地址
        System.out.println(video1==video2);

    }
