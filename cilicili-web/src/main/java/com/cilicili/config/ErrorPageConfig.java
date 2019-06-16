/**
 * 
 */
package com.cilicili.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**统一异常处理
 * @author 李明睿
 * 2019年6月16日
 */
@Configuration
public class ErrorPageConfig {
	 
	    @Bean
	    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {

	        return (factory -> {
	            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	            factory.addErrorPages(errorPage404);
	        });
	    }
	 
	}
