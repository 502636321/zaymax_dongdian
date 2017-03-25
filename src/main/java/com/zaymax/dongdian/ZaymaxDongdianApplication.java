package com.zaymax.dongdian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableScheduling
@EnableAutoConfiguration
@EnableJpaAuditing
@SpringBootApplication
public class ZaymaxDongdianApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaymaxDongdianApplication.class, args);
	}
}
