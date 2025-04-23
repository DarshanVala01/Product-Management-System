package com.project;

import com.project.configuration.TwilioMailConfig;
import com.project.configuration.TwilioSmsConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan("com.project.configuration")
public class ProjectApplication {

	@Autowired
	private TwilioSmsConfig configTwilio;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@PostConstruct
	public void init(){
		System.out.println(configTwilio.getAccountSid());
		System.out.println(configTwilio.getAuthId());
		Twilio.init(configTwilio.getAccountSid(),configTwilio.getAuthId());
	}


}
