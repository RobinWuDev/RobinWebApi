//package com.robinwu.api.config;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.context.annotation.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.sql.DataSource;
//
//
///**
// * Created by Robin on 16/6/1.
// */
//@Configuration
//@ComponentScan(basePackages = {"com.robinwu.api"},
//        excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class)})
//public class RootConfig {
//
//    @Profile("production")
//    @Bean
//    public DataSource mysqlDataSource() {
//        BasicDataSource source = new BasicDataSource();
//        source.setDriverClassName("com.mysql.jdbc.Driver");
//        source.setUrl("jdbc:mysql://localhost:3306/jay");
//        source.setUsername("root");
//        source.setPassword("88283110");
//        return source;
//    }
//
//    @Profile("test")
//    @Bean
//    public DataSource hsqldbDataSource() {
//        BasicDataSource source = new BasicDataSource();
//        source.setDriverClassName("org.hsqldb.jdbcDriver");
//        source.setUrl("jdbc:hsqldb:hsql://localhost/");
//        source.setUsername("sa");
//        source.setPassword("");
//        return source;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource ds) {
//        return new JdbcTemplate(ds);
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(DataSource ds) {
//        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(ds);
//        return transactionManager;
//    }
//
//}
