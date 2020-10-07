
//service接口
/代理演示
public interface PayService {

    /**
     * 支付回调
     * @param outTradeNo
     * @return
     */
    String callback(String  outTradeNo);


    /*
     下单
     */
    int  save(int userId ,int productId);


}



//service接口的实现类
public class PayServiceImpl implements PayService {
    @Override
    public String callback(String outTradeNo) {
        System.out.println("目标类  payServiceImpl回调方法 callback");
        return outTradeNo;
    }

    @Override
    public int save(int userId, int productId) {
        System.out.println("目标类  payServiceImpl回调 方法 save");
        return  productId;
    }
}









//AOP的实现策略之CGLib动态代理
public class CglibProxy implements MethodInterceptor{

    //目标类
   private  Object  targetObject;

   //绑定关系，和哪个目标对象绑定
   public  Object  newProxyInstance(Object  targetObject){
       this.targetObject  = targetObject;
       //绑定对应关系，加强增强意思
       Enhancer   enhancer  = new  Enhancer();

                  //设置代理类的父类(目标类)，才能生成一个子类，作为他的扩展
                  enhancer.setSuperclass(this.targetObject.getClass());
                  //设置回调函数，根据对应的方法做一个回调处理
                  enhancer.setCallback(this);

                  //创建一个子类，目表对象
                  return enhancer.create();

   }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
          Object  result = null;

                try {
                    System.out.println("通过Cglib动态代理调用"+method.getName()+",打印日志 begin");

                    //cglib是通过子类扩展的, 先调用父类的，把目标对象传进去，和他的参数
                    result = methodProxy.invokeSuper(obj, args);

                    System.out.println("通过Cglib动态代理调用"+method.getName()+",打印日志 end");

                }catch (Exception e){
                    e.printStackTrace();
                }
        return result;
    }
}




//测试类

public class testMin {

   //测试代理
    public static void main(String[] args) {

        



              //AOP的实现策略之CGLib动态代理
                CglibProxy   cglibProxy = new CglibProxy();

              PayService      payService  = (PayService) cglibProxy.newProxyInstance(new PayServiceImpl());
                              payService.save(1,2);

                              payService.callback("cccc");
    }


}
