package com.company.config;

import org.rendersnake.ext.spring.RenderableViewResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(value = { "com.company" })
public class ApplicationStarter extends WebMvcConfigurerAdapter {
	
	/*
	 * Application starter
	 */
	public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
	
	/*
	 * RenderSnake view resolver, allow Spring to instantiate
	 * RenderSnake page.
	 */
	@Bean
	public RenderableViewResolver viewResolver() {
		final RenderableViewResolver viewResolver = new RenderableViewResolver();
		return viewResolver;
	}
}