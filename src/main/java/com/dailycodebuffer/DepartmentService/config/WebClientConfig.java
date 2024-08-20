package com.dailycodebuffer.DepartmentService.config;

import com.dailycodebuffer.DepartmentService.client.EmployeeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;
    private static Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

    @Bean
    public WebClient employeeWebClient(){
        logger.info("Inside employeeWebClient: ");
        WebClient webClient =
        WebClient.builder()
                .baseUrl("http://employee-service")
                .filter(filterFunction)
                .build();
        logger.info("End of employeeWebClient: ");
        return webClient;
    }

    @Bean
    public EmployeeClient employeeClient(){
        logger.info("Inside WebClientConnfig.employeeClient(): ");
        HttpServiceProxyFactory httpServiceProxyFactory =
                //HttpServiceProxyFactory.builder(WebClientAdapter.forClient(employeeWebClient()))
                HttpServiceProxyFactory.builderFor(WebClientAdapter.create(employeeWebClient()))
                        .build();
        logger.info("End of WebClientConnfig.employeeClient(): ");
        return httpServiceProxyFactory.createClient(EmployeeClient.class);
    }
}