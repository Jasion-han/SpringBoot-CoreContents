
//cglibe包下的两个类

/**
 * 生产者
 */
public  class Producer{
   //销售
    public  void   saleProduct(float money){
        System.out.println("销售产品,并拿到钱"+money);
    }


      //售后
    public   void  afterService(float money){

        System .out.println("提供售后服务并拿到钱"+money);
    }

}


**
 *模拟一个消费者
 */
public class Client {

    public static void main(String[] args) {

        /**
         动态代理特点:
         字节码随用随创建，随用随加载
         作用:不修改源码基础上，对方法进行增强
         分类:基于子类的动态代理
         基于子类的动态代理
         这里讲的是基于子类的动态代理，涉及的类是Enhancer
         提供者是j第三方cglib库
         学习如何创建动态代理对象:
         使用 Enhancer 类中的 create方法
         要求:被代理类不能是最终类
         create方法的参数:
                   Class字节码
                      它是用于指定被代理对象的字节码

         callback:用于提供增强的代码
                 它是让我们写如何代理,我们一般都是写一个该接口的实现类,通常情况写都是匿名内部类，但不是必须的
                我们一般都是写该接口的、子接口实现类 MethodInterceptor()
         */

              final   Producer   producer  =new Producer(); //匿名内部类访问外部成员时，外部成员必须用final修饰

       Producer  cglibe = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor(){  //MethodInterceptor是callback的子接口
            /**
             * intercept方法,执行被代理对象的任何方法,都会经过该方法
             * @param proxy   代理对象的引用   一般不用
             * @param method   当前执行的方法
             * @param args      当前方法执行所需的参数，和被代理对象的方法有相同的返回值
             * 以上三个参数和基于接口动态代理中， invoke方法的参数是一样的
             * @param methodProxy  :当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {


                //提供增强的代码
                Object  returnValue =null;  //返回值用一个object来接受
                //1、获取方法执行的参数
                Float   money  = (Float) args[0];  //args[0],有几个参数写几
                //2、 判断当前方法是不是销售
                if("saleProduct".equals(method.getName())){ //当前方法是不是参数的方法名称
                    returnValue = method.invoke(producer,money*0.8f); //后面是方法的参数,生产者只拿到800元钱
                }
                return returnValue;
            }
        });

        //执行方法，消费者拿出1000购买商品，生产者拿960，销售者拿走240
        cglibe.saleProduct(1200);
    }
}








//proxy包下的类


/**
 * 对生产厂家要求的接口
 */
public interface IProducer {

    /**
     * 销售
     *
     * @param money
     */
    public void saleProduct(float money);


    /**
     * 售后
     *
     * @param money
     */
    public void afterService(float money);

}


/**
 * 生产者实现类
 */
public  class Producer implements IProducer{
   //销售
    public  void   saleProduct(float money){
        System.out.println("销售产品,并拿到钱"+money);
    }


      //售后
    public   void  afterService(float money){

        System .out.println("提供售后服务并拿到钱"+money);
    }

}






/**
 *模拟一个消费者
 */
public class Client {

    public static void main(String[] args) {

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

        //找生产者,new 一个生产者对象  购买商品
         final Producer producer = new Producer();//匿名内部类访问外部成员时，外部成员必须用final修饰
        //接口指向实现类
         IProducer  proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                  new InvocationHandler() {
                      /**
                       * invoke方法作用:执行被代理对象的任何接口的方法，都会经过该方法，该方法就会有拦截功能
                       * 方法参数的含义:
                       * @param proxy :  代理对象的引用   一般不用
                       * @param method   当前执行的方法
                       * @param args  :  当前方法执行所需的参数，和被代理对象的方法有相同的返回值
                       * @return
                       * @throws Throwable
                       * 匿名内部类访问外部成员时，外部成员必须用final修饰
                       */
                      @Override
                      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                          //提供增强的代码

                          Object  returnValue =null;  //返回值用一个object来接收
                           //1、获取方法执行的参数
                            Float   money  = (Float) args[0];  //args[0],有几个参数写几
                            //2、 判断当前方法是不是销售
                           if("saleProduct".equals(method.getName())){ //当前方法是不是参数的方法名称
                               returnValue = method.invoke(producer,money*0.8f); //后面是方法的参数,生产者只拿到800元钱
                           }
                          return returnValue;
                      }
                  });
            //执行方法，消费者拿出1000购买商品，生产者拿走800，销售者拿走200
            proxyProducer.saleProduct(1000);
    }
}

