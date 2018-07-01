package com.foundernest;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.foundernest.domain.Investor;
import com.foundernest.domain.Startup;
import com.foundernest.repositories.InvestorRepository;
import com.foundernest.repositories.StartupRepository;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
public class founderNestApplication {
	public static void main(String[] args) {
		SpringApplication.run(founderNestApplication.class, args);
	}

	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.any())
	        .paths(PathSelectors.any())
	        .paths(Predicates.not(PathSelectors.regex("/error.*")))
	        .paths(Predicates.not(PathSelectors.regex("/")))
	        .build();
	}
	
}
