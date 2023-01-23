package com.jee.instalite;

import com.jee.instalite.service.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@Configuration
@EnableConfigurationProperties(FileStorageProperties.class)
public class InstaliteApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaliteApplication.class, args);
    }

}
