package com.seerbigdata.sentinel.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2022/2/25 14:38
 * Content:
 */
public class SeerProducerConfig {

    @Bean
    @Autowired
    public KafkaTemplate<String,String> kafkaTemplate(ProducerFactory<String,String> producerFactory){
//        ArrayList<String> interceptors = new ArrayList<>();
//        interceptors.add("com.seerbigdata.sentinel.interceptor.SentinelHandlerInterceptor");
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);
//        producerFactory.updateConfigs(configMap);

        return new KafkaTemplate<>(producerFactory);
    }
}
