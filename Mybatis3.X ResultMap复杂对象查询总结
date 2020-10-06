association 映射的是⼀个pojo类，处理⼀对⼀的关联关系。
collection 映射的⼀个集合列表，处理的是⼀对多的关联关系。
模板


<!-- column不做限制，可以为任意表的字段，⽽property须为type 定义的pojo属性-->

<resultMap id="唯⼀的标识" type="映射的pojo对象">
 <id column="表的主键字段,或查询语句中的别名字段" jdbcType="字段类型" property="映射
pojo对象的主键属性" />
 <result column="表的⼀个字段" jdbcType="字段类型" property="映射到pojo对象的⼀个属
性"/>
 <association property="pojo的⼀个对象属性" javaType="pojo关联的pojo对象">

 <id column="关联pojo对象对应表的主键字段" jdbcType="字段类型" property="关联pojo
对象的属性"/>
 <result column="表的字段" jdbcType="字段类型" property="关联pojo对象的属性"/>
 </association>
 <!-- 集合中的property 需要为oftype定义的pojo对象的属性-->
 <collection property="pojo的集合属性名称" ofType="集合中单个的pojo对象类型">
 <id column="集合中pojo对象对应在表的主键字段" jdbcType="字段类型" property="集合
中pojo对象的主键属性" />
 <result column="任意表的字段" jdbcType="字段类型" property="集合中的pojo对象的属
性" />
 </collection>
</resultMap>
