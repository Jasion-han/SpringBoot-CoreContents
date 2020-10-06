声明切⾯类 @Aspect(切⾯): 通常是⼀个类，⾥⾯可以定义切⼊点和通知


//1、案例实战之基于Spring注解配置AOP⾯向切⾯编程  ,TimHandler类是基于xml文件配置的，跟注解配置不一样

//2、AOP案例实战之环绕通知统计接⼝耗时




//1、当前类是一个配置类

//案例实战之基于Spring注解配置AOP⾯向切⾯编程
@Configuration  //当前类是一个beans配置类
@ComponentScan("net.xdclass") //用于通过注解指定spring在创建容器时所要扫描的包,属性是value
@EnableAspectJAutoProxy ////配置spring开启aop注解配置的支持
public class AnnotationAppConfig {

}







//2、logger类是切面类,基于Spring注解配置AOP⾯向切⾯编程        

@Component(value ="logger")  //该类唯一标识
@Aspect//表示当前类是一个切面类，里面可以定义切入点和通知
public class Logger {


    //配置切入点表达式,实际开发，用环绕通知，
    @Pointcut(value ="execution(* net.xdclass.spring.service.impl.*.*(..) )")
    public  void   pt1(){


    }


        /**
         *前置通知 ,引入切入点表达式
         */
    // @Before("pt1()")
    public  void  beforPrintLog(){
        System.out.println("前置通知logger类中的beforPrintLog方法开始记录日志了");
    }


      /**
       * 后置通知
       */
    //@AfterReturning("pt1()")
    public  void  afetrReturningPrintlog(){
           System.out.println("后置通知logger类中的afetrReturningPrintlog方法开始记录日志了");
    }


    /**
     * 异常通知
     */
    // @AfterThrowing("pt1()")
    public  void   afterThrowingPrintlog(){

           System.out.println("异常通知logger类中的afterThrowingPrintlog方法开始记录日志了");
    }


    /**
     * 最终通知
     */
   // @After("pt1()")
     public  void   afterPrintlog(){

         System.out.println("最终通知logger类中的afterPrintlog方法开始记录日志了");
     }




    /**
     * 环绕通知  案例1
     *
     环绕通知获取⽬标⽅法和参数
     */
    @Around("pt1()")
     public   Object  aroundJoinPoint(JoinPoint  joinPoint){

            Object target = joinPoint.getTarget().getClass().getName();
            System.out.println("调用者:"+target);
            //目标方法签名
            System.out.println("调用方法是哪个:"+joinPoint.getSignature());
            Object[]  args = joinPoint.getArgs();
            System.out.println("参数:"+args[0]);
         return null;
     }







    /**
     * 环绕通知    案例2 环绕通知获取⽬标⽅法和参数
     */
     //@Around("pt1()")
       public  Object   aroundPrintlog(ProceedingJoinPoint  proceeding){


           Object  rtvalues =null;

           try {
               Object[]  args =  proceeding.getArgs();//proced.gtArgs得到运行时所需的参数
               System.out.println("参数:"+args[0]);

               rtvalues =proceeding.proceed(args);
               System.out.println("后置通知:"+ rtvalues);  //获取参数的value
               return  rtvalues;

           } catch (Throwable throwable) {
                //System.out.println("异常通知");
                throw  new  RuntimeException(throwable);
           }finally {
               //System.out.println("最终通知");
           }

       }


    /**
     * AOP案例实战之环绕通知统计接⼝耗时
     通过AOP的环绕通知统计⽅法调⽤耗时


     案例3   配置环绕通知：打印⽅法请求时间总耗时
     */

      // @Around("pt1()")
       public  void  around(JoinPoint  joinPoint){

           try {

               long  start  =  System.currentTimeMillis();
               System.out.println("环绕通知  环绕前==========");
               ((ProceedingJoinPoint)joinPoint).proceed();

              long   end  =  System.currentTimeMillis();
              System.out.println("环绕通知  环绕后============");

              System.out.println("调用方法总耗时  time="+(end - start)); //总耗时:结束时间减去开始时间
           } catch (Throwable throwable) {
                throw new RuntimeException(throwable);  //异常通知
           }finally {
               System.out.println("最终通知=============");

           }
       }
   }









//serviceImpl类，调用这个方法，打印日志
@Override
    public Video findById(int id) {
         //获取配置类的 url
        //System.out.println(configuration.getUrl());

        System.out.println("根据id查找视频");

        try {
            Thread.sleep(2000L);  //假如我们在这添加一个睡眠2秒耗时，logger类计算处总耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Video();
    }

  }










//测试类

public class App {

    public static void main(String[] args) {

        //Spring注解配置AOP的配置类传过来，AnnotationAppConfig

        AnnotationConfigApplicationContext    context =  new AnnotationConfigApplicationContext(AnnotationAppConfig.class);
//                  扫描这个包下的全部包和他的子类
//               context.scan("net.xdclass");
//               里面完成初始化操作  核心方法         这个三段可以删除，不使用了，因为我们在注解下已经配置过了
//                context.refresh();


                 VideoService videoService = (VideoService) context.getBean("videoService");
                    videoService.findById(1);


