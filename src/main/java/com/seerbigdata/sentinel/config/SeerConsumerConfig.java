package com.seerbigdata.sentinel.config;


import com.alibaba.fastjson.JSONObject;
import com.seerbigdata.sentinel.handle.KafkaMessageHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2022/2/25 14:38
 * Content:
 */
@Component
public class SeerConsumerConfig {

    public static final Logger log = LoggerFactory.getLogger(SeerConsumerConfig.class);

    @Autowired
    private KafkaMessageHandler kafkaMessageHandler;

    /**
     * 消息监听获取并处理
     * @param record
     */
    @KafkaListener(topics = "sentinel_test")
    public void onMessage(ConsumerRecord<String, String> record , Acknowledgment ack){
        try {
            log.info("================收到消息：" + JSONObject.parseObject(record.value(),JSONObject.class).getObject("messageId",String.class)   + "========================");
            // 调用消息处理接口处理消息
            kafkaMessageHandler.handler(record);
        }catch (Exception e){
            log.error("================处理消息失败：" + record + "========================",e);
            e.printStackTrace();
        }finally {
            ack.acknowledge();
        }

    }

}
