//VidoOrderController层

    /**
     * 用户登录之后，然后用户下单成功后，查询她的订单列表
     */
      @GetMapping("list")
      public JsonData   listOrder(HttpServletRequest request){
              //先查询出用户的id
             Integer    userId = (Integer) request.getAttribute("user_id");
               //根据用户的id,查出订单
             List<VideoOrder>  videoOrderList = videoOrderService.listOrderByUserId(userId);
                  return JsonData.buildSuccess(videoOrderList);

      }


      videoOrderServiceImpl层
      /**
     * ⼩滴课堂实战之订单列表接⼝开发
     */
    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {

           return  videoOrderMapper.listOrderByUserId(userId);





 //videoOrderMapper
    /**
     * ⼩滴课堂实战之订单列表接⼝开发
     */
     public  List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);
}

}



//videoOrderMapper.xml
             
  <!-- ⼩滴课堂实战之订单列表接⼝开发-->
        <select id="listOrderByUserId" resultType="VideoOrder">
            select * from  video_order where user_id = #{user_id} order by create_time  desc
        </select>


    
