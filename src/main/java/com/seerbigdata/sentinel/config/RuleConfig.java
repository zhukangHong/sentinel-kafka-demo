package com.seerbigdata.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzk
 * @Classname RuleConfig
 * @create 2022-06-17 16:34
 * @Description
 */

@Configuration
public class RuleConfig {



    static{
        initFlowQpsRule();
    }

    private static void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule("sendMessageWithAnnotation");
        // set limit qps to 20
        rule.setCount(2);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rule.setMaxQueueingTimeMs(10);
        //0直接资源 1关联资源 2链路限流
        rule.setStrategy(0);
        //0直接拒绝 1WarmUp 2匀速+排队等待
        rule.setControlBehavior(0);
        rules.add(rule);

        FlowRule rule1 = new FlowRule("sendMessage");
        // set limit qps to 20
        rule1.setCount(2);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        //0直接资源 1关联资源 2链路限流
        rule1.setStrategy(0);
        //0直接拒绝 1WarmUp 2匀速+排队等待
        rule1.setControlBehavior(0);
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }

    private static void initDegradeQpsRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule("/circuitBreakingByRt");
        //阈值数值
        rule.setCount(500);
        //熔断类型 0慢调用，1异常率，2异常数
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //最小请求数
        rule.setMinRequestAmount(6);
        //熔断时间ms
        rule.setTimeWindow(1000);
        //统计时间
        rule.setStatIntervalMs(2000);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

}
