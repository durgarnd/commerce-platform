package com.dds.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {


	@Override
	public String toString() {
		return super.toString();
	}

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
