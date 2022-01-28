package com.edward.nacosproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NacosProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosProducerApplication.class, args);
	}

}
