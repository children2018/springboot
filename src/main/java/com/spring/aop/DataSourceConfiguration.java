package com.spring.aop;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {
	
	private Logger logger = Logger.getLogger(DataSourceConfiguration.class);
	
	@Value("${jdbc.type}")
	private Class<? extends DataSource> dataSourceType;
	
	@Bean(name = "writeDataSource")
	@Primary
	@ConfigurationProperties(prefix = "master.jdbc")
	public DataSource writeDataSource() {
		logger.info("-------------------- writeDataSource init ---------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	@Bean(name = "readDataSource1")
	@ConfigurationProperties(prefix = "slave.jdbc")
	public DataSource readDataSourceOne() {
		logger.info("-------------------- readDataSourceOne init ---------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	@Bean(name = "readDataSource2")
	@ConfigurationProperties(prefix = "slave.jdbc")
	public DataSource readDataSourceTwo() {
		logger.info("-------------------- readDataSourceTwo init ---------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
}
