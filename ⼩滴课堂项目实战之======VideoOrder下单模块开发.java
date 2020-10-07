//VideoOrderController层

/**
 * ⼩滴课堂实战之VideoOrder订单模块开发
 * 不⽤权限可以访问url /api/v1/pub/
 * 要登录可以访问url /api/v1/pri/
 */
@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {

                @Autowired
                private VideoOrderService   videoOrderService;
    /**
     * 订单接口
     * @return
     * 提交表单或者参数特别多时候,因为我们都是以json格式提交数据的，这时候我们要加一个注解@RequestBody
     * 添加HttpServletRequest，为了从拦截器解密之后得到的用户信息，查询到谁下单的:：request.setAttribute("user_id", id);
     */
    @RequestMapping("save")
       public JsonData  save(@RequestBody VideoOrderRequest   orderRequest, HttpServletRequest request){
           Integer  user_id   = (Integer) request.getAttribute("user_id");//获取到用户id,查询谁下单
            int   rows = videoOrderService.save(user_id,orderRequest.getVideoId());

            return rows == 0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess("下单成功");
       }


//VideoOrderServiceImpl实现类

@Service(value ="VideoOrderService")
public class VideoOrderServiceImpl implements VideoOrderService {

        //订单Mapper
         @Autowired
         private VideoOrderMapper  videoOrderMapper;


         //视频Mapper
          @Autowired
         private VideoMapper   videoMapper;

    /**
     * 订单模块开发
     *  优惠券抵扣   风控用户检测   生成订单基础信息   生成支付信息
     */
    @Override
    public int save(int userId, int videoId) {
        //判断是否已经购买，
        VideoOrder   videoOrder  = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);
           //0之后没有下单成功，但是他可能已经支付了，判断是否下单成功
            if(videoOrder!=null){return 0;}


            //查询Video信息，简单的一个视频商品信息查询，通过Video拿到新订单的信息
             Video  video  =    videoMapper.findById(videoId);

              //构建一个新的订单
                VideoOrder   newVideoOrder  = new VideoOrder();

                //设置订单创建时间
                newVideoOrder.setCreateTime(new Date());
                //创建订单标识==订单号
                newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
                //状态码
                newVideoOrder.setState(1);
                //支付金额
                newVideoOrder.setTotalFee(video.getPrice());
                //获取用户信息
                newVideoOrder.setUserId(userId);
                  //属于哪个视频
                newVideoOrder.setVideoId(videoId);
                //封面图
                newVideoOrder.setVideoImg(video.getCoverImg());
                //视频标题
                newVideoOrder.setVideoTitle(video.getTitle());

                //调用下单方法
            int  rows =  videoOrderMapper.saveOrder(newVideoOrder);
            return rows;

    }



    //VideoMapper


@Component
public interface VideoMapper {


    /**
     * 查询全部视频列表
     */
    public List<Video> listVideo();



    /**
     * 根据id查询视频详情信息，包含章，集
     * @param videoId
     * @return
     * 需要使用MyBatis关联复杂查询
     */
    public  Video findDetailById(@Param("video_id") int videoId);






    /**
     * 查询Video信息，简单的一个视频商品信息查询,用在VideoOrder
     * 订单VideoOrderServiceImpl调用，简单查询视频信息,
     * @param videoId
     */
       public  Video findById(@Param("video_id") int videoId);


}




//VideoOrderMapper接口

@Component
public interface VideoOrderMapper {

/**
 * 订单模块开发
 *
 * 查询用户是否购买过此商品,就是说根据用户和商品id，查询订单
 * 用户id  ,视频Id     最后是状态码
 */
  public  VideoOrder   findByUserIdAndVideoIdAndState(@Param("user_id") int userId , @Param("video_id") int videoId , @Param("state") int state);






    /**
     * 上面查询用户是否购买过商品，==0，就生成新的订单
     *  下单
     * @return
     */
     public   int   saveOrder(VideoOrder videoOrder);

}







//VideoOrderMappper.xml配置文件


<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="net.xdclass.online_xdclass.mapper.VideoOrderMapper">

     <!--订单模块开发

     查询用户是否购买过此商品,  就是说根据用户id和商品id和状态码，查询订单
     用户id  ,视频Id     最后是状态码-->
       <select id="findByUserIdAndVideoIdAndState" resultType="VideoOrder">

              select * from video_order  where  user_id = #{user_id} and  video_id = #{video_id} and state = #{state}

       </select>






      <!--上面查询没有购买过，就生成订单-->  <!--获取主键userGeneratedKeys,keyColumn:数据库表id，keyProperty:实体类属性id-->
       <insert id="saveOrder" useGeneratedKeys="true" keyColumn="id" keyProperty="id">

           INSERT INTO `video_order` ( `out_trade_no`, `state`, `create_time`, `total_fee`, `video_id`, `video_title`, `video_img`, `user_id`)
              VALUES (#{outTradeNo,jdbcType=VARCHAR},#{state,jdbcType=INTEGER},#{createTime,jdbcType=TIMESTAMP},
              #{totalFee,jdbcType=INTEGER},#{videoId,jdbcType=INTEGER},#{videoTitle,jdbcType=VARCHAR},#{videoImg,jdbcType=VARCHAR},#{userId,jdbcType=INTEGER});


       </insert>
</mapper>
