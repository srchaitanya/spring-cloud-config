package com.src.scgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringMsForGwApp {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMsForGwApp.class, args);
	}
	
}

@Component
class ConfigClass implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AdviceClass());
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}