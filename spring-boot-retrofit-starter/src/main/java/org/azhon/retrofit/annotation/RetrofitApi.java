package org.azhon.retrofit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.annotation
 * 文件名:    a
 * 创建时间:  2019-04-25 on 16:32
 * 描述:     TODO retrofit自动扫描注入
 *
 * @author ZJB
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetrofitApi {
    /**
     * bean名字
     *
     * @return
     */
    String value() default "";

    /**
     * baseUrl地址
     */
    String url() default "";

    /**
     * client bean alias
     */
    String qualifier() default "";

    boolean primary() default true;

    /**
     * 自定义配置类
     */
    Class<?>[] configuration() default {};


    /**
     * 配置拦截器
     */
    Class<?>[] interceptor() default {};
}
