/**
 * 自定义的拦截器
 */
public class LoginInterCeptor implements HandlerInterceptor {



    /**
     * preHandle：进入到Controller之前的方法，
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("loginInterCeptor  preHandle拦截器==========");
                 try {


                     String token = request.getHeader("token");
                   /*如果token为空串，或者为空,从参数拿*/
                     if (token == null) {
                         token = request.getParameter("token");
                     }
                   /* 如果不为空*/
                     if (StringUtils.isNoneBlank(token)) {
                         //token通过JWT进行解密
                         Claims claims = JWTUtils.checkJWT(token);
                         if (claims == null) {
                             //告诉登录过期，请重新登录
                             sendJsonMessage(response,JsonData.buildError("登录过期,请重新登录"));
                             return false;
                         }
                         //否则就正常，拿到id和name
                         Integer id = (Integer) claims.get("id");
                         String name = (String) claims.get("name");
                         String phone = (String) claims.get("phone");
                         //发送给controller
                         request.setAttribute("id", id);
                         request.setAttribute("name", name);
                         request.setAttribute("phone", phone);
                         return true;
                     }

                 }catch (Exception e){}

                    sendJsonMessage(response,JsonData.buildError("登录过期,请重新登录"));

        return false;
    }


    /**
     * 失败之后，相应Json数据给前端
     * @param response
     * @param obj
     */
    public static  void sendJsonMessage(HttpServletResponse response,Object  obj){


                 try{
                        //这个是将Json对象，转成KeyValue形式   序列化成JSON字符串
                        ObjectMapper objectMapper  = new ObjectMapper();
                        //序列化转到前端那里
                         String   jsonstr  = objectMapper.writeValueAsString(obj);
                          RendJsonStr.rendJson(response,jsonstr);//关闭流，输出内容，使用了工具类

                    }catch (Exception e){
                        e.printStackTrace();
                    }
           }
}





/**
 * 工具类，自动关闭IO流，拿到输出流,把内容输出
 *
 * 这个工具类可以用在过滤器或者是拦截器写法中
 */
public class RendJsonStr{


       public  static void  rendJson(ServletResponse response, String json){
           response.setCharacterEncoding("UTF-8");
           response.setContentType("application/json");   //内容类型
           try(PrintWriter writer = response.getWriter()){
               writer.print(json);//出去了
               writer.close();
               response.flushBuffer();
           } catch (Exception e) {   //捕获异常
               e.printStackTrace();
           }
       }


}












//拦截器配置类

**
 * 不⽤权限可以访问url /api/v1/pub/
 * 要登录可以访问url /api/v1/pri/
 * 先注册，先拦截，意思就是先添加的拦截器,先拦截，最先加载的after,最先出去
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

             //拦截全部
             registry.addInterceptor(getLoginInterCeptor()).addPathPatterns("/api/v1/pri/*/*/**")
                     //不拦截哪些路径
              .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");

              WebMvcConfigurer.super.addInterceptors(registry);//调用父类,把参数传进来
    }


    /**
     * 获取到自定的拦截器
     */
    @Bean
    public  LoginInterCeptor   getLoginInterCeptor(){

            return  new LoginInterCeptor();
    }
}





