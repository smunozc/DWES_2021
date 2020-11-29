package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import listener.EventControl;

public class PoolDB {
	
	private DataSource dataSource;	
	
	final static Logger logger = Logger.getLogger(PoolDB.class);
	
	public PoolDB(){
		initDataSource();
		logger.info("PoolDB constructor invoked");
	}

	private void initDataSource() {
		
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName(	properties.getProperty("db.classForName"));
		
		basicDataSource.setUsername(		properties.getProperty("db.username"));
		basicDataSource.setPassword(		properties.getProperty("db.password"));
		
		String url = 						properties.getProperty("db.url" ) + "//" + 
											properties.getProperty("db.host") + ":" + 
											properties.getProperty("db.port") + "/" + 
											properties.getProperty("db.dbname");
		System.out.println(url);
		basicDataSource.setUrl(				url);
		basicDataSource.setMaxTotal(Integer.parseInt(properties.getProperty("db.pool.maxTotal")));
		basicDataSource.setMaxIdle(Integer.parseInt( properties.getProperty("db.pool.maxIdle" )));
		
		dataSource = basicDataSource;						
	}
	
	public Connection getConnection() {
		Connection result = null;
		try {
			result = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		logger.info("PoolDB 'getConnection' method called (it returned valid connection: '" + (result!=null) + "')");
		
		return result;
	}
	

}
