package com.immobel;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.immobel.service.DataGenerator;

@PropertySource({"classpath:application.properties"})
@EntityScan(basePackages = "com.immobel.model")
@EnableJpaRepositories(basePackages = "com.immobel.repository")
@Configuration
@SpringBootApplication
@EnableScheduling
public class MainApp {

	@Autowired
	private DataGenerator dataGenerator;

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		this.dataGenerator.generateInitialData();
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
