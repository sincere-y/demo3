package com.example.demo3.module.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue categoryDeleteQueue() {
        return new Queue("category.delete.queue");
    }



}
