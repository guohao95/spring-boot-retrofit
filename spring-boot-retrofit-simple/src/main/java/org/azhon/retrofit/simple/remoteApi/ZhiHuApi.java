package org.azhon.retrofit.simple.remoteApi;

import com.netflix.hystrix.HystrixCommand;

import org.azhon.retrofit.annotation.RetrofitApi;
import org.azhon.retrofit.simple.bean.AllColumn;
import org.azhon.retrofit.simple.interceptor.InterceptorConfig;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.simple.remoteApi
 * 文件名:    ZhiHuApi
 * 创建时间:  2019-04-25 on 16:37
 * 描述:     TODO
 *
 * @author ZJB
 */
@RetrofitApi(value = "ZhiHuApi", url = "https://news-at.zhihu.com", interceptor = InterceptorConfig.class)
public interface ZhiHuApi {

    @GET("/api/4/news/latest")
    Call<String> getNews();

    @GET("/api/3/sections")
    Call<AllColumn> getAllColumn();


    @GET("/api/3/sections/err")
    HystrixCommand<AllColumn> returnHystrixCommandBody();

}
