讲解Spring常⻅的事务管理
事务：多个操作，要么同时成功，要么失败后⼀起回滚

具备ACID四种特性
Atomic（原⼦性）
Consistency（⼀致性）
Isolation（隔离性）
Durability（持久性）


你知道常⻅的Spring事务管理⽅式吗
编程式事务管理:
声明式事务管理:


你知道声明式事务管理本质吗：
本质是对⽅法前后进⾏拦截，底层是建⽴在 AOP 的基础之上
在⽬标⽅法开始之前创建或者加⼊⼀个事务，在执⾏完⽬标⽅法之后根据执⾏情况提交或者回滚事
务





Spring事务的传播属性和隔离级别
事物传播⾏为介绍:
如果在开始当前事务之前，⼀个事务上下⽂已经存在，此时有若⼲选项可以指定⼀个事务性
⽅法的执⾏⾏为


@Transactional(propagation=Propagation.REQUIRED) 如果有事务, 那么加⼊事务, 没
有的话新建⼀个(默认情况下)
@Transactional(propagation=Propagation.NOT_SUPPORTED) 不为这个⽅法开启事务
@Transactional(propagation=Propagation.REQUIRES_NEW) 不管是否存在事务,都创
建⼀个新的事务,原来的挂起,新的执⾏完毕,继续执⾏⽼的事务
@Transactional(propagation=Propagation.MANDATORY) 必须在⼀个已有的事务中执
⾏,否则抛出异常
@Transactional(propagation=Propagation.NEVER) 必须在⼀个没有的事务中执⾏,否
则抛出异常(与Propagation.MANDATORY相反)
@Transactional(propagation=Propagation.SUPPORTS) 如果其他bean调⽤这个⽅法,
在其他bean中声明事务,那就⽤事务.如果其他bean没有声明事务,那就不⽤事务.
@Transactional(propagation=Propagation.NESTED) 如果当前存在事务，则创建⼀个
事务作为当前事务的嵌套事务来运⾏； 如果当前没有事务，则该取值等价于
Propagation.REQUIRED。
