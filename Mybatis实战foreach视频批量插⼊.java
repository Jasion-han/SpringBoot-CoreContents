<!--   Mybatis实战foreach批量插⼊语法之视频批量插⼊
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
         close：整个循环内容的结尾字符串
         separator: 每次循环的分隔符
    -->

    
   //例句:这是在resources文件夹下的mapper文件夹,  mapper文件夹里的videoMapper.xml配置文件(配置增删改查语句的)
   
    <insert id="addBatch" parameterType="net.xdclass.online_class.domain.Video" >

        insert  into  video(title,summary,cover_img,price,create_time,point) values
          <foreach collection="list" item="video" separator=",">
         (#{video.title},#{video.summary},#{video.coverImg},#{video.price},#{video.createTime},#{video.point})
          </foreach>
    </insert>

</mapper>



     //dao层方法
       /*
       Mybatis实战foreach批量插⼊语法之视频批量插⼊
        */
     public  int   addBatch(List<Video> list);







           /*
           Mybatis实战foreach批量插⼊语法 之视频批量插⼊测试类里的测试方法
           */
            @Test
          public  void   addBatchs(){
                Video   video = new  Video();
                video.setTitle("python从入门到放弃");
                video.setSummary("www.rrrrrr.com");
                video.setCoverImg("https://xdvideo-file.aliyuncs.com/");
                video.setPrice(66666);
                video.setCreateTime(new Date());
                video.setPoint(9.1);
           List<Video>  list  = new ArrayList<>();
                   list.add(video);

                int  rows   =   videoMapper.addBatch(list);
                System.out.println(rows);
          }
}
