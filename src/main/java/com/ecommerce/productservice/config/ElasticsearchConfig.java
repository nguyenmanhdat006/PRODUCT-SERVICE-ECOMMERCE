package com.ecommerce.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ecommerce.productservice.repository")
public class ElasticsearchConfig {
    // Spring Boot auto-configuration handles the rest
}

