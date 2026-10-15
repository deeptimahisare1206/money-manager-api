package com.moneyManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.moneyManager")
public class MoneyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyManagerApplication.class, args);
        System.out.print("Money Management Started!");

    }
}
