package org.azhon.retrofit.simple.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 项目名:    ziding-whp
 * 包名       com.ziding.baidu.interceptor
 * 文件名:    InterceptorConfig
 * 创建时间:  2019-04-22 on 11:23
 * 描述:     TODO
 *
 * @author ZJB
 */
@Configuration
@Slf4j
public class InterceptorConfig {

    /**
     * 请求日志
     *
     * @return
     */
    @Bean
    public Interceptor logInterceptor() {
        HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor(message -> {
            if (message.startsWith("-->")){
                log.info(message);
            }
            if (message.startsWith("{")){
                log.info(message);
            }
        });
        interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptorBody;
    }

}
