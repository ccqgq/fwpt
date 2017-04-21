package com.qgq.fwpt.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.qgq.fwpt.openaccount.dao"
    }
)
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private PageHelper pageHelper;
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        logger.info("==== sqlSessionFactory init ====");
        VFS.addImplClass(SpringBootVFS.class);
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Interceptor[] plugins =  new Interceptor[]{pageHelper};
        bean.setPlugins(plugins);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
        SqlSessionFactory factory;
        try {
            factory = bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("SqlSessionFactory 创建失败", e);
        }
        return factory;
    }

    @Bean
    public DataSource dataSource() throws Exception {
        logger.info("====== dataSource init========");
        Map<String, String> map = new HashMap<>();
        map.put("driverClassName", "com.mysql.jdbc.Driver");
        map.put("url","jdbc:mysql://mysql.rdsm3ng1qo9h3js.rds.gz.baidubce.com:3306/fwpt?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull&useSSL=false");
        map.put("username","rootcc");
        map.put("password","root123456");
        return DruidDataSourceFactory.createDataSource(map);
    }

}
