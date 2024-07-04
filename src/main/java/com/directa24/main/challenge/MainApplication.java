package com.directa24.main.challenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(title = "Directors API",
        version = "1.0")
)
public class MainApplication {

   public static void main(String[] args) {
      SpringApplication.run(MainApplication.class, args);
   }

}