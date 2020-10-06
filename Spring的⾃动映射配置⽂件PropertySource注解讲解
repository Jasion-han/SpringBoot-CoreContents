
@PropertySource，指定加载配置⽂件,配置⽂件映射到实体类
使⽤@Value映射到具体的java属性




@Configuration   //表明当前类是一个配置类
@PropertySource(value = {"classpath:jdbcConfig.properties"})//指定properties文件的位置,前面加classpath表示类路径
public class SpringConfiguration {

    @Value("${jdbc.driver}")
    private   String  dirver;
    @Value("${jdbc.url}")
    private   String  url;
    @Value("${jdbc.username}")
    private   String  username;
    @Value("${jdbc.password}")
    private   String  password;


    public String getDirver() {
        return dirver;
    }

    public void setDirver(String dirver) {
        this.dirver = dirver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SpringConfiguration{" +
                "dirver='" + dirver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}






//service

@Service(value ="videoService")
public class VideoServiceImpl implements VideoService {
    
    
           @Autowired
          private   SpringConfiguration   configuration;  //将配置类注入进来

           
           
    @Override
    public int save(Video video) {
        System.out.println("保存video");
        return 1;
    }

    @Override
    public Video findById(int id) {
         //获取下配置类的 url
         System.out.println(configuration.getUrl());

        System.out.println("根据id查找视频");
        return new Video();
    }



}



//测试类


public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext    context =  new AnnotationConfigApplicationContext();
                  //扫描这个包下的全部包和他的子类
               context.scan("net.xdclass");
               //里面完成初始化操作  核心方法
                context.refresh();
                 VideoService videoService = (VideoService) context.getBean("videoService");
                    videoService.findById(1);





