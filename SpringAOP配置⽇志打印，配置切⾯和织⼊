


public class TimHandler {


    /**
     * 横切关注点
     * 用于打印日志，并且让其在切入点方法执行前执行(切入点方法就是业务层被增强的方法)
     */

    public  void  printBefor(){
        System.out.println("printBefor 日志  time ="+ LocalDateTime.now().toString());
    }

    public  void  printAfter(){

        System.out.println("printAfter 日志  time ="+ LocalDateTime.now().toString());

    }

}




//service接口类

public interface VideoService {

    public int  save(Video video );



    public  Video  findById(int id);
}






//service实现类

ublic class VideoServiceImpl implements VideoService {


    @Override
    public int save(Video video) {
        System.out.println("保存video");
        return 1;
    }

    @Override
    public Video findById(int id) {
        System.out.println("根据id查找视频");
        return new Video();
    }
}






//resources配置类applicationContext.xml文件里配置Aop方式

    <!-配置aopTimHandler类,也是横切关注点,可以配置多个横切关注点，   和配置多个<aop:aspect-->
    <!--横切关注点也可以是事务类，将事务引入进来，然后控制serviceImpl里的增删改方法-->
       <bean id="timHandler" class="net.xdclass.spring.aop.TimHandler"/>

        <!--配置service-->
       <bean id="videoService" class="net.xdclass.spring.service.impl.VideoServiceImpl"/>

        <!--配置Aop-->
        <aop:config>
            <!--配置切面,引用日志类-->
            <aop:aspect id="tHandler" ref="timHandler">
                <!--配置通知类型，并建立通知方法和切入点方法之间的关联，method代表我们创建的那个TimHandler日志类的方法名字-->
                <aop:before method="printBefor" pointcut="execution(* net.xdclass.spring.service.impl.*.*(..))"></aop:before>
                <aop:after method="printAfter" pointcut="execution(* net.xdclass.spring.service.impl.*.*(..))"></aop:after>
            </aop:aspect>

        </aop:config>








//main方法测试类

    //实战SpringAOP配置⽇志打印，配置切⾯和织⼊
    private static void testAop(ApplicationContext context){

                VideoService    videoService = (VideoService) context.getBean("videoService");
                              videoService.save(new Video());
                              videoService.findById(1);
    }









//切入点方法的配置模式:
<!--第一个只配置save方法打印日志-->
    <!--<aop:before method="printBefor" pointcut="execution(* net.xdclass.spring.service.VideoService.sav*(..))"></aop:before>
       第二个是impl下的所有方法
    <aop:before method="printBefor" pointcut="execution(* net.xdclass.spring.service.impl.*.*(..))"></aop:before>



