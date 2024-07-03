package com.revature.item_management_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.revature")
public class ItemManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemManagementApplication.class, args);
	}
}