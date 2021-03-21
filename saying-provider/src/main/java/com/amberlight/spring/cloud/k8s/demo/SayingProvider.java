package com.amberlight.spring.cloud.k8s.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SayingProvider {

    public static void main(String[] args) {
        SpringApplication.run(SayingProvider.class, args);
    }

    @GetMapping("/saying")
    public ResponseEntity<String> getSaying() {
        RestTemplate restTemplate = new RestTemplate();
        String sayingServiceUrl = "http://saying-generator:9091/saying";
        ResponseEntity<String> response = restTemplate.getForEntity(sayingServiceUrl, String.class);
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new IllegalStateException("Oops, something went wrong.");
        }
        return ResponseEntity.ok("A wise man once said: \"" + response.getBody() + "\".");
    }

}
