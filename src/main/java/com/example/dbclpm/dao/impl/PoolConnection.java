package com.example.dbclpm.dao.impl;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zaxxer.hikari.HikariDataSource;

@WebListener
public class PoolConnection implements ServletContextListener {
	
	/**
	 * 
	 */
	private static HikariDataSource dataSource = new HikariDataSource();
	private static String pathToPropertiesFile = "/WEB-INF/properties/application.properties";
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			Properties properties = new Properties();
			properties.load(event.getServletContext().getResourceAsStream(pathToPropertiesFile));
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");	
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String salt = properties.getProperty("salt");
			event.getServletContext().setAttribute("salt", salt);
			
			dataSource.setDriverClassName(driver);
			dataSource.setJdbcUrl(url);
			dataSource.addDataSourceProperty("characterEncoding", "UTF-8");
			dataSource.setUsername(username);
			dataSource.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Pool Connection initalized!");
	}
	
	public static HikariDataSource getPoolConnection() {
		return dataSource;
	}
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
        dataSource.close();
    }
}
