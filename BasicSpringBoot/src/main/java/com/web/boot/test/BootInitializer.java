package com.web.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.web.boot")
public class BootInitializer {
	public static void main(String[] args) {
		SpringApplication.run(BootInitializer.class,args);
	}
	
}
