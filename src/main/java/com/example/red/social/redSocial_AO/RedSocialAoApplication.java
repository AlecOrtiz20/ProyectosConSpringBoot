package com.example.red.social.redSocial_AO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.red.social.redSocial_AO")
public class RedSocialAoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedSocialAoApplication.class, args);
	}

}
