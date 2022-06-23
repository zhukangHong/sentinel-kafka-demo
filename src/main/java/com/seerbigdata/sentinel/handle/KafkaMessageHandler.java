package com.seerbigdata.sentinel.handle;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2022/2/25 15:28
 * Content:
 */
@Component
public class KafkaMessageHandler {

    public void handler(ConsumerRecord<String,String> record){
        System.out.println("处理中");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("处理结束");


    }

}
