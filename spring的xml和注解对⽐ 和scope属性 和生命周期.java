讲解spring的xml和注解对⽐《上》
常⽤注解
bean定义
xml⽅式：
注解⽅式：@Component 通⽤组件 细分： @Controller (⽤于web层) @Service (⽤于
service层) @Repository (⽤于dao仓库层)


bean取名
xml⽅式：通过id或者name
注解⽅式：@Component("XXXX")



bean注⼊
xml⽅式：通过
注解⽅式：类型注⼊@Autowired 名称注⼊@Qualifier


bean⽣命周期
xml⽅式：init-method、destroy-method
注解⽅式：@PostConstruct初始化、@PreDestroy销毁



bean作⽤范围  
xml⽅式：scope属性
注解⽅式：@scope注解



*用于改变作用范围的注解: 他们的作用像在<bean>标签中使用scope属性实现的功能一样的
                              @scope
                           作用:用于指定bean的作用范围

                                取值: singleton :单例默认值，就是默认单例的  为true
         prototype :多例的，为false
         request   :作用域,web应用的请求范围
         session   :作用于web应用的会话范围
         global-session  :作用域集群环境的绘画范围,(全局绘画范围)当不是集群环境时,他就是session
         全局session，集群session



          用于bean对象生命周期的注解: 他们的作用就和在bean标签中使用init-method ="初始化" destroy-method="销毁"作用一样的
            @PreDestroy() 用于指定销毁方法
           @PostConstruct  用于指定初始化方法
        



@Component(value ="video")
@Scope("singleton") //prototype :多例的，为false 指定作用范围,多例的.  singleton:默认单例的
public class Video implements Serializable {

    private int id;
    private String title;


    @PostConstruct    //初始化方法
   public  void  init(){
       System.out.println("Video类  init方法被调用");
   }


   @PreDestroy    //销毁方法
   public  void  destroy(){

       System.out.println("Video类  destroy方法被调用");
   }




   public  Video(){

       //System.out.println("Video 类  空参构造方法被调用");
   }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        //System.out.println("video setId方法被调用");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
     //System.out.println("video setTitle方法被调用");
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
