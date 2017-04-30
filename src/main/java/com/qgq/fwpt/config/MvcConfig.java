package com.qgq.fwpt.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgq.fwpt.openaccount.aspect.LoginInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.DispatcherServlet;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


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
                .excludePathPatterns("/api/stu/grade");

        super.addInterceptors(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        logger.info("==== mappingJackson2HttpMessageConverter init ====");

        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        mediaTypeList.add(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
        mediaTypeList.add(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")));
        jacksonConverter.setSupportedMediaTypes(mediaTypeList);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        jacksonConverter.setObjectMapper(objectMapper);

        logger.info("==== stringHttpMessageConverter init ====");
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();

        converters.add(stringConverter);
        converters.add(jacksonConverter);
    }


}
