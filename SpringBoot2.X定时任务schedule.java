SpringBoot使⽤注解⽅式开启定时任务
启动类⾥⾯ @EnableScheduling开启定时任务，⾃动扫描
定时任务业务类 加注解 @Component被容器扫描
定时执⾏的⽅法加上注解 @Scheduled(fixedRate=2000) 定期执⾏⼀次


//这是主启动类
@SpringBootApplication  /*标记这是springboot应用,里面包含多个注解*/
@ServletComponentScan  //Servlet3.0的注解进⾏配置⾃定义的过滤器,启动类⾥⾯增加 @ServletComponentScan，进⾏扫描
@EnableScheduling     //定时任务注解
public class DemoProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoProject2Application.class, args);
	}

}



  //定时业务类
*
         fixedRate: 定时多久执⾏⼀次（上⼀次开始执⾏时间点后xx秒再次执⾏；）
         fixedDelay: 上⼀次执⾏结束时间点后xx秒再次执⾏
         cron 定时任务表达式
*/

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VideoOrderTimedTask {

         @Scheduled(fixedDelay =4000)   // fixedDelay: 上⼀次执⾏结束时间后,6秒后再次执⾏
       //@Scheduled(fixedRate =2000)//每两秒执行一次,  定时多久执⾏⼀次（上⼀次开始执⾏时间点后xx秒再次执⾏；）
       //@Scheduled(cron ="*/1 * * * * *")//每秒执行一次
       public  void  sun(){
             //定时统计交易额，这是模拟，正常从数据库中查询
             System.out.println(LocalDateTime.now()+"当前交易额"+Math.random());


           try {
               Thread.sleep(2000L);  //用线程测试间隔
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
}
