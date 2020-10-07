
/*
  标记这个是一个异常处理类,就是我们自定义的异常
  类添加注解
  @ControllerAdvice，如果需要返回json数据，则⽅法需要加@ResponseBody
  @RestControllerAdvice, 默认返回json数据，⽅法不需要加@ResponseBody
  ⽅法添加处理器

  捕获全局异常,处理所有不可知的异常
  @ExceptionHandler(value=Exception.class)
*/




 //自定义异常，返回json对象
@RestControllerAdvice
public class CustomExtHandler{

     @ExceptionHandler(value = Exception.class)
     JsonData handlerException(Exception e, HttpServletRequest request) {

         return JsonData.buildErrorr(-2, "服务端出问题了");
     }

 }







/*   //SpringBoot2.x⾃定义全局异常返回⻚⾯  返回一个自定义异常的页面
   @ControllerAdvice
   public  class    CustomExtHandler{
       @ExceptionHandler(value = Exception.class)
        Object   handlerException(Exception e,HttpServletRequest request){

            ModelAndView  modelAndView  = new ModelAndView();
                         //错误页面的路径
                           modelAndView.setViewName("error.html");  //页面
                           ///设置错误信息提示
                           modelAndView.addObject("msg",e.getMessage());
                   return   modelAndView;

          }
   }*/

