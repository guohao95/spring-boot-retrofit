package org.azhon.retrofit.hystrix;

import com.netflix.hystrix.HystrixCommand;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import lombok.Setter;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Completable;
import rx.Observable;
import rx.Single;


/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.hystrix
 * 文件名:    HystrixCallAdapterFactory
 * 创建时间:  2019-04-25 on 17:47
 * 描述:     TODO
 *
 * @author ZJB
 */
@Setter
public class HystrixCallAdapterFactory extends CallAdapter.Factory {

    private String commandGroup;

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType == Call.class) {
            return null;
        }
        boolean isHystrixCommand = rawType == HystrixCommand.class;
        boolean isObservable = rawType == Observable.class;
        boolean isSingle = rawType == Single.class;
        boolean isCompletable = rawType == Completable.class;
        boolean isResponse = rawType == Response.class;

        Type responseType;
        boolean isBody = false;

        if (!isHystrixCommand
                && !isObservable
                && !isSingle
                && !isCompletable
                && !isResponse) {
            return new HystrixCallAdapter<>(returnType, false, true, false,
                    false, false, false, commandGroup);
        }

        if (isCompletable) {
            return new HystrixCallAdapter<>(Void.class, false, false, false,
                    false, false, true, commandGroup);
        }

        if (!(returnType instanceof ParameterizedType)) {
            String name = isHystrixCommand ? "HystrixCommand" : isObservable ? "Observable"
                    : isSingle ? "Single" : "Response";
            throw new IllegalStateException(name + " return type must be parameterized"
                    + " as " + name + "<Foo> or " + name + "<? extends Foo>");
        }

        if (isResponse) {
            responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
            return new HystrixCallAdapter<>(responseType, true, false, isHystrixCommand,
                    isObservable, isSingle, false, commandGroup);
        }

        Type commandType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawCommandType = getRawType(commandType);
        if (rawCommandType == Response.class) {
            if (!(commandType instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized"
                        + " as Response<Foo> or Response<? extends Foo>");
            }
            responseType = getParameterUpperBound(0, (ParameterizedType) commandType);
        } else {
            responseType = commandType;
            isBody = true;
        }
        return new HystrixCallAdapter(responseType, isResponse, isBody, isHystrixCommand,
                isObservable,
                isSingle, isCompletable, commandGroup);
    }
}
