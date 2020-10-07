
新建一个package文件 文件名:processor,实现BeanPostProcessor接口，重新两个方法. 如果注册多个BeanPostProcessor，必须在后面实现Ordered接口，这是
指定哪个BeanPostProcessor优先级的。该⽅法返回整数，默认值为 0优先级最⾼，值越⼤优先级越低



*什么是BeanPostProcessor
        是Spring IOC容器给我们提供的⼀个扩展接⼝
        在调⽤初始化⽅法前后对 Bean 进⾏额外加⼯，ApplicationContext 会⾃动扫描实现了
        BeanPostProcessor的 bean，并注册这些 bean 为后置处理器
        是Bean的统⼀前置后置处理⽽不是基于某⼀个bean*/



    

public class CustomProcessor1 implements BeanPostProcessor,Ordered{

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor1 postProcessBeforeInitialization beanName="+beanName);

        return bean;  //这里不能返回Null
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor1 postProcessAfterInitialization beanName="+beanName);

        return bean;  //这里不能返回null
    }



    /*该⽅法返回整数，默认值为 0优先级最⾼，值越⼤优先级越低*/
    @Override
    public int getOrder() {
        return 1;
    }
}


============================================================================================================



/*可以注册多个BeanPostProcessor顺序
          在Spring机制中可以指定后置处理器调⽤顺序，通过BeanPostProcessor接⼝实现类实现
           Ordered接⼝getOrder⽅法，该⽅法返回整数，默认值为 0优先级最⾼，值越⼤优先级越低*/



public class CustomProcessor2 implements BeanPostProcessor,Ordered {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor2 postProcessBeforeInitialization beanName="+beanName);

        return bean;  //不能返回null
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor2 postProcessAfterInitialization beanName="+beanName);

        return bean;   //不能返回Null
    }



    //该⽅法返回整数，默认值为 0优先级最⾼，值越⼤优先级越低
    @Override
    public int getOrder() {
        return 2;
    }

==========================================================================================================



    resources\ApplicationContext.xml配置文件里配置以下

    <bean class="net.xdclass.spring.processor.CustomProcessor"/>
     <bean class="net.xdclass.spring.processor.CustomProcessor2"/>



------------------------------------------------------------------------------------------------


     //测试类，测试结果反馈
     private static void testInject(ApplicationContext context) {

        Video videos = (Video) context.getBean("video");
        System.out.println( videos.getTitle());


        VideoOrder videoOrder = (VideoOrder) context.getBean("videoOrder");
        System.out.println( videoOrder.getVideo().getTitle());
      }

   }


-----------------------------------------------------------------------------------------------


Video 类   null构造方法被调用
CustomBeanPostProcessor1 postProcessBeforeInitialization beanName=video
CustomBeanPostProcessor2 postProcessBeforeInitialization beanName=video
Video类  init方法被调用
CustomBeanPostProcessor1 postProcessAfterInitialization beanName=video
CustomBeanPostProcessor2 postProcessAfterInitialization beanName=video
videoOrder空参构造函数被调用
//videoOrder类里没写init方法和destroy方法
VideoOrder类  setVideo方法被调用
CustomBeanPostProcessor1 postProcessBeforeInitialization beanName=videoOrder
CustomBeanPostProcessor2 postProcessBeforeInitialization beanName=videoOrder
CustomBeanPostProcessor1 postProcessAfterInitialization beanName=videoOrder
CustomBeanPostProcessor2 postProcessAfterInitialization beanName=videoOrder
微信教程
微信教程
Video类  destroy方法被调用
