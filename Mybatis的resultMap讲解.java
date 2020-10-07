



<!-- id:别名，指定查询列的唯一表示     type:类型   resultMap:结果集映射-->
    <resultMap id="videoResultMap" type="Video">
        <!--
           column:数据库表的列名,
           property:实体类属性名称
        -->
          <!-- column:数据库表id主键映射property到实体类Video的id-->
           <id column="id" property="id" javaType="INTEGER"></id>

           <result column="video_title" property="title" jdbcType="VARCHAR"></result>
           <result column="summary" property="summary" jdbcType="VARCHAR"/>
           <result column="cover_img" property="coverImg" jdbcType="VARCHAR"/>

    </resultMap>

    <!-- 根据id 查询视频列表-->
      <select id="selectBaseFieldByIdWithResultMap" resultMap="videoResultMap">
             select id ,title as video_title ,summary , cover_img  from  video  where  id = #{video_Id,jdbcType=INTEGER}
      </select>
