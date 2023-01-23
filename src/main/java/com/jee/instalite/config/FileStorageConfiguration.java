package com.jee.instalite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileStorageConfiguration {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Bean
    public String  fileStorageLocation() {
        return uploadDir;
    }
}
