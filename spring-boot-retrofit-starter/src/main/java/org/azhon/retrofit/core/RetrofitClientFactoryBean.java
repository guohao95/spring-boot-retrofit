package org.azhon.retrofit.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.util.Map;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.core
 * 文件名:    a
 * 创建时间:  2019-04-25 on 16:31
 * 描述:     TODO
 *
 * @author ZJB
 */
@Data
@Slf4j
public class RetrofitClientFactoryBean implements FactoryBean<Object>, InitializingBean,
        ApplicationContextAware {

    private Class<?> type;

    private String name;

    private String url;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.name, "name must be set");
    }

    @Override
    public Object getObject() throws Exception {
        RetrofitContext context = applicationContext.getBean(RetrofitContext.class);

        Retrofit.Builder builder = retrofit(context);

        return builder.baseUrl(url).build().create(type);
    }

    protected <T> T getInstance(RetrofitContext context, Class<T> type) {
        T instance = context.getInstance(this.name, type);
        if (instance == null) {
            throw new IllegalStateException("No bean found of type " + type + " for "
                    + this.name);
        }
        return instance;
    }

    protected OkHttpClient getHttpClient(RetrofitContext context) {
        OkHttpClient.Builder builder = getInstance(context, OkHttpClient.Builder.class);
        //获取自定义拦截器
        Map<String, Interceptor> optionals = getOptionals(context, Interceptor.class);
        if (optionals != null) {
            for (Interceptor value : optionals.values()) {
                if (value != null) {
                    builder.addInterceptor(value);
                }
            }
        }

        return builder.build();
    }

    protected Retrofit.Builder retrofit(RetrofitContext context) {
        Retrofit.Builder builder = getInstance(context, Retrofit.Builder.class);

        OkHttpClient httpClient = getHttpClient(context);
        builder.client(httpClient);

        //获取自定义转换工厂
        Map<String, Converter.Factory> optionals = getOptionals(context, Converter.Factory.class);
        if (optionals != null) {
            for (Converter.Factory factory : optionals.values()) {
                if (factory != null) {
                    builder.addConverterFactory(factory);
                }
            }
        }

        //获取自定义返回工厂
        Map<String, Call.Factory> optionals1 = getOptionals(context, Call.Factory.class);
        if (optionals1 != null) {
            for (Call.Factory factory : optionals1.values()) {
                if (factory != null) {
                    builder.callFactory(factory);
                }
            }
        }
        return builder;
    }

    protected <T> T getOptional(RetrofitContext context, Class<T> type) {
        return context.getInstance(this.name, type);
    }

    protected <T> Map<String, T> getOptionals(RetrofitContext context, Class<T> type) {
        return context.getInstances(this.name, type);
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
