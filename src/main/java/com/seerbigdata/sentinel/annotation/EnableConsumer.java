package com.seerbigdata.sentinel.annotation;

import com.seerbigdata.sentinel.config.SeerConsumerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2022/2/25 14:57
 * Content:
 * 开启消费者注解，自动注入kafka消费者，消费者消息处理需要实现SeerMessageHandlerService接口；
 * 否则会默认由DefaultSeerMessageHandlerServiceImpl实现类处理；
 * @notice: 注意：使用本注解前，需要在springboot启动配置文件中，设置kafka的基本配置
 * 包括：
 * 1、bootstrap-servers；
 * 2、key和value反序列化器
 * 3、group-id
 * 4、自定义需要监听的主题的spring.kafka.topicName，监听的主题名称可以写多个，以逗号隔开。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeerConsumerConfig.class)
public @interface EnableConsumer {

}
