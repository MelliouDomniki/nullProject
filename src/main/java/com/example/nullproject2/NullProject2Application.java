package com.example.nullproject2;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.example.nullproject2.entity.Hospital;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.security.KeyPair;


@EnableMongoRepositories("com.example.nullproject2.repositories")
@SpringBootApplication
public class NullProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(NullProject2Application.class, args);
    }



}
