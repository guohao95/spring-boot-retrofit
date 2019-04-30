<p align="center">
<h1>spring-boot-retrofit</h1>
  <a>
    <img src="https://img.shields.io/badge/retrofit-2.4.0-brightgreen.svg" alt="vue">
  </a>
  <a >
    <img src="https://img.shields.io/badge/hystrix-1.5.12-brightgreen.svg" alt="vue">
  </a>
  <a >
    <img src="https://img.shields.io/badge/license-Apache%202.0-green.svg" alt="license">
  </a>
</p>

# spring-boot-retrofit

### 项目介绍
1. 使用retrofit2.0封装微服务外部调用框架，使用简单方便，
2. 已经集成GSON解析，string解析，hystrix降级，
3. springboot自动集成
4. 自定义拦截器，自定义配置retrofit

``` lua
spring-boot-retrofit
├── spring-boot-retrofit-simple   --  简单使用
├── spring-boot-retrofit-starter  --  库
```

### 使用教程
1.开启springboot自动配置，配置包扫描@RetrofitApi注解
``` 
@EnableRetrofitClients("org.azhon")

``` 
2.编写接口
> 完全使用原生retrofit2.0注解

``` 
@RetrofitApi(value = "ZhiHuApi", url = "https://news-at.zhihu.com", interceptor = InterceptorConfig.class)
public interface ZhiHuApi {

    @GET("/api/4/news/latest")
    Call<String> getNews();

    @GET("/api/3/sections")
    Call<AllColumn> getAllColumn();


    @GET("/api/3/sections/err")
    HystrixCommand<AllColumn> returnHystrixCommandBody();

}
``` 
