
package com.cilicili.user.controller.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 注册拦截器

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/a.do");
		// addPathPatterns和excludePathPatterns可以分别指定要拦截的请求和排除的请求，参数也可以是一个List<String>

		//registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/index");
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/toLogin","/admin/toLogin");
	}

}
