package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
/*
   @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            String requestJson = "{\n" +
                    "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL;\",\n" +
                    "  \"streamsProperties\": {\n" +
                    "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                    "  }\n" +
                    "}";
            HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
           String response = restTemplate.postForObject("http://localhost:8088/query",entity, String.class);

            log.info(response);
        };
    }*/


