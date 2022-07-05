package com.src.scgw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class CloudGwApp {
	
	@Autowired
	JwtFilter jwtFilter;
	
	public static void main(String[] args) {
		SpringApplication.run(CloudGwApp.class, args);
	}
	
	@Bean
	RouteLocator createRouteLocator(RouteLocatorBuilder rlb) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>inside create route locator");
		return rlb.routes()
		   .route("securedgateway", predicateSpec -> 
		   					 predicateSpec.path("/securedgw/**")
				   			.filters(fs -> 
				   					fs
				   					.filter(jwtFilter)
				   					.rewritePath("/securedgw/(?<restofthepath>.*)","/ms1/${restofthepath}")
				   					).uri("http://localhost:8082")
				   ).build();
	}
}

@Component
class JwtFilter implements GatewayFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Inside JwtFilter");
		RestTemplate rt = new RestTemplate();
		String jwt = rt.getForObject("http://localhost:8082/ms1/getjwt", String.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> "+ jwt);
		exchange.mutate().request(rbc -> rbc.header("jwtkey", jwt));
		return chain.filter(exchange);
	}
	
}


@RestController
class TestController {
	
	@GetMapping("/mybackup") 
	public String returnSomeValue(ServerWebExchange exchange) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> "+exchange.getRequest().getPath().value());
		return "{empName:'test',empSalary:9999}";
	}
	
}