先在application.properties配置文件里配置:  微信开放平台配置 和 重定向url


#应用启动端口
server.port=8081


#=================================微信相关配置==========================================
#公众号
wxpay.appid=wx5beac15ca207cdd40c           //微信一键登录，公众号配置用不着
wxpay.appsecret=554801238f17fdsdsdd6f96b382fe548215e9


#微信开放平台配置
wxopen.appid=wx025575eac69a2d5b
wxopen.appsecret=f5b6730c592ac15b8b1a5aeb8948a9f3
#重定向url
wxopen.redirect_url=http://16webtest.ngrok.xiaomiqiu.cn/pub/api/v1/wechat/user/callback1






//config包下创建 WeChatConfig配置类   在里面配置

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
     * 从开放平台通过code获取access_token地址
     */
    private  final  static String  OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";





    //微信开放平台的get和set方法

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


//公众号的get和set方法，微信一键登录用不着

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









/**
    新建wechatController类
    
 * 微信授权一键登录开发之授权URL获取  
 */
@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

           @Autowired
          private WeChatConfig   weChatConfig;



    /**
     * 拼装微信扫一扫url
     * @return
     */
    @GetMapping("login_url")  //通过get方式拿到的
    @ResponseBody   //必须加重定向注解，因为微信接口要回调到我们的那个url
    public JsonData loginUrl(@RequestParam(value ="access_page",required =true) String accessPage) throws UnsupportedEncodingException {  //需要加参数，因为微信回调，需要回调到第三方当前那个页面的那个地址
              //拿到开放平台重定向地址，就是说微信扫一扫之后，拿到我们第三方回调的地址
              String  redirectUrl =   weChatConfig.getOpenRedirectUrl();
               //微信回调和微信支付都叫callbackUrl
               String callbackUrl = URLEncoder.encode(redirectUrl,"GBK");//对回调地址进行编码
              //扫码连接, 第一个二维码连接,  第二个appId,第三个重定向url  . 第四个 回调到当前页面的地址 accessPage
               String  qrcodeUrl =String.format(weChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppid(),callbackUrl,accessPage);

        return JsonData.buildSuccess(qrcodeUrl); //返回给前端

    }
}
