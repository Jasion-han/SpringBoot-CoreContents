使⽤JDBC的事务管理
使⽤ java.sql.Connection对象完成对事务的提交（commit()）、回滚（rollback()）、关闭
（close()）


使⽤MANAGED的事务管理
MyBatis⾃身不会去实现事务管理，⽽让程序的容器如（Spring, JBOSS）来实现对事务的管
理


<environment id="development">

 <!-- mybatis使⽤jdbc事务管理⽅式 -->
 <transactionManager type="JDBC"/>

 <dataSource type="POOLED">
 <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
 <property name="url"
value="jdbc:mysql://127.0.0.1:3306/xdclass?
useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false"/>
 <property name="username" value="root"/>
 <property name="password" value="xdclass.net"/>
 </dataSource>
</environment>


事务⼯⼚TransactionFactory 的两个实现类
JdbcTransactionFactory->JdbcTransaction
ManagedTransactionFactory->ManagedTransaction






讲解mybatis事务控制实战

为什么原先没进⾏commit操作，也可以插⼊成功？因为原先是myisam引擎,没有事务，直接插⼊成功检查数据库的 引擎 ，改为innodb
多个表video/chapter/episode/user/video_order/video_banner

案例实战:

     事务管理记得改为这个：<transactionManager type="JDBC"/>


事务管理形式 MANAGED，设置⾮⾃动提交，然后注释 commit, 依旧可以保存成功

不⽤重点关注，公司开发项⽬的事务控制基本是交给Spring，或者使⽤分布式事务
