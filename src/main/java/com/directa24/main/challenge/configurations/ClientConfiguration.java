package com.directa24.main.challenge.configurations;

import com.directa24.main.challenge.configurations.utils.MoviesDtoDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Decoder customDecoder() {
        return new MoviesDtoDecoder(new ObjectMapper());
    }
}
