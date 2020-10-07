使⽤set⽅法注⼊

<bean id="video" class="net.xdclass.sp.domain.Video" scope="singleton">

 <property name="id" value="9"/>
 <property name="title" value="Spring 5.X课程" />

</bean>







//带参构造函数注入

1、实体类写法，写一个有参构造方法，然后重写无参构造方法
public class Video implements Serializable {

    private int id;
    private String title;

    //带参构造方法注入，实体类Video必须加入有参构造方法.如果使用平常那种注入，必须保留空参构造方法
    //注意: 类的构造函数重写的时候，⼀定要保留空构造函数！！！，因为重写了构造方法，默认空参就不保留了


    //空参构造方法
    public Video() {

    }

   //有参构造方法
    public  Video(String title){
        System.out.println("Video有参构造方法被调用");
        this.title =title;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("video setId方法被调用");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
     System.out.println("video setTitle方法被调用");
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





//配置文件写法

 <!--有参构造方法注入，实体类Video必须加入有参构造方法-->
  <bean id="video" class="net.xdclass.spring.domain.Video">
    <constructor-arg name="title" value="面试课程第一季"></constructor-arg>
  </bean>




    //注入测试
   private static  void  tstInject( ApplicationContext context){

       Video   video   = (Video) context.getBean("video");//这个video就是配置文件的bean标签id
          System.out.println("有参构造方法"+"==="+video.getTitle());





///POJO类型注⼊(property 没有使⽤value属性，⽽是使⽤了ref属性)

<!-- 配置订单对象-->
  <bean id="videoOrder" class="net.xdclass.spring.domain.VideoOrder">
    <property name="id" value="1"/>
    <property name="outTradeNo" value="aagaga123"/>
    <!--注入视频video对象是用set方法注入-->
    <property name="video" ref="video"/> //ref是引入有参构造配置标签bean的id=video ,将Video注入
  </bean>



   <!--有参构造方法注入，实体类Video必须加入有参构造方法-->
  <bean id="video" class="net.xdclass.spring.domain.Video">
    <constructor-arg name="title" value="面试课程第一季"></constructor-arg>
  </bean>



//


