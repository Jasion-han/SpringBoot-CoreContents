SpringBoot2.x整合模板引擎freemarker,thymeleaf实战
Freemarker和thymeleaf相关maven依赖

<!-- 引⼊freemarker模板引擎的依赖 -->
 <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-freemarker</artifactId>
 </dependency>




<!--引入thymeleaf模板引擎-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
========================================================
 Freemarker基础配置

 # 是否开启thymeleaf缓存,本地为false，⽣产建议为true
 spring.freemarker.cache=false
 spring.freemarker.charset=UTF-8
 spring.freemarker.allow-request-override=false
 spring.freemarker.check-template-location=true

 #类型
 spring.freemarker.content-type=text/html
 spring.freemarker.expose-request-attributes=true
 spring.freemarker.expose-session-attributes=true

 #⽂件后缀
 spring.freemarker.suffix=.ftl
 #路径
 spring.freemarker.template-loader-path=classpath:/templates/

==================================================================


#thymeleaf基础配置

#开发时关闭缓存,不然没法看到实时⻚⾯
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML5
#前缀
spring.thymeleaf.prefix=classpath:/templates/
#编码
spring.thymeleaf.encoding=UTF-8

#名称的后缀
spring.thymeleaf.suffix=.html

=====================================================


 建⽴⽂件夹freemarker的
 1)src/main/resources/templates/fm/user/
2)建⽴⼀个index.ftl

====================================================

#thymeleaf的
1)src/main/resources/templates/tl/
2)建⽴⼀个index.html




在Controller包文件下建立一个类:FreemarkerController

/*
 * 
SpringBoot2.x整合模板引擎freemarker和thymeleaf实战
 */
@Controller
@RequestMapping(value ="freemarker")
public class FreemarkerController {

          @Autowired   //引入微信配置文件，跳转到指定的页面，拿到配置文件信息
         private WxConfig   wxConfig ;


       @GetMapping("test")
       public  String  index(ModelMap  modelMap){

               modelMap.addAttribute("seting",wxConfig);

           //不用加后缀，因为配置文件里已经指定了后缀，跳转到指定的页面
           return "user/fm/index";
       }




    @GetMapping("thymeleaf")
    public  String  index2(ModelMap  modelMap){

        modelMap.addAttribute("seting",wxConfig);

        //不用加后缀，因为配置文件里已经指定了后缀，跳转到指定的页面
        return "tl/indexTwo";
    }
}



注意：$表达式只能写在th标签内部
