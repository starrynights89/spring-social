package com.alexander.springsocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class SpringSocialHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocialHystrixDashboardApplication.class, args);
	}
}
