/**
 *  springBOOT2.x新版上下文监听器
 *   应用上下文监听器
 */
@WebListener
public class ApplicationListener  implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
           System.out.println("contextInitialized应用启动监听==============");  //启动监听器
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed销毁应用监听==============");  //销毁监听器
    }
}







/**
 * 请求监听器
 * ServletRequestListener 请求监听
 * 常⽤的监听器 ServletContextListener、HttpSessionListener、ServletRequestListener)
 */
@WebListener
public class CustomRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("requestDestroyed=======销毁请求监听器");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("requestDestroyed=======启动请求监听器");
    }
}
