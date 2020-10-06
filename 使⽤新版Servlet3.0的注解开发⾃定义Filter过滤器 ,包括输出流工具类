@WebFilter(value = "/api/v1/pri/*",filterName ="loginFilter")   标记⼀个类为filter，被spring进⾏扫描
public class LoginFilter  implements Filter{
     这个是将Json对象，转成KeyValue形式    序列化
      private  static  final ObjectMapper   objectMapper  = new ObjectMapper();
   

    容器加载时候   

    
    public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("init LoginFilter===========");
    }




  
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter LoginFilter==============");
        HttpServletRequest  req  =(HttpServletRequest)servletRequest;
        HttpServletResponse   resp =(HttpServletResponse)servletResponse;
                  String  token   = req.getHeader("token");
                  if(StringUtils.isEmpty(token)){  如果token为空串或者为null,它都是一个empty
                         token =  req.getParameter("token");就从请求参数里拿
                  }

             ⾃定义Filter 告诉APP.或者网页，用户未登录，或者登录过期 ,json错误码提示开发
               使⽤Servlet3.0注解开发⾃定义的过滤器，返回未登录错误码
                  if(!StringUtils.isEmpty(token)){   如果不为Null
                      //判断是否合法
                      User   user = UserServiceImpl.sessionMap.get(token);  从数据库Map里拿然后比较，符合
                      if(user!=null){  符合需求,但拿到的token也可能失效了，
                          filterChain.doFilter(servletRequest,servletResponse);传进来，符合需求的，下边是可能拿到的失效了
                      }else{  否则拿到的token失效了

                          JsonData   jsonData =JsonData.buildErrorr(-2,"登录失败,token无效");
                         String  jsonStr =   objectMapper.writeValueAsString(jsonData); 这个是将Json对象，转成KeyValue形式字符串 序列化
                          RendJsonStr.renderJson(resp,jsonStr); 输出response和json.  使用了流工具类 RendJsonStr
                      }

                  }else{  走这个else 是因为token可能就没有
                      JsonData   jsonData =JsonData.buildErrorr(-3,"未登录");
                      String  jsonStr =   objectMapper.writeValueAsString(jsonData);
                      RendJsonStr.renderJson(resp,jsonStr);
                  }
           }




    
    容器销毁时候
  
    public void destroy() {
        System.out.println(" destroyFilter===============");
    }
}






/工具类 ，自动关闭IO流  ，拿到输出流,把内容输出
public class RendJsonStr {

    /* ⾃定义Filter 告诉APP.或者网页，用户未登录，或者登录过期 ,json错误码提示开发
        使⽤Servlet3.0注解开发⾃定义的过滤器，返回未登录错误码*/
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




使⽤新版Servlet3.0的注解开发⾃定义Filter
        简介：使⽤Servlet3.0注解开发⾃定义的过滤器
        使⽤Servlet3.0的注解进⾏配置步骤
        启动类⾥⾯增加 @ServletComponentScan，进⾏扫描
        新建⼀个Filter类，implements Filter，并实现对应的接⼝
     @WebFilter 标记⼀个类为filter，被spring进⾏扫描
        urlPatterns：拦截规则，⽀持正则
        控制chain.doFilter的⽅法的调⽤，来实现是否通过放⾏
        不放⾏，web应⽤resp.sendRedirect("index.html") 或者 返回json字符串
        场景：权限控制、⽤户登录状态控制，也可以交给拦截器处理等
        在线教育项⽬案例实战:
        ⽤户登录过滤器  
