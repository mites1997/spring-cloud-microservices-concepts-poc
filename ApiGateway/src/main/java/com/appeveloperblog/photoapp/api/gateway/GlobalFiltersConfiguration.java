package com.appeveloperblog.photoapp.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

	final Logger logger = LoggerFactory.getLogger(MyPreFilter.class);

	@Order(1)
	@Bean
	public GlobalFilter secondPreFilter() {
		return (exchange, chain) -> {
			logger.info("My second global prefilter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				
				logger.info("My Second Global post filter executed");
				
				
			}));
		};
	}
	
	@Order(2)
	@Bean
	public GlobalFilter thirdPreFilter() {
		return (exchange, chain) -> {
			logger.info("My third global prefilter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				
				logger.info("My first Global post filter executed");
				
				
			}));
		};
	}

}
