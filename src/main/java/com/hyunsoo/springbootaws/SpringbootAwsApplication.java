package com.hyunsoo.springbootaws;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootAwsApplication {

    private final Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsApplication.class, args);
    }

    @PostConstruct
    void postConstruct(){
       System.out.println("=================================");
       //String profilesActive = environment.getProperty("spring.security.oauth2.client.registration.google.client-secret");
       //System.out.println(profilesActive);
       System.out.println("=================================");
    }
}
