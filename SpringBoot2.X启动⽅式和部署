Jar⽅式打包启动
官⽅推荐，⼯作中最常⽤
步骤：pom⽂件新增maven插件

<build>
 <plugins>
 <plugin>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-maven-plugin</artifactId>
 </plugin>
 </plugins>
</build>

如果没有加，则执⾏jar包 ，报错如下
java -jar spring-boot-demo-0.0.1-SNAPSHOT.jar
no main manifest attribute, in spring-boot-demo-0.0.1-SNAPSHOT.jar



必备打包启动命令:
构建：mvn install
构建跳过测试类 mvn install -Dmaven.test.skip=true

target⽬录下有对应的jar包就是打包后项⽬进到对应的target⽬录启动 java -jar xxxxx.jar 即可
想后台运⾏，就⽤守护进程 nohup java -jar xxx.jar &




