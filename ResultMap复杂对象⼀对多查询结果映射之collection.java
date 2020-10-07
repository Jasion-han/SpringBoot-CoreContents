<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--ResultMap复杂对象⼀对⼀查询结果映射之association-->

<!--dao接口的全限定类名,可以映射sql语句,到对应的方法名称参数，返回类型，mbatis是使用接口动态代理-->
<mapper namespace="net.xdclass.online_class.dao.VideoOrderMapper">

    <!-- id:别名，指定查询列的唯一表示     type:类型,  resultMap:结果集映射-->
          <!--  column:   数据库表的列名,
              property:   实体类属性名 -->

           <resultMap id="videoOrderResultMap" type="VideoOrder">
                 <id column="id" property="id"/>

                 <result column="out_trade_no" property="outTradeNo"/>
                 <result column="state" property="state"/>
                 <result column="create_time" property="createTime"/>
                 <result column="total_fee" property="totalFee"/>
                 <result column="video_id" property="videoId"/>
                 <result column="video_title" property="videoTitle"/>
                 <result column="video_img" property="videoImg"/>
                  <result column="user_id" property="userId"/>


  <!--配置属性1对1 ，一个订单对应一个用户
                    javaType类型:对应实体类User对象
                    column:数据库表的列名,

               property:对应VideoOrder订单实体类里面的user属性名称-->
                 <association property="user" javaType="User">

                          <id column="id" property="id"></id>

                          <result column="name" property="name"/>
                          <result column="pwd" property="pwd"/>
                          <result column="head_img" property="headImg"/>
                          <result column="phone" property="phone"/>
                          <result column="create_time" property="createTime"/>

                    </association>
           </resultMap>
    

       <!--1对1管理查询订单，订单内部包含用户属性，也就是说查询订单时候，相应的把对应的用户信息查询出来-->
       <select id="queryVideoOrderList" resultMap="videoOrderResultMap">
               select
                  vo.id,
                  vo.out_trade_no,
                  vo.state,
                  vo.create_time,
                  vo.total_fee,
                  vo.video_id,
                  vo.video_title,
                  vo.video_img,
                  vo.user_id,
                  u.name,
                  u.pwd,
                  u.head_img,
                  u.phone,
                  u.create_time
               from  video_order  vo  LEFT JOIN  user u  on vo.user_id = u.id;
       </select>







        <!-- ResultMap复杂对象⼀对多查询，⽐如一个用户 有多个订单
        查询用户，附带查询器包含着的订单信息，一个用户有多个订单 ，先查用户，在查订单
        -->
         <resultMap id="UserOrderResultMap" type="User">
             <id column="id" property="id"></id>

             <result column="name" property="name"/>
             <result column="pwd" property="pwd"/>
             <result column="head_img" property="headImg"/>
             <result column="phone" property="phone"/>
             <result column="create_time" property="createTime"/>

       <!--查询用户信息时候，相应的把用户的订单信息查询出来
          property="videoOrderList：对应User实体类里的订单集合属性名称
          ofType="VideoOrder":指定哪种类型，集合里面的实体类对象，在这是就是订单对象-->

        <collection property="videoOrderList" ofType="VideoOrder">
         <!--订单数据库表中的id,修改成别名order_id,映射订单VieoOrder实体类id-->
            <id column="order_id" property="id"/>

            <result column="out_trade_no" property="outTradeNo"/>
            <result column="state" property="state"/>
            <result column="create_time" property="createTime"/>
            <result column="total_fee" property="totalFee"/>
            <result column="video_id" property="videoId"/>
            <result column="video_title" property="videoTitle"/>
            <result column="video_img" property="videoImg"/>
            <result column="user_id" property="userId"/>

        </collection>
   </resultMap>


     <!-- vo.id as order_id：修改成别名，区分用户id  和订单id
     ResultMap复杂对象⼀对多查询
         查询用户，附带查询器包含着的订单信息 一个用户有多个订单 ，先查用户，在查订单
     -->
     <select id="queryUserOrder" resultMap="UserOrderResultMap">

           select
                  u.id,
                  u.name,
                  u.pwd,
                  u.head_img,
                  u.phone,
                  u.create_time,
                  vo.id as order_id,
                  vo.out_trade_no,
                  vo.state,
                  vo.create_time,
                  vo.total_fee,
                  vo.video_id,
                  vo.video_title,
                  vo.video_img

            from user u  LEFT JOIN  video_order  vo  on  u.id = vo.user_id;
     </select>
</mapper>











//dao层方法

/*
        ResultMap复杂对象⼀对⼀查询结果映射之association
         查询订单 和它附带的用户信息, 1个订单对应1个用户   ，先查订单 ，在查用户
     */
      public List<VideoOrder> queryVideoOrderList();




        /*
         ResultMap复杂对象⼀对多查询
         查询用户，附带查询器包含着的订单信息，一个用户有多个订单 ，先查用户，在查订单
        */

      public  List<User>  queryUserOrder();
}




//测试类 


/*
        ResultMap复杂对象⼀对⼀查询结果映射之association
         查询订单 和它附带的用户信息, 1个订单对应1个用户   ，先查订单 ，在查用户
     */
      public List<VideoOrder> queryVideoOrderList();




        /*
         ResultMap复杂对象⼀对多查询
         查询用户，附带查询器包含着的订单信息，一个用户有多个订单 ，先查用户，在查订单
        */

      public  List<User>  queryUserOrder();
}
