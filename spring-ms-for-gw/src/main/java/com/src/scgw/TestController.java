package com.src.scgw;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms1")
public class TestController {
	
	@GetMapping("/getep")
	public Employee getEmployeeDetails() {
		Employee e = new Employee();
		e.setEmpName("Some name");
		e.setSalary(10000L);
		return e;
	}

	@PostMapping("/postep")
	public Employee saveEmployeeDetails(@RequestBody Employee e) {
		return e;
	}
	
	@GetMapping("/errorep")
	public Employee throwError() throws InterruptedException {
		Thread.sleep(200);
		int ri = new Random().nextInt();
		System.out.println(">>>>>"+ri);
//		if(ri%2!=0)
		if(true)
			throw new NullPointerException("this is some error...");
		Employee e = new Employee();
		e.setEmpName("New Employee");
		e.setSalary(10000L);
		return e;
	}
	
	@GetMapping("/getjwt")
	public String getJwt() {
		return "new jwt:" + new Random().nextInt();
	}
	
	
}
