   /* SpringBoot2.X统⼀接⼝返回协议-掌握开发规范
        简介:springboot⾃定义返回对象，统⼀协议
        存在的问题：协议未统⼀，缺少业务状态码
        JSONData⼯具类开发*/

public class JsonData {

      private   int  code;   /*状态码*/
      private   Object   data;  //数据
      private    String  msg; //错误信息提示


     //无参构造方法
      public  JsonData(){}


      //有参构造方法,这个是数据正常,返回正常状态码,然后把数据返回去
      public   JsonData(int code,Object data){

            this.code = code;
            this.data = data;
          }


        //在定义一个三层的有参构造方法，提示错误信息的
      public  JsonData(int code,Object data,String msg){

            this.code = code;
            this.data =data;
            this.msg = msg;
      }


      //快速生成自定义工具， code 0: 走data代表正常数据
      public  static   JsonData  buildSuccess(Object data){

            return   new JsonData(0,data);
      }


    //如果信息有误，状态码返回错误信息 空
      public  static   JsonData  buildError(String  msg){

            return   new JsonData(-1,"",msg);
      }


      //这样写也可以，将状态码传进来
      public  static  JsonData  buildErrorr(int code,String msg){

            return  new  JsonData(code,"",msg);
      }







      //get 和set方法
      public int getCode() {
            return code;
      }

      public void setCode(int code) {
            this.code = code;
      }

      public Object getData() {
            return data;
      }

      public void setData(Object data) {
            this.data = data;
      }

      public String getMsg() {
            return msg;
      }

      public void setMsg(String msg) {
            this.msg = msg;
      }


    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}









//规范写法

public class JsonData {

       /**
        *  SpringBoot2.X统⼀接⼝返回协议-掌握开发规范
           简介:springboot⾃定义返回对象，统⼀协议
           存在的问题：协议未统⼀，缺少业务状态码
           JSONData⼯具类开发*/

    /**
     * 正常数据状态码
     */
    private  Integer  code;

    /**
     * 业务数据
     */
    private   Object   data;

    /**
     * 错误信息信息提示
     */
    private    String  msg;






    /**
     * 空参构造方法
     */
    public JsonData(){

        }





    /**
     * 定义一个三层的有参构造方法，提示信息的
     * @param code
     * @param data
     * @param msg
     */
      public JsonData(Integer code,Object data,String msg){
              this.code = code;
              this.data = data;
              this.msg = msg;
          }





    /**
     * 构建成功不用返回数据 code:0代表数据正常
     * @return
     */
    public  static   JsonData   buildSuccess(){

               return   new JsonData(0,null,null);
          }

    /**
     * 构建成功返回数据，可以传入多个数据
     * @param data
     * @return
     */
     public  static   JsonData   buildSuccess(Object data){

          return   new JsonData(0,data,null);
         }






    /**
     * 构建失败，状态码返回错误信息
     * @param msg
     * @return
     */
    public  static   JsonData  buildError(String  msg){

       return   new JsonData(-1,null,msg);
    }


    /**
     * 构建失败，自定义状态码  ，自己传一个状态码参数
     * @param code
     * @param msg
     * @return
     */
      public  static  JsonData  buildErrorr(Integer code,String msg){

       return  new  JsonData(code,null,msg);
    }


    /**
     * get和set方法
     * @return
     */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


