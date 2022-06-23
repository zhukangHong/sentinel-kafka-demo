package com.seerbigdata.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.seerbigdata.sentinel.common.AjaxResult;
import com.seerbigdata.sentinel.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzk
 * @Classname TestController
 * @create 2022-06-16 17:12
 * @Description
 */
@RestController
public class TestController {

    @Autowired
    private TestService service;


    /**
     * 限流
     * @return
     */
    @GetMapping(value = "/flowController")
    public AjaxResult apiFlowControl() {
        return AjaxResult.success(service.flowController("限流"));
    }


    /**
     * 超时熔断
     * @return
     * @throws InterruptedException
     */
    @GetMapping(value = "/circuitBreakingByRt")
    public AjaxResult circuitBreakingByRt() throws InterruptedException {
        return AjaxResult.success(service.circuitBreaking("慢调用熔断")) ;
    }


    /**
     * 异常熔断
     * @return
     */
    @GetMapping(value = "/circuitBreakingByException")
    @SentinelResource(value = "circuitBreakingByException",blockHandler = "blockHandler")
    public AjaxResult circuitBreakingByException() {
        return AjaxResult.success(service.circuitBreakingByException("异常熔断")) ;
    }


    /**
     * MQ发消息
     * @return
     */
    @GetMapping(value = "/sendMessage")
    public AjaxResult sendMessage() throws InterruptedException {
        return AjaxResult.success(service.sendMessage()) ;
    }

    /**
     * 注解控制快速失败
     * @return
     */
    @GetMapping(value = "/sendMessageWithAnnotation")
    @SentinelResource(value = "sendMessageWithAnnotation",blockHandler = "blockHandler")
    public AjaxResult sendMessageWithAnnotation() throws InterruptedException {
        return AjaxResult.success(service.sendMessageWithAnnotation()) ;
    }

    public AjaxResult blockHandler(BlockException blockException) {
        return AjaxResult.error("block自定义异常") ;
    }


}
