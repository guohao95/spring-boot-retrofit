package org.azhon.retrofit.simple;

import org.azhon.retrofit.annotation.EnableRetrofitClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRetrofitClients("org.azhon")
public class RetrofitSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetrofitSimpleApplication.class, args);
    }

}

