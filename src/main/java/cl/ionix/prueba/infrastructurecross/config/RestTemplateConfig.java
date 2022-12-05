package cl.ionix.prueba.infrastructurecross.config;

import cl.ionix.prueba.infrastructurecross.application.Setting;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(Setting setting) {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(setting.getConnectTimeOut()))
                .setReadTimeout(Duration.ofSeconds(setting.getReadTimeOut()))
                .build();
    }

}
