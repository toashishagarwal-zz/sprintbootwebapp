package com.example.appdirect.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages={"com.example.appdirect.domain"})
@EnableJpaRepositories(basePackages={"com.example.appdirect.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {

}
