package com.jee.instalite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



    @Configuration
    @EnableWebMvc
    public class WebConfig implements WebMvcConfigurer {
/*

        @Value("${file.upload.dir}")
        private String uploadDir;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/files/**")
                    .addResourceLocations("file:" + uploadDir);
        }
*/
@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp("/resources/templates", ".html");
}

    }


