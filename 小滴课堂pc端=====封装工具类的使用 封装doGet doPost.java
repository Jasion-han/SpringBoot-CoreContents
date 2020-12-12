//首先加入依赖

 1、加入依赖
        <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpclient</artifactId>
                <version>4.5.3</version>
            </dependency>
            <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpmime</artifactId>
                <version>4.5.2</version>
            </dependency>

            <dependency>
                <groupId>commons-codec</groupId>
                <artifactId>commons-codec</artifactId>
            </dependency>
            <dependency>
                <groupId>commons-logging</groupId>
                <artifactId>commons-logging</artifactId>
                <version>1.1.1</version>
            </dependency>
                    <dependency>
                    <groupId>org.apache.httpcomponents</groupId>
                    <artifactId>httpcore</artifactId>
            </dependency>


        <!-- gson工具，封装http的时候使用 -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.0</version>
        </dependency>




//在utils包下创建一个 HttpUtils工具类，代码如下:


/**
 * 封装http。  get 和 post方法
 */
public class HttpUtils {
            //new Gson对象，这是
            private  static  final Gson   gson  =  new  Gson();

    /**
     * get方法   用在微信登录
     * @param url
     * @return
     */
    public  static Map<String,Object>  doGet(String url){

               //将jsonResult字符串请求转成map形式，在这new Hashmap
                Map<String,Object>  map  =  new HashMap<>();

              //拿到客户端,准备向微信发送请求，获取用户的信息和token
             CloseableHttpClient   httpClient = HttpClients.createDefault();


        //建立超时连接，请求微信端，必须设置连接超时时间，
        RequestConfig    requestConfig  =  RequestConfig.custom().setConnectTimeout(5000) //连接超时
                              .setConnectionRequestTimeout(5000) //请求超时时间
                              .setSocketTimeout(5000)    //socket连接超时
                              .setRedirectsEnabled(true)  //允许重定向
                              .build();

               //new HttpGet请求对象
                 HttpGet  httpGet  =  new   HttpGet(url);
                 httpGet.setConfig(requestConfig); //给Get请求设置超时时间



                  try{
                      //向微信端发送get请求，获取到code和token
                      HttpResponse    httpResponse   = httpClient.execute(httpGet);
                      //拿到响应，判断如果请求状态码是200，就是成功的
                      if(httpResponse.getStatusLine().getStatusCode()==200){
                           //拿到结果，转成字符串
                           String  jsonResult = EntityUtils.toString(httpResponse.getEntity());
                              //最终需要key value 形式 我们用Gson工具转成Mapper,现在上面new一个mapper,赋值给map
                               map = gson.fromJson(jsonResult,map.getClass());

                      }
                  }catch (Exception e){
                           e.printStackTrace();
                  }finally {

                      try {

                          httpClient.close();

                      } catch (IOException e) {
                          e.printStackTrace(); //并发量高时候，可以不用答应，影响性能
                      }
                  }
        return map;

        }


    /**
     * 封装post方法，用在微信支付方面
     * @param url
     * @return
     */
    public  static  String doPost(String url,String  data, int timout){


        //拿到客户端,准备向微信发送请求，获取用户的信息和token
        CloseableHttpClient   httpClient = HttpClients.createDefault();

        //建立超时连接，请求微信端，必须设置连接超时时间，
        RequestConfig    requestConfig  =  RequestConfig.custom().setConnectTimeout(5000) //连接超时
                .setConnectionRequestTimeout(timout) //请求超时时间
                .setSocketTimeout(timout)    //socket连接超时
                .setRedirectsEnabled(true)  //允许重定向
                .build();

                 //new post请求
                 HttpPost   httpPost  = new HttpPost(url);
                     httpPost.setConfig(requestConfig);//给post请求设置超时时间


              //头部信息
               httpPost.addHeader("Content-Type","text/html;chartset=UTF-8");
               //我们这边不能把参数放进url里面，因我们这边是把参数传进来，判断不为Null,并且是字符串子类
               if(data!=null && data instanceof String ){
                   StringEntity   stringEntity  =  new StringEntity(data,"UTF-8");
                   //封装
                      httpPost.setEntity(stringEntity);
               }

                try {

                    //向微信端发送post请求，获取到code和token
                    CloseableHttpResponse httpResponse  =  httpClient.execute(httpPost);
                          //拿到结果
                         HttpEntity httpEntity  =  httpResponse.getEntity();
                    //拿到响应，判断如果请求状态码是200，就是成功的
                    if(httpResponse.getStatusLine().getStatusCode()==200){

                          //将结果转成字符串
                            String   result  = EntityUtils.toString(httpEntity);
                              return  result;
                    }
                }catch (Exception e){

                   e.printStackTrace();
                }finally {


                     try{
                         httpClient.close();//关闭

                     }catch (Exception e){
                         e.printStackTrace();
                     }
                }

            return null;
        }
    }
