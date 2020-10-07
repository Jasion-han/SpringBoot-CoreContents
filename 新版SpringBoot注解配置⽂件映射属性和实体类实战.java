pay.properties假设存放微信配置文件的
#新版SpringBoot注解配置文件映射属性和实体类实战

#微信支付的配置

#微信支付的app
wxpay.appid=w23432432432

#微信支付密钥
wxpay.sercret=sfwerefwef

#微信支付商户号
wxpay.mechid=324324




//解析配置文件信息的config文件下的 一个实体类

@Configuration
@PropertySource("classpath:pay.properties")
public class WxConfig {
      @Value("${wxpay.appid}")
      private   String  payAppid;
      @Value("${wxpay.sercret}")
      private  String  paySercret;
      @Value("${wxpay.mechid}")
      private  String  paymechId;

     //get和set方法

      public String getPayAppid() {
            return payAppid;
      }

      public void setPayAppid(String payAppid) {
            this.payAppid = payAppid;
      }

      public String getPaySercret() {
            return paySercret;
      }

      public void setPaySercret(String paySercret) {
            this.paySercret = paySercret;
      }

      public String getPaymechId() {
            return paymechId;
      }

      public void setPaymechId(String paymechId) {
            this.paymechId = paymechId;
      }
}







//测试类

@RestController
@RequestMapping("api/v1/test")
@PropertySource({"classpath:pay.properties"})  //类路径下的properties配置文件
public class TestController {
 //${wxpay.appid}这个必须和配置文件properties文件里的配置名称一致，否则报错
      @Value("${wxpay.appid}")
      private  String   payAppid;
      @Value("${wxpay.sercret}")
      private   String  paySercret;

       @Autowired
       private WxConfig  wxConfig;  //自动引入  WxConfig微信配置文件，我们是拿这个配置文件的属性，而不是返回


     //获取微信配置文件信息
      @GetMapping("get_config")
      public JsonData  testConfig(){

          Map<String,String>  map = new HashMap<>();
             //这是直接通过注解方式
//            map.put("appid",payAppid);
//            map.put("secret",paySercret);

              //通过引入配置类，通过配置类获取
              map.put("appid",wxConfig.getPayAppid());
              map.put("secret",wxConfig.getPaySercret());
              map.put("mechid",wxConfig.getPaymechId());

          return  JsonData.buildSuccess(map);
      }

