
<!--dao接口的全限定类名,可以映射sql语句,到对应的方法名称参数，返回类型，mbatis是使用接口动态代理-->
<mapper namespace="net.xdclass.online_class.dao.VideoMapper">

   <!--Mybatis的sql⽚段的使⽤
    你是否常⽤select * 去查询数据库？⼩项⽬没问题，⾼并发项⽬不推荐这样使⽤，查询性能低，应该选择需要的字段
    什么是sql⽚段?根据业务需要，⾃定制要查询的字段，并可以复⽤-->
  
      <sql id="base_video_field">
          id, title,summary,cover_img
      </sql>


   <!--dao层方法的名字getById,   resultType是返回值的类型，就是sql查询结果集的封装-->
    <select id="getById" resultType="Video">

                                                                <!--video_id使用了别名，查询方法上的参数必须使用别名-->
           select <include refid="base_video_field"/> from video where id = #{video_Id,jdbcType=INTEGER}

    </select>
