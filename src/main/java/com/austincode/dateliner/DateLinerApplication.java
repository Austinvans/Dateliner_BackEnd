package com.austincode.dateliner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableSwagger2
@EnableScheduling
public class DateLinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DateLinerApplication.class, args);
	}
        
        @Bean
        public Docket api() {
           return new Docket(DocumentationType.SWAGGER_2).select()
              .apis(RequestHandlerSelectors.basePackage("com.austincode.dateliner")).build();
        }
}
