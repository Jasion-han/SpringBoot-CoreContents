转SpringBoot2.x异步任务EnableAsync实战
简介：什么是异步任务，和使⽤SpringBoot2.x开发异步任务实战
什么是异步任务和使⽤场景：适⽤于处理log、发送邮件、短信……等
下单接⼝->查库存 1000
余额校验 1500
⻛控⽤户1000
启动类⾥⾯使⽤@EnableAsync注解开启功能，⾃动扫描

定义异步任务类并使⽤@Component标记组件被容器扫描,异步⽅法加上@Async



首先在主启动类里添加异步任务注解:

SpringBootApplication  /*标记这是springboot应用,里面包含多个注解*/
@ServletComponentScan  //Servlet3.0的注解进⾏配置⾃定义的过滤器,启动类⾥⾯增加 @ServletComponentScan，进⾏扫描
@EnableScheduling     //定时任务注解
@EnableAsync    // 启动类⾥⾯使⽤@EnableAsync注解开启功能,⾃动扫描 ......异步任务
public class DemoProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoProject2Application.class, args);
	}

}








新建异步任务业务类:

//异步任务业务类
@Component
@Async//这个注解表明下边的方法都是异步的，不用每个方法都加@Async
public class AsyncTask {


      public  void    task1(){

            try {
                  Thread.sleep(4000L);//线程
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }

            System.out.println("task1");
      }



      public  void    task2(){
            try {
                  Thread.sleep(4000L);//线程
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }
            System.out.println("task2");
      }



       @Async
      public  void    task3(){
             try {
                   Thread.sleep(4000L);//线程
             } catch (InterruptedException e) {
                   e.printStackTrace();
             }
            System.out.println("task3");
     }
}






       @Autowired
       private AsyncTask   asyncTask;  //将异步任务业务类注进来

//测试异步任务 响应时间
    @GetMapping("async")
    public  JsonData  testAsync(){
         long   begin   =  System.currentTimeMillis();//拿到开始时间
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        long  end  =  System.currentTimeMillis(); //拿到结束时间
        return  JsonData.buildSuccess( end - begin); //响应耗时
    }
