package com.appeveloperblog.photoapp.api.gateway;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
@Component
public class MyPreFilter implements GlobalFilter,Ordered{

	final Logger logger=LoggerFactory.getLogger(MyPreFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("my prefilter is executed");
		String requestpath=exchange.getRequest().getPath().toString();
		logger.info("Request path="+requestpath);
		HttpHeaders headers=exchange.getRequest().getHeaders();
		logger.info("Request path="+headers);
		Set<String>headernames=headers.keySet();
		
		headernames.forEach((headername)->{
			String headerValue=headers.getFirst(headername);
			logger.info(headername+" "+headerValue);
			
		});
		
		return chain.filter(exchange);
	}
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
