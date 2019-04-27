package org.azhon.retrofit.hystrix;

import com.netflix.hystrix.HystrixCommand;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import retrofit2.CallAdapter;



/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.hystrix
 * 文件名:    HystrixAutoConfiguration
 * 创建时间:  2019-04-25 on 17:47
 * 描述:     TODO
 *
 * @author ZJB
 */
@Configuration
@ConditionalOnClass({ HystrixCommand.class })
public class HystrixAutoConfiguration {

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty(name = "retrofit.hystrix.enabled", havingValue = "true")
    @ConditionalOnMissingBean
    public CallAdapter.Factory hystrixCallAdapterFactory() {
        return new HystrixCallAdapterFactory();
    }
}
