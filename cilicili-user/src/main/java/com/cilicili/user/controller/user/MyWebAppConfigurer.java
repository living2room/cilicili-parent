package com.cilicili.user.controller.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//图片回显
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //外部访问路径映射到本地磁盘路径
        registry.addResourceHandler("/static/**").addResourceLocations("file:C:/Users/admin/Desktop/5.29(2)/cilicili-parent-master/cilicili-web/src/main/resources/static/img/");
    }
}
/*
 * C:\Users\admin\Desktop\5.29(2)\cilicili-parent-master\cilicili-web\src\main\
 * resources\static\img
 * C:\Users\Administrator\Desktop\5.29(2)\cilicili-parent-master\cilicili-web\
 * src\main\resources\static\img
 */