package com.amberlight.spring.cloud.k8s.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class SayingGenerator {

    public static void main(String[] args) {
        SpringApplication.run(SayingGenerator.class, args);
    }

    @Autowired
    private ObjectMapper objectMapper;

    private final TypeReference<ArrayList<String>> TYPE_REFERENCE_ARRAYLIST_OF_STRINGS = new TypeReference<>() {};
    private ArrayList<String> sayings;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void postConstruct() throws IOException {
        String sayingsJsonString =
                new String(resourceLoader.getResource("classpath:sayings.json").getInputStream().readAllBytes());
        this.sayings = objectMapper.readValue(sayingsJsonString, TYPE_REFERENCE_ARRAYLIST_OF_STRINGS);
    }

    @GetMapping("/saying")
    public ResponseEntity<String> getSaying() {
        return ResponseEntity.ok(sayings.get(getRandomNumber(0, sayings.size() - 1)));
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
