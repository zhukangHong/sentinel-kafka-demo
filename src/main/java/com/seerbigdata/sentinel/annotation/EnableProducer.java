package com.seerbigdata.sentinel.annotation;


import com.seerbigdata.sentinel.config.SeerProducerConfig;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2022/2/25 14:57
 * Content:
 * 开启生产者注解，自动注入kafka生产者
 * @notice: 注意：使用本注解前，需要在springboot启动配置文件中，设置kafka的基本配置
 * 包括：
 * 1、bootstrap-servers
 * 2、key和value的序列化器
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SeerProducerConfig.class)
public @interface EnableProducer {

}
