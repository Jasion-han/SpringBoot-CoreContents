
VideoMapper.xml 配置类

<!--* 根据id查询视频详情信息，包含章，集
    * @param videoId
    * @return
    * 需要使用MyBatis关联复杂查询-->

    <resultMap id="videoDetailResultMap" type="Video">
        <!-- id:别名，指定查询列的唯一表示     type:类型，对应订单实体类对象,  resultMap:结果集映射-->
        <!--  column: 数据库表的列名,        property:   实体类属性名 -->
         <id column="id" property="id" jdbcType="INTEGER"/>

         <result column="title" property="title" jdbcType="VARCHAR"/>
         <result column="summary" property="summary" jdbcType="VARCHAR"/>
         <result column="cover_img" property="coverImg" jdbcType="VARCHAR"/>
         <result column="price" property="price" jdbcType="INTEGER"/>
         <result column="create_time" property="createTime" jdbcType="TIMESTAMP"/>
         <result column="point" property="point" jdbcType="DOUBLE"/>

        <!--查询视频信息时候，相应的把章信息查询出来
          property=""：对应实体类里的章集合的名称
          ofType="":指定哪种类型，list集合里面的实体类对象-->
        <collection property="chapterList" ofType="Chapter">
                <!--把id改成chapter_id,要不和上面重叠，没法映射，查询语句也得改，取个别名-->
                 <id column="chapter_id" property="id" jdbcType="INTEGER"/>

                 <!--<result column="video_id" property=" videoId" jdbcType="INTEGER"/>-->
                 <result column="chapter_title" property="title" jdbcType="VARCHAR"/>
                 <result column="chapter_ordered" property="ordered" jdbcType="INTEGER"/>
                 <result column="chapter_create_time" property="createTime" jdbcType="TIMESTAMP"/>


            <!--章里面包含集,在把章里面的集查询出来-->
             <collection property="episodeList" ofType="Episode">
                 <!--把id改成episode_id,要不和上面重叠，没法映射，查询语句也得改，取个别名-->
                       <id column="episode_id" property="id" jdbcType="INTEGER"/>

                 <result column="episode_title" property="title" jdbcType="VARCHAR"/>
                 <result column="num" property="num" jdbcType="INTEGER"/>
                 <result column="episode_ordered" property="ordered" jdbcType="INTEGER"/>
                 <result column="play_url" property="playUrl" jdbcType="VARCHAR"/>
                 <result column="free" property="free" jdbcType="INTEGER"/>
                 <result column="episode_create_time" property="createTime" jdbcType="TIMESTAMP"/>

             </collection>
        </collection>
