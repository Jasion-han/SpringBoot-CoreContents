



/**
 * 用户登录实体类
 */
public class LoginRequest {

    private String  phone;
    private String  pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}














//controller包下的UserController类


/**
 * ⼩滴课堂实战之⽤户注册和登录功能开发,MD5加密⼯具类封装
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

           @Autowired
          private UserService   userService;

    /**
     * 提交表单参数特别多时候,因为我们都是以json格式提交数据的，
     * 这时候我们要加一个注解@RequestBody
     *
     *
     * 
     * 注册接口
     */
       @PostMapping("register")
      public JsonData register(@RequestBody Map<String,String> userInfo){
               int  rows =userService.save(userInfo);
               return rows==1 ? JsonData.buildSuccess(): JsonData.buildError("注册失败,请重试");
      }


    /**
     * 登录接口
     */
     @PostMapping("login")
     public JsonData login(@RequestBody LoginRequest  loginRequest){

           String  token  =   userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
            /*判断*/
           return token == null ? JsonData.buildError("登录失败,账号密码错误"): JsonData.buildSuccess(token);
     }

}






//service包下的 UserService接口

   public interface UserService {

          /**
           * 注册接口，新增用户
           */
          public  int  save(Map<String,String> userInfo);




         /**
           * 根据手机号查询用户信息数据库有电话号码就不能注册，一个手机号只能注册一个用户
             数据库表phone,电话可以增加一个唯一索引
           */
         public User findByPhone(String phone);




           /**
            *  登录接口
            */
           String findByPhoneAndPwd(String phone, String pwd);
       }





//service实现类UserServiceImpl

@Service(value ="userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 注册接口,新增用户
     */

    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user != null) {
            return userMapper.save(user);
        } else {
            return -1;
        }

    }


    /*将Map转成User

        解析user对象
    */
    private User parseToUser(Map<String, String> userInfo) {
        if (userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")) {

            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getrandomImg());  /*=====将方法名称传进来，随机头像==============*/
            user.setPhone(userInfo.get("phone"));
            user.setCreateTime(new Date());

            String pwd = userInfo.get("pwd");
               /*将密码加密成MD5*/
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        } else { /*如果其中一个为null*/
            JsonData.buildErrorr(-1, "数据异常");

        }
        return null;
    }


    /**
     ================随机头像⽣成=======================
     放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    /*获取随机数组头像，*/
     private  String  getrandomImg(){
         int  size  =headImg.length;
         Random   random  =  new Random();
         /*获取一个随机下标，把长度传进去*/
           int  index  = random.nextInt(size);
           /*返回一个随机头像*/
           return headImg[index];
     }





    /**
     * 根据手机号查询用户信息数据库有电话号码就不能注册，一个手机号只能注册一个用户
     * 数据库表phone,电话可以增加一个唯一索引
     */
    //这个方法还没写
    @Override
    public User findByPhone(String phone) {
        return null;
    }





    /**
     * 登录接口开发
     * @param phone
     * @param pwd
     * @return
     */

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {

          User  user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
          if(user==null){
                return null;
          }else{
              String token =JWTUtils.geneJsonWebToken(user); //不等于null,生成校验令牌
              return token;
          }
    }
}





//dao层 UserMapper接口

@Component
public interface UserMapper {


    /**
     * 注册接口,新增用户
     */
      public  int  save(User user);


    /**
     * 根据手机号查询用户信息数据库有电话号码就不能注册，一个手机号只能注册一个用户
       数据库表phone,电话可以增加一个唯一索引
     @Param("phone"):别名
     */
      public User findByPhone(@Param("phone") String phone);





    /**
     *登录接口开发
     */
      public User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);


}




//UserMapper.xml配置文件、
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="net.xdclass.online_xdclass.mapper.UserMapper">

          <!--
          ⼩滴课堂实战之⽤户注册功能开发
          -->
         <!--插入一个用户，就是新注册一个用户-->
         <insert id="save" parameterType="User">
          insert into  user(name,pwd,head_img,phone,create_time)
          values(#{name,jdbcType=VARCHAR},#{pwd,jdbcType=VARCHAR},#{headImg,jdbcType=VARCHAR},#{phone,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP})
         </insert>




         <!--根据手机号查询用户信息，数据库有电话号码就不能注册，一个手机号只能注册一个用户
             数据库表phone,电话可以增加一个唯一索引
             这个还未写
         -->
        <select id="findByPhone" resultType="User">

              select * from user where  phone = #{phone}

        </select>



       <!--登录接口开发，根据手机号和密码找用户-->
      <select id="findByPhoneAndPwd" resultType="User">
           select * from user where  phone = #{phone} and pwd = #{pwd}
      </select>
</mapper>






//工具包下的JWTUtils===分布式应⽤下登录检验解决⽅案 JWT
ublic class JWTUtils {


    /**
     *过期时间:一周
     */
    private static final long  EXPIRE = 60000 * 60 * 24 * 7;


    /**
     * 加密密钥
     */
    private  static  final  String  SECRET = "xdclass.net168";

    /**
     * 令牌前缀
     */
    private   static  final  String  TOKEN_PREFIX  = "xdclass";


    /**
     * 颁布者subject
     */
    private   static  final  String  SUBJECT = "xdclass";






    /**
     * 根据⽤户信息，⽣成令牌
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token =  TOKEN_PREFIX + token;
        return token;

    }




    /**
     * 校验token的方法
     * 注意点:   1、生成的token，是可以通过base64进行解密出明文信息
     *          2、base64进行解密出明文信息，修改在进行编码,则会解密失败
     *          3、无法作废已颁布的token，除非改密钥
     */

    public static Claims checkJWT(String token){
        try{
            final Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace( TOKEN_PREFIX,"")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
