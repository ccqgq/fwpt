package com.qgq.fwpt.config;


import com.qgq.fwpt.openaccount.aspect.LoginInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.DispatcherServlet;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created on 2017/03/16
 *
 * @author annpeter.it@gmail.com
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.qgq.fwpt.openaccount.controller",
        "com.qgq.fwpt.openaccount.aspect"
})
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);


    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        logger.info("==== methodValidationPostProcessor init ====");

        return new MethodValidationPostProcessor();
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        logger.info("==== localValidatorFactoryBean init ====");

        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setProviderClass(HibernateValidator.class);
        return factoryBean;
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        logger.info("==== dispatcherRegistration init ====");

        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return new ServletRegistrationBean(dispatcherServlet);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("==== addInterceptors init ====");

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/message/messageList")
                .excludePathPatterns("/api/admin/userLogout");

        super.addInterceptors(registry);
    }


}
