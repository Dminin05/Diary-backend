package com.minin.diarybackend;

import com.minin.diarybackend.properties.PatternProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PatternProperties.class)
public class DiaryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiaryBackendApplication.class, args);
	}

}
