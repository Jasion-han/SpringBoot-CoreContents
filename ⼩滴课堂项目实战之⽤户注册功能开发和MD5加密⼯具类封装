//UserMapper.xml配置文件：

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="net.xdclass.online_xdclass.mapper.UserMapper">

          <!--
          ⼩滴课堂实战之⽤户注册功能开发
          -->
         <!--插入一个用户，就是新注册一个用户-->
         <insert id="save" parameterType="User">
          insert into  user(name,pwd,head_img,phone,create_time)
          values(#{name,jdbcType=VARCHAR},#{pwd,jdbcType=VARCHAR},#{headImg,jdbcType=VARCHAR},#{phone,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP})
         </insert>




         <!--根据手机号查询用户信息，数据库有电话号码就不能注册，一个手机号只能注册一个用户
             数据库表phone,电话可以增加一个唯一索引
             这个还未写
         -->
        <select id="findByPhone" resultType="User">

              select * from user where  phone = #{phone}

        </select>

</mapper>




//dao层

package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * ⼩滴课堂实战之⽤户注册功能开发,MD5加密⼯具类封装
 *
 */

@Component
public interface UserMapper {


    /**
     * 新增用户
     */
      public  int  save(User user);


    /**
     * 根据手机号查询用户信息数据库有电话号码就不能注册，一个手机号只能注册一个用户
       数据库表phone,电话可以增加一个唯一索引
     @Param("phone"):别名
     */
      public User findByPhone(@Param("phone") String phone);

}







 /**
     * MD5加密工具类
     * @param data
     * @return
     */
ublic class CommonUtils {
    
    public static String MD5(String data) {
        try {
            java.security.MessageDigest md =
                    MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) |
                        0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;
    }
}















//serviceImpl实现类 ，包含Md5加密工具类


@Service(value ="userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 新增用户
     */

    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user != null) {
            return userMapper.save(user);
        } else {
            return -1;
        }

    }


    /*将Map转成User

        解析user对象
    */
    private User parseToUser(Map<String, String> userInfo) {
        if (userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")) {

            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getrandomImg());  /*=====将方法名称传进来，随机头像==============*/
            user.setPhone(userInfo.get("phone"));
            user.setCreateTime(new Date());

            String pwd = userInfo.get("pwd");
               /*将密码加密成MD5*/
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        } else { /*如果其中一个为null*/
            JsonData.buildErrorr(-1, "数据异常");

        }
        return null;
    }


    /**
     ================随机头像⽣成=======================
     放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cnbeijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    /*获取随机数组头像，*/
     private  String  getrandomImg(){
         int  size  =headImg.length;
         Random   random  =  new Random();
         /*获取一个随机下标，把长度传进去*/
           int  index  = random.nextInt(size);
           /*返回一个随机头像*/
           return headImg[index];
     }





    /**
     * 根据手机号查询用户信息数据库有电话号码就不能注册，一个手机号只能注册一个用户
     * 数据库表phone,电话可以增加一个唯一索引
     */
    //这个方法还没写
    @Override
    public User findByPhone(String phone) {
        return null;
    }
}



//controller类

/**
 * ⼩滴课堂实战之⽤户注册功能开发,MD5加密⼯具类封装
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

           @Autowired
          private UserService   userService;

    /**
     * 提交表单参数特别多时候,因为我们都是以json格式提交数据的，
     * 这时候我们要加一个注解@RequestBody
     *
     * 新增一个用户
     */
     
       @PostMapping("register")
      public JsonData register(@RequestBody Map<String,String> userInfo){
               int  rows =userService.save(userInfo);
               return rows==1 ? JsonData.buildSuccess(): JsonData.buildError("注册失败,请重试");
      }

}
