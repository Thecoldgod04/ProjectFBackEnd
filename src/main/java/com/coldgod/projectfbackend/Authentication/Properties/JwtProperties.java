package com.coldgod.projectfbackend.Authentication.Properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {

    private String secretKey;

}
