/*
   新版SpringBoot2.X拦截器配置实战
        简介: 讲解Spingboot2.x新版本配置拦截器在项⽬中的使⽤
        拦截器： 和过滤器⽤途基本类似
        SpringBoot2.x使⽤步骤
        SpringBoot2.X 新版本配置拦截器 implements WebMvcConfigurer

        ⾃定义拦截器 HandlerInterceptor
        preHandle：调⽤Controller某个⽅法之前
        postHandle：Controller之后调⽤，视图渲染之前，如果控制器Controller出现了
        异常，则不会执⾏此⽅法
        afterCompletion：不管有没有异常，这个afterCompletion都会被调⽤，⽤于资
        源清理
        按照注册顺序进⾏拦截，先注册，先被拦截   */

/*
 拦截器配置类

  先注册，先拦截，意思就是先添加的拦截器,先拦截，最先加载的after,最先出去
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
                //addPathPatterns()配置拦截器路径，将拦截器和拦截路径添加进来
               registry.addInterceptor(getLoginInterCeptor()).addPathPatterns("/api/v1/pri/**");

               //可以添加多个拦截器，看需求......


              WebMvcConfigurer.super.addInterceptors(registry);//把参数传进来
    }


    /*
      获取到自定义的拦截器
     */
     @Bean
     public  LoginInterCeptor   getLoginInterCeptor(){


        return  new LoginInterCeptor();
     }


}





**
 * 自定义的拦截器
 */
public class LoginInterCeptor implements HandlerInterceptor {

    //这个是将Json对象，转成KeyValue形式   序列化
    private  static  final ObjectMapper objectMapper  = new ObjectMapper();
        /*
        preHandle：调⽤Controller某个⽅法之前
        */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           System.out.println("loginInterCeptor  preHandle拦截器==========");

                    String  token   =  request.getHeader("token");
                    if(StringUtils.isEmpty(token)){  //如果token为空串，或者为空,它都是一个empty
                             token =  request.getParameter(token);
                    }

            /* ⾃定义Filter 告诉APP.或者网页，用户未登录，或者登录过期 ,json错误码提示开发
               使⽤Servlet3.0注解开发⾃定义的过滤器，返回未登录错误码*/
                   if(!StringUtils.isEmpty(token)){  //如果不为空
                      User user   =    UserServiceImpl.sessionMap.get(token);//从map里拿，然后比较
                       if(user!=null){//用户不为空往下走返回true,但拿到的token也可能失效了,继续往下
                             return true; //往下走
                       }else{//否则拿到的token失效了
                         JsonData  jsonData =  JsonData.buildErrorr(-2,"登录失败,token无效");
                            String   jsonstr  =    objectMapper.writeValueAsString(jsonData); //序列化转到前端那里
                             RendJsonStr.renderJson(response,jsonstr);//关闭流，输出内容，使用了工具类
                             return false;
                           }

                   }else{//走这个else 是因为token可能就没有
                       JsonData  jsonData =  JsonData.buildErrorr(-3,"未登录");
                       String   jsonstr  =    objectMapper.writeValueAsString(jsonData);
                       RendJsonStr.renderJson(response,jsonstr);//使用了工具类
                       return false;
                   }

        //return HandlerInterceptor.super.preHandle(request,response,handler);  
    }





import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//工具类 ，自动关闭IO流  ，拿到输出流,把内容输出
public class RendJsonStr {

    /*
      这个工具类可以用在过滤器或者是拦截器写法中
     */
       public static  void  renderJson(ServletResponse response, String json){

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");   //内容类型
        try(PrintWriter writer = response.getWriter()){
                writer.print(json);//出去了
        } catch (Exception e) {   //捕获异常
            e.printStackTrace();
        }
    }
}










    /*  下边的postHandle 和afterCompletion可以不重写
     postHandle：Controller之后调⽤，视图渲染之前，如果控制器Controller出现了
     异常，则不会执⾏此⽅法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle  preHandle拦截器Controller之后调⽤，视图渲染之前==========");
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    /*
     afterCompletion：不管有没有异常，这个afterCompletion都会被调⽤，⽤于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion  preHandle不管有没有异常，这个afterCompletion都会被调⽤==========");
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
