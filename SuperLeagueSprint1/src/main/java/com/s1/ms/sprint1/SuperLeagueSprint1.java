package com.s1.ms.sprint1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.s1.ms.sprint1.controller.InventoryController;

@SpringBootApplication
@EnableAutoConfiguration
public class SuperLeagueSprint1 {
	
	public static void main(String[] args) {
//		SpringApplication.run(SuperLeagueSprint1.class, args);		
		ConfigurableApplicationContext context = SpringApplication.run(SuperLeagueSprint1.class, args);
		context.getBean(InventoryController.class).save();
	}

}
