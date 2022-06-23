package com.seerbigdata.sentinel.interceptor;

/**
 * @author hzk
 * @Classname SentinelHandlerInterceptor
 * @create 2022-06-20 14:16
 * @Description
 */

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.seerbigdata.sentinel.exception.GlobalException;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * Sentinel限流
 */
@Component
public class SentinelHandlerInterceptor implements ProducerInterceptor<String,Object> {

    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        Entry entry = null;
        try {
            entry = SphU.entry( "sendMessage");
            return producerRecord;
        } catch (BlockException e) {
            //睡眠一会，防止消息一直进入队列
            System.out.println("限流了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                System.out.println("中断异常");
            }
            return null;
            //throw new GlobalException("限流了...");
        }
        finally {
            if (null != entry) {
                entry.exit();
            }
        }
    }



    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}