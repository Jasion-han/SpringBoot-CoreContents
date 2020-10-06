切⼊点表示式除了返回类型、⽅法名和参数外，其它项都是可选的 (修饰符基本都是省略不写)




访问修饰符                       返回值类型（必填）             包和类                  ⽅法
（必填）
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?namepattern(param-pattern) throws-pattern?)
@Pointcut("execution(public int net.xdclass.sp.service.VideoOrderService.*(..))")



常⻅匹配语法
  *：  匹配任何数量字符 单个；
  ..： 匹配任何数量字符,可以多个，在类型模式中匹配任何数量⼦包；在⽅法参数模式中匹配任何数量参数
   ()  匹配⼀个不接受任何参数的⽅法
  (..) 匹配⼀个接受任意数量参数的⽅法
  (*) 匹配了⼀个接受⼀个任何类型的参数的⽅法
  (*,Integer) 匹配了⼀个接受两个参数的⽅法，其中第⼀个参数是任意类型，第⼆个参数必须是Integer类型





  常⻅例⼦:

       任意公共⽅法:execution（public * *（..））

       任何⼀个名字以“save”开始的⽅法:  execution（* save*（..））

       VideoService接⼝定义的任意⽅法（识别）:  execution（* net.xdclass.service.VideoService.*（..））

       在service包中定义的任意⽅法（识别）:execution（* net.xdclass.service.*.*（..））

       匹配 service 包,⼦孙包下所有类的所有⽅法（识别:  execution（* net.xdclass.service..*.*（..））

