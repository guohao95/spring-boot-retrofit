package org.azhon.retrofit.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;



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
public class RetrofitAutoConfiguration {

    @Autowired(required = false)
    private List<RetrofitClientSpecification> configurations = new ArrayList<>();

    @Bean
    public RetrofitContext retrofitContext() {
        RetrofitContext context = new RetrofitContext();
        context.setConfigurations(this.configurations);
        return context;
    }
}
