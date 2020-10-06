        
     == 批量插入语法之，批量插入获取自增id==


          简介：讲解Mybatis的foreach批量插⼊语法的使⽤
          批量插⼊多条视频记录
          foreach: ⽤于循环拼接的内置标签，常⽤于 批量新增、in查询等常⻅
          包含以下属性：

         collection：必填，值为要迭代循环的集合类型，情况有多种
         ⼊参是List类型的时候，collection属性值为list
         ⼊参是Map类型的时候，collection 属性值为map的key值

         item：每⼀个元素进⾏迭代时的别名
         index：索引的属性名，在集合数组情况下值为当前索引值，当迭代对象是map时，这个值是  map的key

         open：整个循环内容的开头字符串
         close：整个循环内容的结尾字符串                              如果批量插⼊要获取⾃增id, 可以按照单条记录获得⾃增id的⽅式
         separator: 每次循环的分隔符                                 useGeneratedKeys="true" keyProperty="id" keyColumn="id":获取自增id
    -->


    //resources配置目录下的mapper文件夹，他里面的VideoMapper.xml配置文件 resources/mapper/VideoMapper.xml

    <insert id="addBatch" parameterType="net.xdclass.online_class.domain.Video" useGeneratedKeys="true" keyProperty="id" keyColumn="id">

        insert  into  video(title,summary,cover_img,price,create_time,point) values
          <foreach collection="list" item="video" separator=",">
         (#{video.title,jdbcType=VARCHAR},#{video.summary,jdbcType=VARCHAR},#{video.coverImg,jdbcType=VARCHAR},#{video.price,jdbcType=INTEGER},#{video.createTime,jdbcType=TIMESTAMP},#{video.point,jdbcType=DOUBLE})
          </foreach>
    </insert>

</mapper>
