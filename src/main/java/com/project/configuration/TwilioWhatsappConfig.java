package com.project.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "twilio.whatsapp")
@Getter
@Setter
@AllArgsConstructor
public class TwilioWhatsappConfig {

    private String trialNumber;
}
