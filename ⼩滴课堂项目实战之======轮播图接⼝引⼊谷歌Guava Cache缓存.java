//工具类， 引入谷歌Guava Cache


/**
 * ⾕歌开源缓存框架Guava Cache讲解和封装缓存组件
 *     讲解⾕歌开源框架Guava Cache，封装API
 *
 * 分布式缓存
 与应⽤分离的缓存组件或服务，与本地应⽤隔离⼀个独⽴的应⽤，多个应⽤可直接的共享缓
 存
 常⻅的分布式缓存 Redis、Memcached等

 本地缓存:
       和业务程序⼀起的缓存，例如myabtis的⼀级或者⼆级缓存，本地缓存⾃然是最快的，但是不
       能在多个节点共享
      常⻅的本地缓存：ssm基础课程myabtis ⼀级缓存、mybatis⼆级缓存；框架本身的缓存；
       redis本地单机服务；ehchche；guava cache、Caffeine等
 */


    @Component
    public class BaseCache {


       private Cache<String,Object> tenMinuteCache = CacheBuilder.newBuilder()
            //设置缓存初始⼤⼩，应该合理设置，后续会扩容
            .initialCapacity(10)
            //最⼤值
            .maximumSize(100)
            //并发数设置，多个线程同时写入
            .concurrencyLevel(5)
            //缓存过期时间，写⼊后10分钟过期
            .expireAfterWrite(600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            //构建下类
            .build();



     //get  和set方法
    public Cache<String, Object> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }
}






//cofig包下的key管理类

**
 * ⾕歌开源缓存框架Guava Cache讲解和封装缓存组件
 *
 * 这个类是缓存的key管理类
 */
public class CacheKeyManager {

    /**
     *首页轮播图缓存key,  index:banner==模块+功能划分的，比如说:banner首页轮播图
     */
       public  static final String  INDEX_BANNER_KEY = "index:banner";
}





//轮播图列表接口开发----------他的实现类VideoBannerServiceImpl加入谷歌缓存组件

@Service
public class VideoBannerServiceImpl implements VideoBannerService {

    @Autowired
    private VideoBannerMapper    videoBannerMapper;

    /**
     * 注入谷歌Guava缓存
     */
    @Autowired
     private BaseCache  baseCache;


    /**
     * 查询轮播图列表
     *
     * @return
     */
    @Override
    public List<VideoBanner> listBanner(){
        //调用谷歌Guava缓存，然后将轮播图key传进去,
        // 后面Callable<? extends Object>)callable,是回调函数，用jdk8 Lambdas表达式写
                 try{

                    Object cacheObj  = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{

                         List<VideoBanner>    bannerList  =   videoBannerMapper.listVideoBanner();
                        System.out.println("从数据库⾥⾯找轮播图列表");
                         return bannerList;
                     });

                     if(cacheObj instanceof List){
                         List<VideoBanner> bannerList = (List<VideoBanner>)cacheObj;
                         return bannerList;
                     }

                 }catch (Exception e){
                     e.printStackTrace();
                 }
             return  null;

           }
   }
