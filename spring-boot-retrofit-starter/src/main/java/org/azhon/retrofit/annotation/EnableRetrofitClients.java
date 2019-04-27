package org.azhon.retrofit.annotation;


/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.annotation
 * 文件名:    a
 * 创建时间:  2019-04-25 on 16:32
 * 描述:     TODO 开启retrofit自动配置
 *
 * @author ZJB
 */


import org.azhon.retrofit.core.RetrofitClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RetrofitClientsRegistrar.class)
public @interface EnableRetrofitClients {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}
