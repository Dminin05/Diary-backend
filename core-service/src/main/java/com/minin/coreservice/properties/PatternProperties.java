package com.minin.coreservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
@ConfigurationProperties(prefix = "spring.patterns")
@Data
public class PatternProperties {

    private String emailPattern;

    public Pattern getEmailPattern() {
        return Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
    }
}
