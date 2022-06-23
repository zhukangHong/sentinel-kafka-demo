package com.seerbigdata.sentinel;

import com.seerbigdata.sentinel.annotation.EnableConsumer;
import com.seerbigdata.sentinel.annotation.EnableProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConsumer
@EnableProducer
public class SeerbigdataSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeerbigdataSentinelApplication.class, args);
    }

}
