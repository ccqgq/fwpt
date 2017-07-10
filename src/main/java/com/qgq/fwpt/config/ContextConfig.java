package com.qgq.fwpt.config;

import com.github.pagehelper.PageHelper;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
@Configuration
public class ContextConfig {
    private static final Logger logger = LoggerFactory.getLogger(ContextConfig.class);
    private static List<BasicHeader> defaultHeaders;
    static {
        defaultHeaders = new ArrayList<>();
        defaultHeaders.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        defaultHeaders.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        defaultHeaders.add(new BasicHeader("Accept-Language", "zh-CN"));
    }

    @Bean
    public PageHelper pageHelper() {
        logger.info("======PageHelper init======");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        properties.setProperty("dialect","mysql");
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("pageSizeZero","true");
        properties.setProperty("reasonable","false");
        properties.setProperty("params","pageNum=start;pageSize=limit;");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        logger.info("==== httpComponentsClientHttpRequestFactory init ====");

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);           // 整个连接池的并发
        connectionManager.setDefaultMaxPerRoute(10);   // 每个主机的并发

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(connectionManager);
        builder.setDefaultHeaders(defaultHeaders);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(builder.build());
        factory.setConnectTimeout(5_000);
        factory.setReadTimeout(60_000);
        return factory;
    }
    @Bean
    public RestTemplate restTemplate() {
        logger.info("==== restTemplate init ====");

        RestTemplate template = new RestTemplate(httpComponentsClientHttpRequestFactory());

        List<HttpMessageConverter<?>> httpMessageConverterList = template.getMessageConverters();
        for (HttpMessageConverter httpMessageConverter:httpMessageConverterList) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
            }
        }

        template.setErrorHandler(new DefaultResponseErrorHandler());
        return template;
    }
}
