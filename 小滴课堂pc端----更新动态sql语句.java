
//创建一个动态sql语句类

/**
 * video构建动态sql语句，用于我们在更新数据时候，不打算更新全部信息，只更新部分信息时候用
 */
public class VideoProvider {
    /**
     * 更新video动态sql语句，
     * @param video
     * @return
     */
    public String updateVideo(final Video video){
        return new SQL(){{
            UPDATE("video");

            //条件写法.
            if(video.getTitle()!= null){
                SET("title=#{title}"); //这个title取上面video对象里的的值
            }
            if(video.getSummary()!= null){
                SET("summary=#{summary}");
            }
            if(video.getCoverImg()!=null){
                SET("cover_img=#{coverImg}");
            }
            if(video.getViewNum()!=null){
                SET("view_num=#{viewNum}");
            }
            if(video.getPrice()!=null){
                SET("price=#{price}");
            }
            if(video.getOnline()!=null){
                SET("online=#{online}");
            }
            if(video.getPoint()!=null){
                SET("point=#{point}");
            }

            WHERE("id=#{id}");
        }}.toString();
    }
}



================VideoMapper类============================

   
/**
     * 更新视频信息，使用了动态sql语句
     * @param video
     */
    // @Update("update  video set title =#{title} where id =#{id}")
    //使用动态sql语句,updateVideo是我们在provider类里的方法名称
     @UpdateProvider(type = VideoProvider.class,method ="updateVideo")
     public  int update(Video  video);

