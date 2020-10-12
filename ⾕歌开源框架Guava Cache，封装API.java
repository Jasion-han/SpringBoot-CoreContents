Guava Cache
github地址：https://github.com/google/guava/wiki/CachesExplained
全内存的本地缓存实现
⾼性能且功能丰富
线程安全，操作简单 (底层实现机制类似ConcurrentMap)



添加依赖
<!--guava依赖包-->
 <dependency>
 <groupId>com.google.guava</groupId>
 <artifactId>guava</artifactId>
 <version>19.0</version>
 </dependency>


 封装api

  private Cache<String,Object> tenMinuteCache = CacheBuilder.newBuilder()
 //设置缓存初始⼤⼩，应该合理设置，后续会扩容
 .initialCapacity(10)
 //最⼤值
 .maximumSize(100)
 //并发数设置
 .concurrencyLevel(5)
 //缓存过期时间，写⼊后10分钟过期
 .expireAfterWrite(600,TimeUnit.SECONDS)
 //统计缓存命中率
 .recordStats()
 .build();




 //get set 方法
 public Cache<String, Object> getTenMinuteCache() {
 return tenMinuteCache;
 }
 public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
 this.tenMinuteCache = tenMinuteCache;
 }
