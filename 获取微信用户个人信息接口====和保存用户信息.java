//微信配置类配置:




/**
 * 微信配置类
 */
@Configuration
@PropertySource(value ="classpath:application.properties")//注解指定配置文件的位置
public class WeChatConfig {

    /**
     * 公众号appid
     * 映射过来
     */
    @Value("${wxpay.appid}")
    private String appId;


    /**
     * 公众号密钥
     */
    @Value("${wxpay.appsecret}")
    private String  appsecret;



/*======================下边是微信一键登录使用的===========================================================*/
    /**
     * 微信开发平台，appid
     */
    @Value("${wxopen.appid}")
    private String   openAppid;


    /**
     * 微信开放平台, appsecret
     */
    @Value("${wxopen.appsecret}")
    private  String  openAppsecret;


    /**
     * 微信开放平台，回调 url
     */
    @Value("${wxopen.redirect_url}")
    private  String  openRedirectUrl;



    /*
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";



    /**
     * 开放平台通过code获取access_token
     * 通过code获取access_token
     */
    private  final  static String  OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";




    /**
     * 获取用户个人信息（UnionID机制）
     * @return
     * zh_CN代表简体中文
     */
     private  final   static  String  OPEN_USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&zh_CN";




    //微信开放平台的get和set方法
    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }


    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }


    public  static  String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }


    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }


//公众号

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}





WechatController类里写一个接口,如下:
/**
     * 开发微信回调第三方接口，因为需要重定向，所以把response写上
     * 使用授权码code获取微信个人用户信息接口
     */
      @GetMapping("/user/callback")
      public  void  wechatUserCallback(@RequestParam(value ="code",required =true) String code, String state, HttpServletResponse  response){

            //System.out.println("code="+code);  //打印微信是否将code和state传过来
           // System.out.println("state="+state);

         User  user = userService.saveWechatUser(code);
          if(user!=null){

                  //生成JWT
                  String token = JWTUtils.geneJsonWebToken(user);
                  //state:当前用户的页面地址，需要拼接http:// 这样不会站内跳转，获取的name 也需要编码一下
                  response.sendRedirect(state+"?token="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(),"UTF-8"));
      }




      //创建userService接口，然后写它的实现类

      
/**
 *
 * 使用授权码code获取微信用户个人信息接口
 *
 * 用户业务接口的实现类
 */

@Service
public class UserServiceImpl implements UserService {

       @Autowired
       private WeChatConfig  weChatConfig;

       @Autowired
        private UserMapper   userMapper;


    /**
     * 根据code获取用户的token，保存用户的信息
     * 还得在配置类WechatConfig类里配置final  OPEN_ACCESS_TOKEN_URL
     */
    @Override
    public User saveWechatUser(String code) {
     //通过静态类拿到tokenUrl(先不注入weChatConfig,通过静态类拿)，通过注入wechatConfig拿到aapId
        String  access_tokenUrl  = String.format(WeChatConfig.getOpenAccessTokenUrl(),weChatConfig.getOpenAppid(),weChatConfig.getOpenAppsecret(),code);

       //通过httpUtils工具类获取access_token
        Map<String, Object> basemap = HttpUtils.doGet(access_tokenUrl);
        if(basemap==null  || basemap.isEmpty()){return null;}
           String  accessToken  = (String) basemap.get("access_token");
           String   openId  = (String) basemap.get("openid");
        //如果用户注册了，就没必要在拿用户基本信息
           User  dbUser  =  userMapper.findByOpenid(openId);
            if(dbUser!=null){  //可以更新用户，或者直接返回
                return dbUser;
            }



            //先在在微信配置类配置:private  final   static  String  OPEN_USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
          //获取用户基本信息,通过静态方法,微信文档api要求传入access_token=ACCESS_TOKEN&openid=OPENID
        String  userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),accessToken,openId);
           Map<String, Object>  baseUserMap  =   HttpUtils.doGet(userInfoUrl);
          if(baseUserMap==null || baseUserMap.isEmpty()){return  null;}

            //微信回调 用户昵称乱码,解决：
             String  nickname   =(String) baseUserMap.get("nickname");
             try{
                  nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
             }catch(UnsupportedEncodingException e){
                  e.printStackTrace();
             }

             //改成double类型
             Double    sexTemp = (Double) baseUserMap.get("sex");
                  int  sex = sexTemp.intValue(); //在转成int，普通用户性别，1为男性，2为女性、

             String  province   =(String) baseUserMap.get("province");//普通用户个人资料填写的省份
             String  city   =(String) baseUserMap.get("city");//普通用户个人资料填写的城市
             String  country   =(String) baseUserMap.get("country");//国家，如中国为CN
             String  headimgurl   =(String) baseUserMap.get("headimgurl");//用户头像，最后一个数值代表正方形头像大小
             //封装下，国家，省份  和城市
             StringBuilder  stringBuilder  = new StringBuilder(country).append("||").append(province).append("||").append(city);
             String  finalAddres = stringBuilder.toString();
             try{
                 //拿到是这样 中国||上海||普陀,用到时候切割
                  finalAddres =  new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
             }catch (UnsupportedEncodingException e){
                 e.printStackTrace();
             }


              //封装User对象
                 User user =new User();
                     user.setName(nickname);//普通用户昵称
                     user.setHeadImg(headimgurl);  //头像
                     user.setCity(city);    //普通用户个人资料填写的城市
                     user.setOpenid(openId);    //普通用户的标识，对当前开发者帐号唯一
                     user.setSex(sex);   //普通用户性别，1为男性，2为女性
                     user.setCreateTime(new Date());//创建时间

                userMapper.saveUser(user);

        return user;
    }


}




//创建UserMapper接口
public interface UserMapper {


    /**
     * 根据主键id查找
     * @param userId
     * @return
     * @param:给参数取别名
     */
      @Select("select * from User where id=#{id}")
      public User   findById(@Param("id") int  userId);





    /**
     * 根据openid找用户
     * @param openid
     * @return
     */
     @Select("select * from User where openid =#{openid}")
      public  User  findByOpenid(@Param("openid") String  openid);




    /**
     * 保存用户信息
     */
      @Insert("INSERT INTO `user` (`openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)"+
              "VALUES (#{openid}, #{name}, #{headImg}, #{phone}, #{sign}, #{sex}, #{city}, #{createTime})")
      @Options(useGeneratedKeys = true,keyProperty ="id",keyColumn ="id")  //获取自增Id
      public int  saveUser(User  user );


}
