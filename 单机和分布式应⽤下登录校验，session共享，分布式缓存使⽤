第2集 常⻅的互联⽹项⽬中 单机和分布式应⽤的登录校验解决⽅案
简介：讲解单机和分布式应⽤下登录校验，session共享，分布式缓存使⽤
单机tomcat应⽤登录检验
sesssion保存在浏览器和应⽤服务器会话之间
⽤户登录成功，服务端会保存⼀个session，当然客户端有⼀个sessionId
客户端会把sessionId保存在cookie中，每次请求都会携带这个sessionId
分布式应⽤中session共享
真实的应⽤不可能单节点部署，所以就有个多节点登录session共享的问题需要解决
tomcat⽀持session共享，但是有⼴播⻛暴；⽤户量⼤的时候，占⽤资源就严重，不推荐
使⽤redis存储token:
服务端使⽤UUID⽣成随机64位或者128位token，放⼊redis中，然后返回给客户端并存
储在cookie中
⽤户每次访问都携带此token，服务端去redis中校验是否有此⽤户即可
