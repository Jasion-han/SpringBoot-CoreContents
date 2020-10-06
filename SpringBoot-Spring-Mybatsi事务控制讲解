
快速创建SpringBoot+Spring+Mybatsi项⽬
https://start.spring.io/
连接打通数据库


连接打通数据库
多表操作，通过@Transactional控制事务
启动类加注解 @EnableTransactionManagement
业务类 加 @Transactional
















//controller层


@RestController
@RequestMapping("api/v1/user")
public class UserController {

   /* springboot的事务默认是使用jdk的动态代理，即基于接口）)。在controller层中注入的Bean是实现类，因此就会报错。
    解决:将此注入Bean的方式改成了其接口
    假如你是在不想将注入的Bean改成接口方式，非得要用实现类，而且还不想再启动事务时配置proxyTargetClass=true，
    那么接下来让你知道什么叫厉害，有如下方法：
    在你Service层对应的实现类上配置@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)注解，
    表明此类上所有方法上的事务都是CGLib方式代理的，问题照样可以解决
    */

       @Autowired
       private UserService  userService; //这里必须注入的是service的接口，因为springboot的事务默认是使用jdk的动态代理，即基于接口

      @RequestMapping("save")
      public Object  save(){
          User  user =  new User();
                user.setName("小滴课堂");
                user.setPhone("13254");
               userService.save(user);
          return user;
      }
}





//service接口
public interface UserService {


    public int save(User user);

}






//serviceImpl实现类

@Service(value ="userService")
@Transactional    ////配置事务的属性，如果数据产生异常，数据将不会插入数据库.配置事务的重要性
public class UserServiceImpl implements UserService{

      @Autowired
       private UserMapper   userMapper;


    @Override
    public int save(User user) {
        userMapper.save(user);
          int i = 1/0;  //这里之所以这样写是为了测试出现异常，事务拦截数据插入数据库
        return 1;

    }
}








//dao层接口
@Repository
public interface UserMapper {

      @Insert("insert into  user(name,phone) values(#{name},#{phone})")
      public  int  save(User user);
}







//实体类domain
public class User {

    private int  id;
    private  String  name;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}










//主启动类
/*@MapperScan("Mapper文件所在的包")，@MapperScan 是扫描mapper类的注解，就不用在每个mapper类上加@MapperScan了*/

@SpringBootApplication     /*标记这是springboot应用,里面包含多个注解*/
@MapperScan("net.xdclass.demo.mapper")
@EnableTransactionManagement   /*开启spring对注解事务的支持*/
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}







//application.properties配置文件

spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver

spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xdclass?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT
spring.datasource.username=root
spring.datasource.password=123456
#使⽤阿⾥巴巴druid数据源，默认使⽤⾃带的
#spring.datasource.type =com.alibaba.druid.pool.DruidDataSource
#开启控制台打印sql
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl



