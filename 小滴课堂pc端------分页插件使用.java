PageHelper分页插件使用
	简介：讲解开源组件，mybaits分页插件的使用




  //首先在pom文件里，写入分页插件的依赖
    <dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper</artifactId>
			<version>4.1.0</version>
		</dependency>




//创建分页插件的配置类

/**
 * mybatis分页插件的配置文件
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","true");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}



//使用分页插件,在视频列表使用

   /**
     * 视频列表， 分页接口 ,使用了分页插件依赖，分页配置类是MyBatisConfig
     * @param page  当前第几页, 默认是第一页
     * @param size  页数，每页显示几条
     */
     @GetMapping("page")
     public JsonData pageVideo(@RequestParam(value ="page",defaultValue ="1")int page, //分页  第一页
                               @RequestParam(value ="size",defaultValue ="10")int size){ //页数，10页

           PageHelper.startPage(page,size);
           List<Video>  videoList  = videoService.findAll();//把videoList当构造函数传进去
             PageInfo<Video> pageInfo = new PageInfo<>(videoList);//总数据
               //将主要的信息封装到Map里，传给前端
                Map<String,Object>  data  =  new HashMap<>();
                     data.put("total_size",pageInfo.getTotal());//总条数
                     data.put("total_page",pageInfo.getPages()); //总页数
                     data.put("current_page",page); //当前页
                      data.put("datas",pageInfo.getList());//数据

            //返回data对象
             return  JsonData.buildSuccess(data);
     }





