package com.revature.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class TrelloV2ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2ZuulApplication.class, args);
	}
	
	/**
	 * Only needed when running on EC2's.
	 * @return a CorsFilter
	 */
	@Bean
	public CorsFilter corsFilter(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        config.addAllowedOrigin("*");

        config.addAllowedHeader("*");

        config.addAllowedMethod("OPTIONS");

        config.addAllowedMethod("HEAD");

        config.addAllowedMethod("GET");

        config.addAllowedMethod("PUT");

        config.addAllowedMethod("POST");

        config.addAllowedMethod("DELETE");

        config.addAllowedMethod("PATCH");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

	}
}
