
//xml文件配置

<bean id="video" class="net.xdclass.sp.domain.Video" >

 <!--list类型注⼊-->
 <property name="chapterList">
   <list>
      <value>第⼀章SpringBoot</value>
      <value>第⼆章Mybatis</value>
      <value>第三章Spring</value>
   </list>
 </property>


//Map类型注入
<property name="videoMap">
    <map>
      <entry key="1" value="SpringCloud课程"></entry>
      <entry key="2" value="⾯试课程"></entry>
      <entry key="3" value="javaweb课程"></entry>
    </map>
 </property>

</bean>







//实体类必须配置一个List类型属性  和一个Map类型属性
public class Video {
 private int id;
 private String title;
 private List<String> chapterList;
 private Map<Integer,String> videoMap;

//省略set get⽅法
}
