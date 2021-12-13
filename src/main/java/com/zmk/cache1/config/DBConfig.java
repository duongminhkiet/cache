package com.zmk.cache1.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;
import com.zmk.cache1.helper.GlobalVariable;

//@Configuration
public class DBConfig {
//    @Bean
//    @Primary
//    public DataSource dnDataSource() {
//    	HikariDataSource mainDatasource = new HikariDataSource();
//    	mainDatasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    	mainDatasource.setJdbcUrl("jdbc:sqlserver://10.72.99.14;databaseName=testx1");//LIVE
//    	
//    	mainDatasource.setUsername(GlobalVariable.UX);
//    	mainDatasource.setPassword(GlobalVariable.PX);
//    	mainDatasource.setMaximumPoolSize(1000);
//    	mainDatasource.setConnectionTimeout(6000000);
//    	mainDatasource.setPoolName("GSSLDIEN-LOCAL-HIKARI-");
//        return mainDatasource;
//    }
    
}
