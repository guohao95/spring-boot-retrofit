package org.azhon.retrofit.core;

import java.util.Arrays;
import java.util.Objects;

import lombok.Data;


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
public class RetrofitClientSpecification implements NamedContextFactory.Specification {

    private String name;

    private Class<?>[] configuration;

    public RetrofitClientSpecification() {
    }

    public RetrofitClientSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrofitClientSpecification that = (RetrofitClientSpecification) o;
        return Objects.equals(name, that.name) &&
                Arrays.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, configuration);
    }

    @Override
    public String toString() {
        return new StringBuilder("RetrofitClientSpecification{")
                .append("name='").append(name).append("', ")
                .append("configuration=").append(Arrays.toString(configuration))
                .append("}").toString();
    }

}
