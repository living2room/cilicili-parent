
package com.cilicili.user.controller.user;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 注册拦截器
	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * //registry.addInterceptor(new
	 * MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/a.do"); //
	 * addPathPatterns和excludePathPatterns可以分别指定要拦截的请求和排除的请求，参数也可以是一个List<String>
	 * 
	 * //registry.addInterceptor(new
	 * MyInterceptor()).addPathPatterns("/user/index"); registry.addInterceptor(new
	 * MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/toLogin",
	 * "/admin/toLogin"); }
	 */
	
	//监听器的注册
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	    @Bean
	    public ServletListenerRegistrationBean listenerRegist() {
	        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
	        srb.setListener(new LoginListenner());
	        System.out.println("listener");
	        return srb;
	    }

}
