

//init-method="init" destroy-method="destroy"配置生周期

!-- 配置视频video对象-->
       <bean id="video" class="net.xdclass.spring.domain.Video" scope="singleton" init-method="init" destroy-method="destroy">
            <property name="id" value="6"/>
            <property name="title" value="微信教程"/>
     </bean>






//实体类写的方法
public class Video implements Serializable {

    private int id;
    private String title;

   public  void  init(){
       System.out.println("Video类  init方法被调用");
   }
   
   public  void  destroy(){

       System.out.println("Video类  destroy方法被调用");
   }


   public  Video(){

       System.out.println("Video 类   null构造方法被调用");
   }


    public int getId() {
        return id;
    }

    public void setId(int id) {
       
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    
        this.title = title;
    }


    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}










        ApplicationContext:他是一个接口,应用程序上下文。=====
        ClassPathXmlApplicationContext：类路径下面找xml,里面配置了多少bean，
        getBean就是从对容器里去找对应的那个Bean对象 */


        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                    testInitDestroy(context);
                 //tstInjectConllection(context);
                 //testInject(context);
                //testInherit(context);

         //回调函数，这样才能使destroy销毁方法调用
          ((ClassPathXmlApplicationContext) context).registerShutdownHook();

        }




     // spring⾥⾯bean的⽣命周期⾥⾯的init和destroy⽅法测试
    //spirngBean的声明周期，先是调用空参构造方法创建对象,然后在调用init方法， 最后才调用destroy方法销毁
    private  static void testInitDestroy( ApplicationContext context){

             Video  video = (Video) context.getBean("video");
             System.out.println(video.getTitle());
    }

