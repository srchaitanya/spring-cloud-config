package com.src.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class TestController {
	@Value("${prop1}")
	String prop1;
	
	@GetMapping("/prop1")
	public String getProp1() {
		return prop1;
	}
}


@RestController
@RequestMapping("/config")
@RefreshScope
class TestController2 {
	@Value("${prop2}")
	String prop2;
	
	@GetMapping("/prop2")
	public String getProp2() {
		return this.prop2;
	}
	
}

@RequestMapping("/config")
@RestController
class TestController3 {
	@Value("${secret}")
	String secret;
	
	@GetMapping("/secret")
	public String getSecret() {
		return this.secret;
	}
}

