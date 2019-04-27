package org.azhon.retrofit.core;

import com.google.gson.GsonBuilder;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.core
 * 文件名:    a
 * 创建时间:  2019-04-25 on 16:31
 * 描述:     TODO
 *
 * @author ZJB
 */
@Configuration
public class RetrofitClientsConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient.Builder okHttpClient() {
        return new OkHttpClient.Builder();
    }


    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * Scalars转换
     *
     * @return
     */
    @Bean
    public Converter.Factory ScalarsConverterFactory() {
        return ScalarsConverterFactory.create();
    }

    /**
     * Gson转换
     *
     * @return
     */
    @Bean
    public Converter.Factory GsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder()
                .setLenient()
                .create()
        );
    }
}
