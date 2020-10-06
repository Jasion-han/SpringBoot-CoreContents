
resources配置目录下的 mapper文件夹，他里面的VideoMapper.xml文件



<!-- 删除某个时间段之后 且⾦额⼤于 10元的数据,

   为什么要转义字符:
    由于MyBatis的sql写在XML⾥⾯， 有些sql的语法符号和xml⾥⾯的冲突

    ⼤于等于 <![CDATA[ >= ]]>
    ⼩于等于 <![CDATA[ <= ]]>
   -->

      <!--这个参数parameterType也可以用别名 Map-->
     <delete id="deleteByCreateTimeAndPrice" parameterType="Map">

         delete from video where create_time <![CDATA[ > ]]> #{createTime} and price <![CDATA[ >= ]]> #{price}

    </delete>
