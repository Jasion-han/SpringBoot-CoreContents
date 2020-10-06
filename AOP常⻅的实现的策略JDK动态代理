什么是动态代理
在程序运⾏时，运⽤反射机制动态创建⽽成，⽆需⼿动编写代码
JDK动态代理与静态代理⼀样，⽬标类需要实现⼀个代理接⼝,再通过代理对象调⽤⽬标⽅法






//代理演示:service接口类
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



   

   
//实现类
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





//动态代理类


/**
 动态代理特点:
 字节码随用随创建，随用随加载
 作用:不修改源码基础上，对方法进行增强
 分类:基于接口的动态代理
 基于子类的动态代理
 这里讲的是基于接口的动态代理，涉及的类是proxy
 提供者是jdk
 学习如何创建动态代理对象:
 使用proxy类中的 new ProxyInsance方法
 要求:被代理类最少实现一个接口，如果没有则不能使用
 new ProxyInsance方法的参数:

 ClassLoader:用于加载代理对象字节码的，被代理对象的类加载器,或者说和和被代理对象使用相同的类加载器
 代理谁就写谁的getClass().getClassLoder()     固定写法

 interfaces: 字节码数组
 Class<?>[]  用于让代理对象和被代理对象有相同的方法，代理谁就写谁的getClass().getinterfaces()    固定写法

 InvocationHandler  接口  用于提供增强的代码
 它是让我们写如何代理，我们都是写一个该接口的实现类，通常都是一个匿名内部类
 此接口的实现类，谁用谁写
 */
//动态代理
public class JdkProxy implements InvocationHandler {

        private  Object   targetObject;  //目标类 ,就是PayService类


         public Object newProxyInstance(Object targetObject){ //这个代理类，和目标类绑定关系

                 this.targetObject = targetObject;
                 //绑定关系，也就是和具体的哪个实现类关联， 上面用了Object类型，可以更广泛一些，这样我们根据不同类型进行业务书写，解耦思想
             return    Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(),this);


         }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{

                Object   result = null;
                try {
                    //可以做一些打印日志和事务控制，这样service类每加一个方法，就不用在代理类里拓展内容，这个是通用的
                    System.out.println("通过JDK动态代理调用"+method.getName()+",打印日志 begin");
                    result = method.invoke(targetObject, args);
                    System.out.println("通过JDK动态代理调用"+method.getName()+",打印日志 end");
                }catch (Exception e){
                       e.printStackTrace();
                }
              return result;
    }
  }

}




//测试动态代理
    public static void main(String[] args) {

          //PayService   payService  = new staticProxyPayServiceImpl(new PayServiceImpl());
         // payService.save(1,2);

            JdkProxy   jdkProxy   =  new JdkProxy();

               PayService  payServiceProxy  = (PayService) jdkProxy.newProxyInstance(new PayServiceImpl());

                payServiceProxy.callback("asadf");
               payServiceProxy.save(1,2);

    }


}
