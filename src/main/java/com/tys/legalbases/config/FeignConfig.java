package com.tys.legalbases.config;

import com.tys.legalbases.infrastructure.external.FeignRequestLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // Log everything including headers and body
    }

    @Bean
    public FeignRequestLogger feignRequestLogger() {
        return new FeignRequestLogger();
    }
}
