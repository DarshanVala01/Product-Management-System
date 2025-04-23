package com.project.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TwilioSmsConfig {

    private String accountSid;

    private String trialNumber;

    private String authId;
}
