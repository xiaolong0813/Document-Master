package com.translation.salestranslation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync  // async consideration
public class SalestranslationApplication {
    private static final Logger logger = LogManager.getLogger(SalestranslationApplication.class);

    // * beans.factory.annotation
    // * set request origin and enable it in server
    @Value("${origin}")
    private String origin;
    @Value("${async.core-pool-size}")
    private Integer corePoolSize;
    @Value("${async.max-pool-size}")
    private Integer maxPoolSize;
    @Value("${async.queue-capacity}")
    private Integer queueCapacity;


    // * this bean is to filter the request which is from allowed domain (localhost:4201)
    // if not, crossorigin should be set in each controller
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // set allowed domain name, set to * if all are allowed, 4201 is allowed here
        config.addAllowedOrigin(origin);
        // add Header or Method limit if needed. all Headers and Methods are allowed here
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // set configuration to all paths. /** to match 0 or more paths
        source.registerCorsConfiguration("/**", config);
        // generate bean with generated cors filter by configuration of source
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // set the order to be the first to avoid conflict
        bean.setOrder(0);

        // * return to ?
        return bean;
    }

    // * set thread pool - core and max pool size / queue capacity
    // * enableasync is needed to perform Asynchronous tasks
    @Bean("Async")
    public Executor asyncExecutor() {
        logger.info("Initialize async executor!");
        // * JavaBean that allows for configuring a java.util.concurrent.ThreadPoolExecutor
        // * in bean style
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // set executor core (initial threads, always create) pool size
        executor.setCorePoolSize(corePoolSize);
        // set max pool size
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        // set name prefix, to locate pool
        executor.setThreadNamePrefix("Async-");

        executor.setWaitForTasksToCompleteOnShutdown(true);

//        executor.setAwaitTerminationSeconds(200);
//
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // * return to ?
        return executor;
    }



    public static void main(String[] args) {
        logger.info("Start translation server!");
        SpringApplication.run(SalestranslationApplication.class, args);
    }

}
