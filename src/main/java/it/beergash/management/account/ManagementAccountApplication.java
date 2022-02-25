package it.beergash.management.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring boot Application main class
 *
 * @author A.Aresta
 */
@SpringBootApplication
public class ManagementAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementAccountApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
