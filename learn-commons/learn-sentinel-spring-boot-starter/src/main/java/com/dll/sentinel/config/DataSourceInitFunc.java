package com.dll.sentinel.config;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.cloud.sentinel.datasource.config.NacosDataSourceProperties;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 *
 * 2019/7/8.
 * 配置sentinel 熔断规则初始化配置类
 */
@Configuration
public class DataSourceInitFunc {
    @Autowired
    private SentinelProperties sentinelProperties;

    @Bean
    public DataSourceInitFunc init() throws Exception{
        System.out.println("[NacosSource初始化,从Nacos中获取熔断规则]");

        sentinelProperties.getDatasource().entrySet().stream().filter(map -> {
            return map.getValue().getNacos() != null;
        }).forEach(map -> {
            NacosDataSourceProperties nacos = map.getValue().getNacos();
            //需要在配置阶段dataId做规则化处理
            if (nacos.getDataId().contains("degrade-rules")){
                //降级规则
                ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<List<DegradeRule>>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>(){
                        }));
                DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
            }else if (nacos.getDataId().contains("flow-rules")){
                //限流规则
                ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                        }));
                FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
            }else if (nacos.getDataId().contains("system-rules")){
                //系统保护规则
                ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new NacosDataSource<List<SystemRule>>(nacos.getServerAddr(),
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>(){
                        }));
                SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
            }
        });
        return new DataSourceInitFunc();
    }
}
