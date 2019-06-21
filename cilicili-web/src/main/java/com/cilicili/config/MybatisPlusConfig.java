/**
 * 
 */
package com.cilicili.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Configuration
@MapperScan("com.cilicili")
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
    	 PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
         //格式化sql语句
         Properties properties = new Properties();
         properties.setProperty("format", "true");
         performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }

    /*
     * 分页插件，自动识别数据库类型 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
