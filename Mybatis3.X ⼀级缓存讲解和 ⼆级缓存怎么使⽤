*Mybatis中的一级缓存:
            一级缓存指的是Mybatis中的SqlSession对象的缓存,
            当我们执行查询后，查询结果会存入SqlSession对象为我们提供的一块区域中，该区域的结构是一个Map
            当我们再次查询同样的数据，Mybatis会先去SqlSession中查询是否有数据，如果有就拿出来用
            当SqlSession对象消失时，Mabais一级缓存也会随之消失


二级缓存:它指的是Mybatis中,SqlSessionFactory对象的缓存.  由同一个SqlsessionFactory对象创建的
    SqlSession共享其缓存.



⼆级缓存是namespace级别的，多个SqlSession去操作同⼀个namespace下的
Mapper的sql语句，多个SqlSession可以共⽤⼆级缓存,如果两个mapper的namespace相
同，（即使是两个mapper，那么这两个mapper中执⾏sql查询到的数据也将存在相同的⼆级
缓存区域中，但是最后是每个Mapper单独的名称空间）
基于PerpetualCache 的 HashMap本地缓存，可⾃定义存储源，如 Ehcache/Redis

默认是没有开启⼆级缓存
操作流程：第⼀次调⽤某个namespace下的SQL去查询信息，查询到的信息会存放该
mapper对应的⼆级缓存区域。 第⼆次调⽤同个namespace下的mapper映射⽂件中，相同
的sql去查询信息，会去对应的⼆级缓存内取结果
失效策略：执⾏同个namespace下的mapepr映射⽂件中增删改sql，并执⾏了commit操作,会清
空该⼆级缓存
注意：实现⼆级缓存的时候，MyBatis建议返回的POJO是可序列化的， 也就是建议实现
Serializable接⼝
缓存淘汰策略：会使⽤默认的 LRU 算法来收回（最近最少使⽤的）
如何开启某个⼆级缓存 mapper.xml⾥⾯配置



例：
  
 resources/config/sqlMapConfig.xml文件里配置：
        
      <settings>

        <!--下划线⾃动映射驼峰字段,实体类里的驼峰属性字段匹配数据库里的下划线字段，-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>

        <!--二级缓存的全局配置
        这个配置使全局的映射器(⼆级缓存)启⽤或禁⽤缓存，全局总开关，这⾥关闭，mapper中开启了也没⽤-->
        <setting name="cacheEnabled" value="true" />

      </settings>





      resources/mapper/VideoMapper.xml 配置文件里开启mapper的namespace下的⼆级缓存            

       <!--  eviction:代表的是缓存回收策略，常⻅下⾯两种。

       (1) LRU,最近最少使⽤的，⼀处最⻓时间不⽤的对象
       (2) FIFO,先进先出，按对象进⼊缓存的顺序来移除他们
           flushInterval:刷新间隔时间，单位为毫秒，这⾥配置的是100秒刷新，如果不配置它，当SQL被执⾏的时候才会去刷新缓存。
         
          readOnly:只读，缓存数据只能读取⽽不能修改，默认值是false
              size:引⽤数⽬，代表缓存最多可以存储多少个对象，设置过⼤会导致内存溢出  -->

         <cache eviction="LRU" flushInterval="100000" readOnly="true" size="1024"/>
             


        例子



     //测试二级缓存
     
    public class SqlSessionCachDemo {
    InputStream in;
    SqlSessionFactory factory;

    @Before
    public void init() throws Exception {

        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory工厂
        factory = new SqlSessionFactoryBuilder().build(in);


    }

     @After
     public  void  destroy() throws IOException {

           in.close();
     }


 /*Mybatis中的一级缓存和二级缓存
            一级缓存指的是Mybatis中的SqlSession对象的缓存,
            当我们执行查询后，查询结果会存入SqlSession对象为我们提供的一块区域中，该区域的结构是一个Map
            当我们再次查询同样的数据，Mybatis会先去SqlSession中查询是否有数据，如果有就拿出来用

            当SqlSession对象消失时，Mabais一级缓存也会随之消失



    二级缓存:它指的是Mybatis中,SqlSessionFactory对象的缓存.  由同一个SqlsessionFactory对象创建的
    SqlSession共享其缓存.


      二级缓存使用步骤:   1  让Mybatis框架支持二级缓存(在SqlMapConfig.xml中配置)
                         2  让当前的映射文件支持二级缓存(在VideoMapper.xml中配置)
                         3  优先查询⼆级缓存-》查询⼀级缓存-》数据库  */


       //测试sqlSession二级缓存
         @Test
         public   void  testFirstLevelCache(){
             //获取Sqlsession对象
             SqlSession sqlSession1   =  factory.openSession();
                  //再次重新获取接口的动态代理对象
                 VideoMapper   videoMapper  = sqlSession1.getMapper(VideoMapper.class);
                              Video   video1  = videoMapper.getById(30);
                              System.out.println(video1.toString());
                              sqlSession1.close();




                        SqlSession   sqlSession2  =  factory.openSession();
                             //重新获取接口的动态代理对象
                 VideoMapper   videoMapper2   =    sqlSession2.getMapper(VideoMapper.class);
                               Video   video2  =      videoMapper2.getById(30);
                               System.out.println(video2.toString());
                               sqlSession2.close();
                              System.out.println(video1==video2);
         }



}




<!--userCache是在方法上配置二级缓存，=false 说明这个方法不使用二级缓存，   =true 说明使用二级缓存-->
   <!--dao层方法的名字getById,   resultType是返回值的类型，就是sql查询结果集的封装-->
    <select id="getById" resultType="Video" useCache="true">
