package com.demo.tspring.EEBook4.config;

import com.demo.tspring.EEBook4.interceptors.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @Description: SpringMVC 总体配置
 * @Author: CaoXiaoLong create on 2017/7/20 15:41.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.demo.tspring.EEBook4")
public class WebConfig extends WebMvcConfigurerAdapter{

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");  //将返回的页面映射到该路径下
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override //静态资源的映射 对外部暴露访问路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
    }

    public TestInterceptor testInterceptor(){
        return new TestInterceptor();
    }

    @Override  //添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testInterceptor());
    }
}
