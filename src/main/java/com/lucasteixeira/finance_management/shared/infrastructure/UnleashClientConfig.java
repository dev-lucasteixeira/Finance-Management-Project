package com.lucasteixeira.finance_management.shared.infrastructure;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnleashClientConfig {

    @Value("${unleash.api.url:http://localhost:4242/api/}")
    private String unleashApi;

    @Value("${unleash.api.token}")
    private String unleashToken;

    @Bean
    public Unleash unleash() {
        UnleashConfig sdkConfig = UnleashConfig.builder()
                .appName("finance-management-project")
                .unleashAPI(unleashApi)
                .customHttpHeader("Authorization", unleashToken)
                .build();

        return new DefaultUnleash(sdkConfig);
    }
}

