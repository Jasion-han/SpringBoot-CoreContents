//videoOrderServiceImpl类


@Service(value ="VideoOrderService")
public class VideoOrderServiceImpl implements VideoOrderService {

        //订单Mapper
         @Autowired
         private VideoOrderMapper  videoOrderMapper;


         //视频Mapper
          @Autowired
         private VideoMapper   videoMapper;


          //注入集的Mapper
           @Autowired
          private EpisodeMapper   episodeMapper;

           //注入播放记录Mapper
           @Autowired
          private PlayRecordMapper   playRecordMapper;


    /**
     * 订单模块开发,下单
     *  优惠券抵扣   风控用户检测   生成订单基础信息   生成支付信息
     */
    @Override
    @Transactional  //配置事务的属性，如果数据产生异常，数据将不会插入数据库.配置事务的重要性
    public int save(int userId, int videoId) {
        //判断是否已经购买，
        VideoOrder   videoOrder  = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);
           //如果！=null,就是下单了，然后return 0 不能购买了，等于null,没有订单,就走下边创建一个新订单,但是他可能已经支付了购买了，就不走下边 newVideoOrder
            if(videoOrder!=null){return 0;}


            //查询Video信息，就是用户购买39号视频产品,查出来这个商品之后，付给下边的新订单.简单的一个视频商品信息查询，通过Video将43视频属性，
        // 复制给新订单，拿到新订单的信息
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


        /**
         * 课堂之播放记录表设计和模块开发，生成订单就给他生成播放记录
         */
             if(rows==1){
                 //通过用户传入的videoId查询到用户购买的视频和视频第一集,这些操作是调用 集Mapper里的方法
                Episode  episode = episodeMapper.findFirstEpisode(videoId);
                 if(episode==null){
                     throw  new XDException(-1,"视频没有集信息，请运营人员检查");//抛一个自定义异常，
                     //在自定义异常类，将异常返回给前端，方便运营人员排查
                 }
                  //生成一个播放记录对象，通过上面拿到的集episode设置它的属性
                 PlayRecord playRecord  =  new PlayRecord();
                           playRecord.setCreateTime(new Date());
                           playRecord.setCurrentNum(episode.getNum());
                           playRecord.setEpisodeId(episode.getId());
                           playRecord.setUserId(userId);
                           playRecord.setVideoId(videoId);
                      //将对像传进来
                           playRecordMapper.saveRecord(playRecord);//这出毛病了


             }
        return rows;

    }
}





//集Mapper里的方法

/**
 * 集Mapper
 *
 */
@Component
public interface EpisodeMapper {


    /**
     * ⼩滴课堂实战之播放记录表设计和模块开发
     * @param videoId
     * @return
     */
        public Episode   findFirstEpisode(@Param("video_id") int videoId);
}






//注入播放记录Mapper,⼩滴课堂实战之播放记录表设计和模块开发

@Component
public interface PlayRecordMapper {


    /**
     * 播放记录方法
     * @param playRecord
     * @return
     */
    public   int  saveRecord(PlayRecord playRecord);




}





//用到自定义异常类

/**
 * 自定义异常处理器，捕获异常，然进行判断，全局异常处理
 */

@RestControllerAdvice
public class CustomExceptionHandler {

    /**记录对应日志
     * @param e
     * @return
     */

     private  final static Logger  logger = LoggerFactory.getLogger(CustomExceptionHandler.class);



    @ExceptionHandler(value = Exception.class)
    @ResponseBody  /*传到前端*/
    public JsonData handler(Exception e){
             /*打印异常*/
            logger.error("[系统异常]{}",e.getMessage());//返回给前端异常打印

           /*判断是否是自定义异常*/
            if(e instanceof XDException){
                /*如果是,强转成自定义异常*/
                XDException  xdException  = (XDException) e;

                return  JsonData.buildErrorr(xdException.getCode(),xdException.getMsg());

            }else{
                  return  JsonData.buildError("全局异常,未知错误");
            }
    }

}




//视频Mapper也得注入
 /**
     * 查询Video信息，简单的一个视频商品信息查询,用在VideoOrder
     * 订单VideoOrderServiceImpl调用，简单查询视频信息,
     * @param videoId
     */
       public  Video findById(@Param("video_id") int videoId);


}







//主启动类配置事务

/*@MapperScan("Mapper文件所在的包")，@MapperScan 是扫描mapper类的注解，就不用在每个mapper类上加@MapperScan了
   必须加这个，否则不能识别dao层
* */
@SpringBootApplication
@MapperScan("net.xdclass.online_xdclass.mapper")
@EnableTransactionManagement   /*开启spring对注解事务的支持*/
public class OnlineXdclassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineXdclassApplication.class, args);
	}

}


