package org.azhon.retrofit.core;


/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.core
 * 文件名:    RetrofitContext
 * 创建时间:  2019-04-25 on 16:31
 * 描述:     TODO
 *
 * @author ZJB
 */
public class RetrofitContext extends NamedContextFactory<RetrofitClientSpecification> {

    public RetrofitContext() {
        super(RetrofitClientsConfiguration.class, "retrofit", "retrofit.client.name");
    }
}
