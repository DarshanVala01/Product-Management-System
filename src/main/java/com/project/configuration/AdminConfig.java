package com.project.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminConfig {

    private String name;

    private String email;

    private String phoneNumber;

    private String whatsappNumber;
}
