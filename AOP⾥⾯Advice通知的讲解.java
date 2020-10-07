

@Before前置通知
在执⾏⽬标⽅法之前运⾏



@After后置通知
在⽬标⽅法运⾏结束之后


@AfterReturning返回通知
在⽬标⽅法正常返回值后运⾏


@AfterThrowing异常通知
在⽬标⽅法出现异常后运⾏


@Around环绕通知
在⽬标⽅法完成前、后做增强处理 ,环绕通知是最重要的通知类型 ,像事务,⽇志等都是环绕通
知,注意编程中核⼼是⼀个ProceedingJoinPoint，需要⼿动执⾏ joinPoint.procced()








//⽬标类 VideoOrderService； ⾥⾯每个⽅法都是连接点，；切⼊点是CUD类型的⽅法，R读取的不作为切⼊点

//CRDU全称：增加(Create)、读取查询(Retrieve)、更新(Update)和删除(Delete)
VideoOrderService{
 //新增订单
 addOrder(){ }
 //查询订单
 findOrderById(){}
 //删除订单
 delOrder(){}
 //更新订单
 updateOrder(){}
}



//权限切⾯类 = 切⼊点+通知
PermissionAspects{

 //切⼊点 定义了什么地⽅
 @Pointcut("execution(public int
net.xdclass.sp.service.VideoOrderService.*(..))")
 public void pointCut(){}


 //before 通知 表示在⽬标⽅法执⾏前切⼊, 并指定在哪个⽅法前切⼊
 //什么时候，做什么事情
 @Before("pointCut()")
 public void permissionCheck(){

 System.out.println("在 xxx 之前执⾏权限校验");
 }
 ....
}



//⽇志切⾯类 = 切⼊点+通知
LogAspect{

 //切⼊点 定义了什么地⽅
 @Pointcut("execution(public int
net.xdclass.sp.service.VideoOrderService.*(..))")
 public void pointCut(){}


 //after 通知 表示在⽬标⽅法执⾏后切⼊, 并指定在哪个⽅法前切⼊
 //什么时候，做什么事情
 @After("pointCut()")
 public void logStart(){


System.out.println("在 xxx 之后记录⽇志");
 }
 ....
}




