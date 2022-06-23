package com.seerbigdata.sentinel.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.seerbigdata.sentinel.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @author hzk
 * @Classname TestService
 * @create 2022-06-16 17:18
 * @Description
 */
@Service
public class TestService {

    @Value("${spring.kafka.topicName}")
    private String topic;


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;




    @SentinelResource(value = "flowController")
    public String flowController(String name) {

               return "Hello, " + name;
    }

    @SentinelResource(value = "circuitBreaking")
    public String circuitBreaking(String name) throws InterruptedException {
        if (System.currentTimeMillis()%2==0){
            Thread.sleep(3);
        }
        return "Hello, " + name;
    }



    public String circuitBreakingByException(String name) {
        if (System.currentTimeMillis()%3==0){
           throw new NullPointerException();
        }
        return "Hello, " + name;
    }


    public Integer sendMessage() throws InterruptedException {
            JSONObject message = new JSONObject();
            String messageId =  UUID.randomUUID().toString();
            message.fluentPut("messageId", messageId);
            message.fluentPut("time", System.currentTimeMillis());
            System.out.println("发送的messageId:"+messageId);
            Entry entry=null;
            try {
                entry = SphU.entry( "sendMessageWithAnnotation");
                kafkaTemplate.send(topic, message.toString());
                return 1;
            }catch (BlockException e){
                Thread.sleep(1000);
                throw new GlobalException("限流了");
            }
            finally {
                if (null != entry) {
                    entry.exit();
                }
            }

    }

    public Integer sendMessageWithAnnotation() throws InterruptedException {
        JSONObject message = new JSONObject();
        String messageId =  UUID.randomUUID().toString();
        message.fluentPut("messageId", messageId);
        message.fluentPut("time", System.currentTimeMillis());
        System.out.println("发送的messageId:"+messageId);
        Thread.sleep(100);
        kafkaTemplate.send(topic, message.toString());
        return 1;
    }








}
