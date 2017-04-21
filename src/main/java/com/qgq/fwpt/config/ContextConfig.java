package com.qgq.fwpt.config;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
@Configuration
public class ContextConfig {
    private static final Logger logger = LoggerFactory.getLogger(ContextConfig.class);

    @Bean
    public PageHelper pageHelper() {
        logger.info("======PageHelper init======");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        //properties.setProperty("dialect","mysql");
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        //properties.setProperty("pageSizeZero","true");
        properties.setProperty("reasonable","false");
       // properties.setProperty("params","pageNum=start;pageSize=limit;");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
