package com.example.demo3.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutePoolConfig {

        @Bean
        public Executor myTaskAsyncPool() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10); //核心线程数
            executor.setMaxPoolSize(20);  //最大线程数
            executor.setQueueCapacity(1000); //队列大小
            executor.setKeepAliveSeconds(300); //线程最大空闲时间

            executor.setThreadNamePrefix("MyTaskExecutor-"); //线程前缀名称
            executor.initialize();
            return executor;
        }
}


