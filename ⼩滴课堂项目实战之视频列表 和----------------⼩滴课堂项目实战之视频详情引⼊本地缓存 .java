
//Utils工具包里的BaseCache类，封装谷歌缓存工作

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




    //根据时间区分的，因为根据id查找视频详情，这个很少改动，所以把过期时间调长一些，不是绝对的
    private Cache<String,Object> oneHourCache = CacheBuilder.newBuilder()
            //设置缓存初始⼤⼩，应该合理设置，后续会扩容
            .initialCapacity(30)
            //最⼤值
            .maximumSize(100)
            //并发数设置，多个线程同时写入
            .concurrencyLevel(5)
            //缓存过期时间，写⼊后一小时过期
            .expireAfterWrite(3600, TimeUnit.SECONDS)
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

    public Cache<String, Object> getOneHourCache() {
        return oneHourCache;
    }

    public void setOneHourCache(Cache<String, Object> oneHourCache) {
        this.oneHourCache = oneHourCache;
    }
}






/**
 * 缓存key管理类
 */
public class CacheKeyManager {

    /**
     * 首页轮播图缓存key
     */
    public static final String INDEX_BANNER_KEY = "index:banner:list";


    /**
     * 首页视频列表缓存key
     */
    public static final String INDEX_VIDEL_LIST = "index:video:list";


    /**
     * 视频详情缓存key, %s是视频id
     */
    public static final String VIDEO_DETAIL = "video:detail:%s";


}













//serviceImpl实现类

package net.xdclass.online_xdclass.service.impl;

/**
 * Video视频的service层实现类
 *
 */

@Service(value ="videoService")
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper   videoMapper;


    /**
     * 注入谷歌Guava缓存
     */
    @Autowired
    private BaseCache  baseCache;



    /**
     * 查询所有视频列表
     * @return
     *   调用谷歌Guava缓存，传入视频列表缓存key,
     *   后面Callable<? extends Object>)callable,是回调函数，用jdk8 Lambdas表达式写
     */
    @Override
    public List<Video> listVideo() {
        try {
            Object  cachObj  =baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST, ()->{

                List<Video>  videoList = videoMapper.listVideo();
                    System.out.println("从数据库⾥⾯找视频列表");

                return videoList;
            });

            if(cachObj instanceof List){
                List<Video> videoList  = (List<Video>) cachObj;
                 return videoList;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //可以返回兜底数据,业务系统降级
        return  null;
    }








    /**
     * 根据id查询视频详情信息，包含章，集
     * @param videoId
     * @return
     * 需要使用MyBatis关联复杂查询
     *
     * 调用谷歌Guava缓存，然后将视频详情key传进去,
     *   后面Callable<? extends Object>)callable,是回调函数，用jdk8 Lambdas表达式写
     */
    @Override
    public Video findDetailById(int videoId) {
          //格式化一下，因为有%号
           String     videoCachKey =  String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

        try {
               Object  cacheObj  = baseCache.getOneHourCache().get( videoCachKey,()->{

                Video   video  =    videoMapper.findDetailById(videoId);
                return video;
            });

            if(cacheObj instanceof  Video){
                Video   video  = (Video)cacheObj;
                return video;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return  null;
    }



}
