  resources配置文件下的mapper文件夹下的VideoMapper.xml文件
  
  
  
  <!-- 左边是数据库表的名称 =========   #{}:这里面是实体类对应的属性-->

        <!--案例实战MyBatis3.X玩转更新=====-->
       <update id="updateVideo" parameterType="net.xdclass.online_class.domain.Video">
             update  video set  title = #{title,jdbcType=VARCHAR},
                                 summary=#{summary,jdbcType=VARCHAR},
                                 cover_img=#{coverImg,jdbcType=VARCHAR},
                                 price=#{price,jdbcType=INTEGER},
                                 create_time=#{createTime,jdbcType=TIMESTAMP},
                                 point=#{point,jdbcType=DOUBLE}  where id =#{id}
       </update>






        <!--案例实战MyBatis3.X玩转更新====选择性更新某个属性-->
        <!--Mybatis 动态字段更新 if test 标签使⽤
        可以选择性更新⾮空字段
        if test标签介绍
        if 标签可以通过判断传⼊的值来确定查询条件，test 指定⼀个OGNL表达式-->

    <update id="updateVideo2" parameterType="net.xdclass.online_class.domain.Video">
          <!--把set加进去，下边就不用写set了,prefixOverrides=","把最后面的逗号去掉-->
        update  video
          <trim prefix="set" prefixOverrides=",">

        <if test="title!=null"> title = #{title,jdbcType=VARCHAR},</if>
        <if test="summary!=null"> summary=#{summary,jdbcType=VARCHAR},</if>
        <if test="coverImg!=null"> cover_img=#{coverImg,jdbcType=VARCHAR},</if>
        <if test="price!=0"> price=#{price,jdbcType=INTEGER},</if>
        <if test="createTime!=null"> create_time=#{createTime,jdbcType=TIMESTAMP},</if>

         /*一定要看实体类里面是基本数据类型还是包装类型，只有包装类型对象才有!=null判断*/
        <if test="point!=null"> point=#{point,jdbcType=DOUBLE}</if>

          </trim>
        where
        id = #{id}
    </update>







//这是Dao层方法

      /*
       更新视频
      */
     public  void  updateVideo(Video  video)


     /*选择性更新某个属性*/
     int  updateVideo2(Video  video);







//测试类，测试方法

           /*
            更新视频
            */
           @Test
            public  void  testUpdatVideo(){

                Video   video = new  Video();
                video.setId(56);
                video.setTitle("微信小程序开发");
                video.setSummary("www.sss.com");
                video.setCoverImg("https://xdvideooo-filesss.aliyuncsss.com/");
                video.setPrice(77777);
                video.setCreateTime(new Date());
                video.setPoint(9.3);

                videoMapper.updateVideo(video);
            }



         /*选择性更新某几个属性*/
         @Test
          public void    updateVideo2(){

              Video   video = new  Video();
              video.setId(54);
              video.setTitle("Msql数据库");
              video.setCoverImg("https://xdvideo-file.aliyuncs.com/");


              videoMapper.updateVideo2(video);
          }

