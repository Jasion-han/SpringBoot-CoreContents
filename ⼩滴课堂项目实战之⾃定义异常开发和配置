开发⾃定义异常和配置



/**
 * 自定义异常类,⾃定义异常 继承 RuntimeException
 */
public class XDException extends  RuntimeException{

       private  Integer  code;

       private    String  msg;

    /**
     * 空参构造方法
     */

       public  XDException(){

       }

    /**
     * 有参构造方法
     * @param code
     * @param msg
     */
       public XDException(Integer code,String msg){
              this.code  = code;
              this.msg = msg;
       }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}







/**
 * 自定义异常处理器，捕获异常，然进行判断，全局异常处理
 */

@RestControllerAdvice
public class CustomExceptionHandler {

    /**记录对应日志
     * @param e
     * @return
     */

     private  final static Logger  logger = LoggerFactory.getLogger(CustomExceptionHandler.class);



    @ExceptionHandler(value = Exception.class)
    @ResponseBody  /*传到前端*/
    public JsonData handler(Exception e){
             /*打印异常*/
            logger.error("[]{}",e);

           /*判断是否是自定义异常*/
            if(e instanceof XDException){
                /*如果是,强转成自定义异常*/
                XDException  xdException  = (XDException) e;

                return  JsonData.buildErrorr(xdException.getCode(),xdException.getMsg());

            }else{
                  return  JsonData.buildError("全局异常,未知错误");
            }
    }

}
