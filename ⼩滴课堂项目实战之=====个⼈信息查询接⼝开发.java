
//UserController层

/**
     * 个⼈信息查询接⼝开发,根据id查询用户信息
       根据token查询个⼈信息接⼝开发
       直接解密token，获取个⼈信息
       通过token解密查询数据库获取个⼈信息
     */
      @GetMapping("find_by_token")
      public JsonData  findUserInfoByToken(HttpServletRequest request){

          //我们从拦截器那拿的LoginlnterCeptor：request.setAttribute("user_id", id),他传给了controller层
          Integer  user_id   = (Integer) request.getAttribute("user_id");

          if(user_id==null){
             JsonData.buildError("查询失败");
          }
          User user  = userService.findByUserId(user_id);

          return  JsonData.buildSuccess(user);
      }



//UserServiceImpl层
 /**
     *  根据id 查询用户信息
     *  个⼈信息查询接⼝开发,根据id查询用户信息
        根据token查询个⼈信息接⼝开发
       直接解密token，获取个⼈信息
       通过token解密查询数据库获取个⼈信息
     * @param user_id
     * @return
     */
        @Override
       public User findByUserId(Integer user_id) {
            User  user   =   userMapper.findByUserId(user_id);
            return user;
    }
}


//UserMapper层

       /* 根据id 查询用户信息
          个⼈信息查询接⼝开发,根据id查询用户信息*/

      public  User findByUserId(@Param("user_id") Integer user_id);





//UserMapper.xml配置文件里的查询语句

<!-- 根据id 查询用户信息
    个⼈信息查询接⼝开发,根据id查询用户信息-->
     <select id="findByUserId" resultType="User">
          select * from  user where  id =#{user_id}
     </select>


