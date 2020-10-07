<!--新增一条视频记录,
            Mybatis插⼊语法的使⽤和如何获得⾃增主键     自增主键必须加这个：useGeneratedKeys="true" keyProperty="id" keyColumn="id"<!--实体类id映射数据库表的id-->
       <!-  parameterType:参数类型 . resultType:返回值类型，或者叫封装类型
            keyProperty:对应的实体类id属性的名称!!!可以和表的属性名称不一样
            keyColumn:对应数据库表的id
            order:什么时候执行获取id的操作，是在插入之后
            useGeneratedKeys="true":利用自身的主键
  -->
                                                                                                          <!--实体类id映射数据库表的id-->
      <insert id="addVideo" parameterType="net.xdclass.online_class.domain.Video" useGeneratedKeys="true" keyProperty="id" keyColumn="id">

                               <!--前面的是数据库表的名称-->                                          <!--这里是实体类对应的属性-->
       insert  into  video(title,summary,cover_img,price,create_time,point) values(#{title},#{summary},#{coverImg},#{price},#{createTime},#{point})
      </insert>

