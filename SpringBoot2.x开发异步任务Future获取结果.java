SpringBootApplication  /*标记这是springboot应用,里面包含多个注解*/
@ServletComponentScan  //Servlet3.0的注解进⾏配置⾃定义的过滤器,启动类⾥⾯增加 @ServletComponentScan，进⾏扫描
@EnableScheduling     //定时任务注解
@EnableAsync    // 启动类⾥⾯使⽤@EnableAsync注解开启功能,⾃动扫描 ......异步任务 
public class DemoProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoProject2Application.class, args);
	}

}                   
               
                           主启动类
===================================================================================




/*⽤SpringBoot2.x开发异步任务Future获取结果
              定义异步任务类需要获取结果
      注意点：
      要把异步任务封装到类⾥⾯，不能直接写到Controller
      增加Future 返回结果 AsyncResult("task执⾏完成");
      如果需要拿到结果 需要判断全部的 task.isDone()*/

      以后调用远程接口，使用异步做,需要返回一定的类型，可以这样做，new 一个 AsyncResult<类型>告诉它返回一个类型
      用什么类型接收
      AsyncResult:用于包装异步任务的结果
      Future:并发包


//异步任务业务类
@Component
@Async//下边的方法都是异步的
public class AsyncTask {



      public Future<String> task4(){
            try {
                  Thread.sleep(4000L);//线程
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }
            System.out.println("task4");

            return  new AsyncResult<String>("任务4");  //声明返回一个类型
      }




      public Future<String>  task5(){
            try {
                  Thread.sleep(4000L);//线程
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }
            System.out.println("task5");
            return new AsyncResult<String>("任务5");

      }
}





==================================================================================
                    测试类
@RestController
@RequestMapping("api/v1/test")
public class TestController {


      
       @Autowired
       private AsyncTask   asyncTask;  //将异步任务业务类注进来


       /*⽤SpringBoot2.x开发异步任务Future获取结果
              定义异步任务类需要获取结果
      注意点：
      要把异步任务封装到类⾥⾯，不能直接写到Controller
      增加Future 返回结果 AsyncResult("task执⾏完成");
      如果需要拿到结果 需要判断全部的 task.isDone()*/


     @GetMapping("asyncs")
     public  JsonData  testAsync2(){
         long   begin   =  System.currentTimeMillis();//拿到开始时间
        Future<String> task4 = asyncTask.task4();  //返回一个Future
        Future<String> task5 = asyncTask.task5();
        for(;;){
            if(task4.isDone() && task5.isDone()){ //死循环判断task4和task5是否完成
                try {
                    /*
                     异步请求，并行、拿到结果取耗时时间大的那个，并发比串行执行效率高，
                     串行拿到结果后，还得等另一个结果，耗时比较长
                               
                     */

                    String task4Result = task4.get(); //拿到task4结果
                    System.out.println(task4Result);

                    String task5Result = task5.get();//拿到task5结果
                    System.out.println(task5Result);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }finally {
                    break;  //跳出循环
                }
            }
        }
         long  end  =  System.currentTimeMillis(); //拿到结束时间
         return  JsonData.buildSuccess( end - begin); //响应耗时
     }

}


#在高并发模式下，尽量使用并行
