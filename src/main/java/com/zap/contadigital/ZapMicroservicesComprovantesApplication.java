package com.zap.contadigital;

import com.zap.contadigital.ratelimit.RateLimitInterceptor;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class ZapMicroservicesComprovantesApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ZapMicroservicesComprovantesApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1000 requests por minuto, mas não mais que 50 requests por segundo.
        Bucket bucket = Bucket4j.builder()
                .addLimit(Bandwidth.simple(1000, Duration.ofMinutes(1)))
                .addLimit(Bandwidth.simple(50, Duration.ofSeconds(1)))
                .build();
        registry.addInterceptor(new RateLimitInterceptor(bucket, 1)).addPathPatterns("/zap-contas/*");
    }
}
