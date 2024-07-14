package com.atlas.jobsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsmsApplication.class, args);
	}

}
