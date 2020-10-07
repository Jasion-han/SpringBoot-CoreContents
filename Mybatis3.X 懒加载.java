//sqlMapConfig.xml配置文件中配置

 <settings>
        <!--下划线⾃动映射驼峰字段,实体类里的驼峰属性字段匹配数据库里的下划线字段，-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>

        <!--二级缓存的全局配置
        这个配置使全局的映射器(⼆级缓存)启⽤或禁⽤缓存，全局总开关，这⾥关闭，mapper中开启了也没⽤-->
        <setting name="cacheEnabled" value="true" />

        <!--延迟加载总开关-->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!--将aggressiveLazyLoading设置为false表示按需加载，默认为true-->
        <setting name="aggressiveLazyLoading" value="false"/>

    </settings>







resources/config/mapper/VideoOrderLazyLoadingMapper.xml 文件里配置:



<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

 <!--dao接口的全限定类名,可以映射sql语句,到对应的方法名称参数，返回类型，mbatis是使用接口动态代理-->
<mapper namespace="net.xdclass.online_class.dao.VideoOrderMapper">

    <!-- id:别名，指定查询列的唯一表示     type:类型，对应订单实体类对象, -->
    <!--  column:   数据库表的列名.......property:   实体类属性名 -->
    <resultMap id="videoOrderResultMapLasy" type="VideoOrder">
        <id column="order_id" property="id"/>

        <result column="out_trade_no" property="outTradeNo"/>
        <result column="state" property="state"/>
        <result column="create_time" property="createTime"/>
        <result column="total_fee" property="totalFee"/>
        <result column="video_id" property="videoId"/>
        <result column="video_title" property="videoTitle"/>
        <result column="video_img" property="videoImg"/>
        <result column="user_id" property="userId"/>


    <!--配置属性1对1 ，一个订单对应一个用户
           javaType类型:对应VideoOrder订单实体类里面的user类型
          select属性指定的内容，查询用户的唯一标识,(延迟查询用户信息的那个id方法名) 指定延迟加载需要执⾏的statement id
           property:对应VideoOrder订单实体类里面的user属性名称
          column： 和select查询关联的字段  -->
         <association property="user" column="user_id" javaType="User" select="findUserByUserId"/>

        </resultMap>

       <!--查询账户，同时延迟查询其用户信息,先查订单，在根据需要查用户信息
           一对一 , 一个订单对应一个用户-->
       <select id="queryVideoOrderListLazy" resultMap="videoOrderResultMapLasy">

         select

                  vo.id as order_id,
                  vo.out_trade_no,
                  vo.state,
                  vo.create_time,
                  vo.total_fee,
                  vo.video_id,
                  vo.video_title,
                  vo.video_img,
                  vo.user_id
          from  video_order vo

       </select>

        <select id="findUserByUserId" resultType="User">
            select * from  user where  id =#{id}
        </select>








    //测试方法

  /*
           Mybatis3.X 懒加载 ,就是延迟查询 ，查询订单时，同时延迟查询其用户信息
           查询订单时候，相应的延迟查询其用户信息，先查订单，在根据自己的需要查用户信息，如果有需要就查用户，
           一个订单对应一个用户
         */
         @Test
         public  void   testLasyLoadingVideoOrder(){

             List<VideoOrder>  video = orderMapper.queryVideoOrderListLazy();
             for(VideoOrder videoOrders: video){

                 System.out.println("---------每个订单信息----------------------");
                 System.out.println(videoOrders); //获取订单信息
                 System.out.println(videoOrders.getUser());//获取用户信息
                 //System.out.println(videoOrders.getUser().getName());//获取用户信息和用户名称
             }
         }
