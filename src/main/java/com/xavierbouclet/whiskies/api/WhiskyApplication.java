package com.xavierbouclet.whiskies.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xavierbouclet.whiskies.api.configuration.WhiskyClientProperties;
import com.xavierbouclet.whiskies.api.model.Whisky;
import com.xavierbouclet.whiskies.api.repository.WhiskyRepository;
import com.xavierbouclet.whiskies.api.service.WhiskyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.zalando.problem.ProblemModule;

import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties(WhiskyClientProperties.class)
@EnableFeignClients
public class WhiskyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiskyApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModules(
                new ProblemModule());
    }

    @Bean
    CommandLineRunner commandLineRunner(WhiskyService service, WhiskyRepository repository) {
        return args -> {
            var whiskies = service.loadAll();

            repository.saveAll(whiskies.stream().map(whisky -> new Whisky(UUID.nameUUIDFromBytes(whisky.bottle().getBytes()),
                    whisky.bottle(),
                    whisky.price(),
                    whisky.rating(),
                    whisky.region())).collect(Collectors.toList()));
        };
    }

}
