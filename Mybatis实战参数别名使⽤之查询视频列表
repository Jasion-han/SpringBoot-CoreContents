<!--resources下的config文件下的各个文件配置参数-->

//jdbcConfig.properties连接数据库文件的配置:

jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/xdclass?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false
jdbc.username=root
jdbc.password=123456




//log4j.properties文件的配置:

log4j.rootLogger=ERROR, stdout
log4j.logger.net.xdclass=DEBUG  调试阶段使用
#TRACE内容会打印更多
#log4j.logger.net.xdclass=TRACE
#细化到打印某个mapper  ，也可以打印别的dao层
#log4j.logger.net.xdclass.online_class.dao.VideoMapper=TRACE

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n


#==============================下面是日志打印方法执行的详细信息和方法名等=================================================================

# Set root category priority to INFO and its only appender to CONSOLE.
#log4j.rootCategory=INFO, CONSOLE            debug   info   warn error fatal
#log4j.rootCategory=debug, CONSOLE, LOGFILE

# Set the enterprise logger category to FATAL and its only appender to CONSOLE.
#log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
#log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
#log4j.appender.LOGFILE=org.apache.log4j.FileAppender
#log4j.appender.LOGFILE.File=${WORKDIR}/logs/app.log
#log4j.appender.LOGFILE.Append=true
#log4j.appender.LOGFILE.layout=org.apache.log4j.PatternLayout
#log4j.appender.LOGFILE.layout.ConversionPattern=%d{ISO8601} %-6r [%15.15t] %-5p %30.30c %x - %m\n


========================================================================================================


//SqlMapConfig.xml配置文件信息:

<?xml version="1.0" encoding="UTF-8"?>
<!-- 导入约束 -->
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

   <!--1、配置properties可在标签内部配置连接数据库信息，也可以通过属性引用外部配置文件信息-->
    <properties resource="jdbcConfig.properties"></properties>

 
    <!--下划线⾃动映射成驼峰字段,实体类里的驼峰属性字段匹配数据库里的下划线字段，固定写法，必须配置在typeALIAliases标签上面-->
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>





    <!--用于指定配置别名的包,当指定之后，该包下的实体类都会注册别名，并且类名就是别名不在区分大小写-->
     <typeAliases>
         <package name="net.xdclass.online_class.domain"></package>
     </typeAliases>

    <!--配置数据源-->
    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <!--配置文件方式，如果使用了resources下mapper配置文件方式，这里必须写我们放置查询的配置文件路径-->
     <mapper resource="mapper/VideoMapper.xml"/>

    </mappers>
</configuration>


=================================================================================================


//mapper文件下的配置文件信息

//VideoMapper.xml查询配置文件信息:

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--dao接口的全限定类名,可以映射sql语句,到对应的方法名称参数，返回类型，mbatis是使用接口动态代理-->
<mapper namespace="net.xdclass.online_class.dao.VideoMapper">
   <!--dao层方法的名字getById,   resultType是返回值的类型，就是实体类全限定类名。就是sql查询结果集的封装-->
    <select id="getById" resultType="net.xdclass.online_class.domain.Video">

           select * from video where id = #{video_id}
    </select>



       <!--xml方式获取整个视频列表信息,resultType:返回值类型-->
       <select id="selectListFindVideo" resultType="net.xdclass.online_class.domain.Video">
            select * from video
       </select>

      <!--当使用模糊查询或者使用多参数查询时候，dao层查询方法参数必须设置成取别名@Param,才能识别,这边必须使用%拼接-->
       <select id="selectByPointAndTitleLike" resultType="net.xdclass.online_class.domain.Video">
           select * from video where  point=#{point} and title like concat('%',#{title},'%')
       </select>
</mapper>



===============================================================================================================


//dao层类文件信息
public interface VideoMapper {

     /*
        给id取的别名@Param("video_id")，对应查询那的id.
          根据id查询某个视频信息
      */
    public Video   getById(@Param("video_id") int VideoId);

     /*
        通过注解方式获取整个视频列表信息
         查询所有视频信息
      */

     @Select("select * from  video")
      public List<Video>   selectList();


     /*
      我们在通过xml配置文件方式获取整个视频列表信息
      */
       public  List<Video>  selectListFindVideo();


      /*
        根据评分point，和标题title模糊查询
        当使用模糊查询或者使用多参数查询时候，参数必须设置成取别名@Param   才能识别
       */
       public   List<Video> selectByPointAndTitleLike(@Param("point") double point, @Param("title") String title);


}


==========================================================================================



/*
 视频实体类
 */
import java.util.Date;

public class Video {

       private   int  id;
       private  String  title;  //视频标题
       private  String  summary;  //  视频详情
       private  String  coverImg;  //封面图
       private  int    price;  //  价格
       private  Date   createTime;   //创建时间
       private   double  point;    //默认8.7，最高10分


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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", point=" + point +
                '}';
    }
}


====================================================================================================


//测试类信息

public class SqlSessionDemo {
    InputStream in;
    SqlSession sqlSession;
    VideoMapper   videoMapper;

    @Before
    public void init() throws Exception {

        //1.读取配置文件，生成字节输入流
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory工厂
        SqlSessionFactory  factory = new SqlSessionFactoryBuilder().build(in);
         //3.获取SqlSession对象
        sqlSession  = factory.openSession();

        //4.获取dao的代理对象
       videoMapper  =   sqlSession.getMapper(VideoMapper.class);//通过反射拿到动态代理对象
    }

       @After
       public  void   destroy() throws Exception {
        //提交事务
          sqlSession.commit();
          //释放资源
          sqlSession.close();
          in.close();

      }

/*通过配置文件方式,根据id查询一个视频*/
   @Test
      public  void  testFindById(){

           Video    video  = videoMapper.getById(30);
         System.out.println(video);
      }




  /*通过注解方式查询所有的视频列表信息*/
      @Test
      public  void   testFindAllVideo(){

          List<Video>   list = videoMapper.selectList();
           System.out.println(list.toString());
      }






      /*
        通过配置文件方式，查询所有的视频列表信息
      */
       @Test
       public  void  testFindAllVidos(){
           List<Video>   lists = videoMapper.selectListFindVideo();
           System.out.println(lists);

       }



         /*
           多参数模糊查询
          */
         @Test
         public   void  testByPointAndTitleLike(){

             List<Video> video = videoMapper.selectByPointAndTitleLike(9.1,"20年");
                System.out.println(video);
         }
}

===========================================================================================================

pom文件信息
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>net.xdclass</groupId>
    <artifactId>xd-mybatis</artifactId>
    <version>1.0-SNAPSHOT</version>

      <dependencies>
          <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis</artifactId>
              <version>3.5.4</version>
          </dependency>
          <!--myssql驱动-->
          <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>8.0.19</version>
          </dependency>
         <!--日志依赖-->
          <dependency>
              <groupId>org.slf4j</groupId>
              <artifactId>slf4j-log4j12</artifactId>
              <version>1.7.5</version>
          </dependency>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
          </dependency>


      </dependencies>
</project>
