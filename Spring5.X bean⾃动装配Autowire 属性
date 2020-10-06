




<!--Spring5.X bean⾃动装配Autowire 属性-->

    <!-- 配置订单对象       byName: 根据bean的id名称，注⼊到对应的属性⾥⾯
           Spring⾃动注⼊
           使⽤元素的 autowire 属性为⼀个 bean 定义指定⾃动装配模式
           autowire设置值
           no：没开启
           byType：根据bean需要注⼊的类型，注⼊到对应的属性⾥⾯
           如果按照类型注⼊，存在2个以上bean的话会抛异常
           expected single matching bean but found 2
           constructor: 通过构造函数注⼊，需要这个类型的构造函数 -->




    <!--byName: 根据bean的id名称，注⼊到对应的属性⾥⾯,VideoOrder实体类里的Video属性的名称video和配置名称必须一致，-->
      <bean id="videoOrder" class="net.xdclass.spring.domain.VideoOrder" autowire="byName">



     <!--如果有两个同样的video配置类，使用类型注入会报错，因为他不知道注入哪个，这时候得使用bean的id名称注入-->

    <!--byType：根据bean需要注⼊的类型，注⼊到对应的属性⾥⾯-->
    <bean id="videoOrder" class="net.xdclass.spring.domain.VideoOrder" autowire="byType">
    <property name="id" value="1"/>
    <property name="outTradeNo" value="aagaga123"/>
     <!--   <property name="video" ref="video"/>--><!--不用这样写,可以使用Autowire 属性自动注入-->
    </bean>


  <!-- 配置视频video对象-->
       <bean id="video" class="net.xdclass.spring.domain.Video" scope="singleton" init-method="init" destroy-method="destroy">
            <property name="id" value="6"/>
            <property name="title" value="微信教程"/>
     </bean>







//订单实体类必须有Video属性


//订单实体类
public class VideoOrder implements Serializable{

    private   int  id;
    private  String  outTradeNo; //订单唯一标识
    private   Video   video ; //video实体类的属性，这是⾃动装配Autowire的前提，

    public  VideoOrder(){
      System.out.println("videoOrder空参构造函数被调用");
        }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Video getVideo() {
        return video;
    }



    public void setVideo(Video video1) {
         System.out.println("VideoOrder类  setVideo方法被调用");
        this.video = video1;
    }

