：分布式应⽤的登录检验解决⽅案 JWT讲解 json wen token


什么是JWT
JWT 是⼀个开放标准，它定义了⼀种⽤于简洁，⾃包含的⽤于通信双⽅之间以 JSON 对象的
形式安全传递信息的⽅法。 可以使⽤ HMAC 算法或者是 RSA 的公钥密钥对进⾏签名
简单来说: 就是通过⼀定规范来⽣成token，然后可以通过解密算法逆向解密token，这样就
可以获取⽤户信息



优点
⽣产的token可以包含基本信息，⽐如id、⽤户昵称、头像等信息，避免再次查库
存储在客户端，不占⽤服务端的内存资源
缺点
token是经过base64编码，所以可以解码，因此token加密前的对象不应该包含敏感信
息，如⽤户权限，密码等
如果没有服务端存储，则不能做登录失效处理，除⾮服务端改秘钥



JWT格式组成 头部、负载、签名
header+payload+signature
头部：主要是描述签名算法
负载：主要描述是加密对象的信息，如⽤户的id等，也可以加些规范⾥⾯的东⻄，如iss
签发者，exp 过期时间，sub ⾯向的⽤户
签名：主要是把前⾯两部分进⾏加密，防⽌别⼈拿到token进⾏base解密后篡改token




======登录校验Json Web Token实战之封装通⽤⽅法===============================================================

import java.util.Date;

/**

 引⼊相关依赖并开发JWT⼯具类, 开发⽣产token和校验token的办法
 */


 <!-- 跨域身份验证解决⽅案 Json web token依赖,JWT相关 -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.7.0</version>
		</dependency>




public class JWTUtils {
    /**
     *过期时间:一周
     */
    private static final long  EXPIRE = 6000 * 60 * 24 * 7;


    /**
     * 加密密钥
     */
    private  static  final  String  SECRET = "xdclass.net168";

    /**
     * 令牌前缀
     */
    private   static  final  String  TOKEN_PRFIX = "xdclass";


    /**
     * 颁布者subject
     */
    private   static  final  String  SUBJECT = "xdclass";






    /**
     * 根据用户信息生成令牌
     * @param user
     * @return
     */
    public  static  String  geneJsonWebToken(User user){

       String  token = Jwts.builder().setSubject(SUBJECT).//构建令牌+颁布者
                claim("head_img",user.getHeadImg()).
                claim("id",user.getId()).
                claim("name",user.getName()).
                claim("phone",user.getPhone()).
                setIssuedAt(new Date()). //颁布时间
                setExpiration(new Date(System.currentTimeMillis() + EXPIRE)). //现在时间的毫秒 +EXPIRE设置的过期时间
                signWith(SignatureAlgorithm.HS256,SECRET).compact(); //加密算法+secret密钥

           token = TOKEN_PRFIX + token; //拼一个前缀
          return null;

    }



    
    /**
     * 校验token的方法
     * 注意点:   1、生成的token，是可以通过base64进行解密出明文信息
     *          2、base64进行解密出明文信息，修改在进行编码,则会解密失败
     *          3、无法作废已颁布的token，除非改密钥
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PRFIX, "")).getBody();
            return claims;
        }catch (Exception e) {
           return null; //解密失败返回一个空
        }

    }
}
