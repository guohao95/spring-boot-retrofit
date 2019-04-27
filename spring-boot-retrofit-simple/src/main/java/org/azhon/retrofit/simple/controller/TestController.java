package org.azhon.retrofit.simple.controller;

import org.azhon.retrofit.simple.bean.AllColumn;
import org.azhon.retrofit.simple.remoteApi.ZhiHuApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import lombok.AllArgsConstructor;

/**
 * 项目名:    spring-boot-retrofit
 * 包名       org.azhon.retrofit.simple.controller
 * 文件名:    TestController
 * 创建时间:  2019-04-25 on 16:36
 * 描述:     TODO
 *
 * @author ZJB
 */
@AllArgsConstructor
@RestController()
public class TestController {

    ZhiHuApi zhiHuApi;

    @GetMapping("/news/latest")
    public String getNewsLatest() throws IOException {
        return zhiHuApi.getNews().execute().body();
    }


    @GetMapping("/all/column")
    public AllColumn getAllColumn() throws IOException {
        return zhiHuApi.getAllColumn().execute().body();
    }

    @GetMapping("/all/column/hystrix")
    public AllColumn getAllColumnHystrix() throws IOException {
        return zhiHuApi.returnHystrixCommandBody().execute();
    }
}
