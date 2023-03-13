package com.dlucchesi.myglic.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebMvc
@Slf4j
public class WebConf implements WebMvcConfigurer {

    final String CORS_ENABLED = "ENABLED";
    @Value("${myglic.cors:DISABLED}")
    private String useCorsProperty;
    @Value("${build.version:unknown}")
    private String version;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        log.info("### Myglic Version: {}", version);

        if (CORS_ENABLED.equals(useCorsProperty)) {
            log.info("ATTENTION: Dev CORS allowed!!!");
            registry.addMapping("/**")
//                    .allowedOrigins("http://*")
//                    .allowedMethods("GET", "POST")
//                    .allowedHeaders("Content-Type")
//                    .exposedHeaders("Content-Type")
//                    .allowCredentials(true).maxAge(3600)
            ;
        } else {
            log.info("ATTENTION: Dev CORS not allowed!!!");
        }

    }
}