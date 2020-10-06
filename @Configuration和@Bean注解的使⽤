首先定义一个包和类，包名为Config,类命为AppConfig。或者其他类名都行




/* spring的@Configuration和@Bean注解定义第三⽅bean  ，，第三方那些类，或者他的方法都可以传进来


@Configuration标注在类上，相当于把该类作为spring的xml配置⽂件中的，作⽤为：配置spring
容器(应⽤上下⽂)



@bean注解：⽤于告诉⽅法产⽣⼀个Bean对象，然后这个Bean对象交给Spring管理，Spring将会
将这个Bean对象放在⾃⼰的IOC容器中
注意点:SpringIOC容器管理⼀个或者多个bean，这些bean都需要在@Configuration注解下进⾏创
建
 */




@Configuration  //当前类是一个beans配置类
public class AppConfig {
      //@Bean将当前方法的返回值作为bean对象存入spring容器中,如果没有指定名称，默认采用方法名+第一个字母小写
       @Bean(value = "videoOrder",initMethod ="init",destroyMethod ="destry") //还可以引用订单类的初始化方法
       @Scope("singleton")//单例的，scope用于指定bean的作用范围
       public VideoOrder videoOrder(){
           return new VideoOrder();
       }

}




//这是相关实体类
//订单
public class VideoOrder implements Serializable {

    private int id;
    private String outTradeNo; //订单唯一标识
    private Video video;

    public VideoOrder() {
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



    public void init() {
        System.out.println("VideoOrder类  init方法被调用");
    }

    public  void  destry(){

        System.out.println("VideoOrder类  destroy方法被调用");
    }







