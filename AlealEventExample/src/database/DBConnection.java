package database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConnection {

	private static Connection connection = null;
	
	final static Logger logger = Logger.getLogger(DBConnection.class);
	
	
	
	public static Connection getConnecttion() {
		Properties p = new Properties();	
		try {
			if(connection == null  || connection.isClosed()) {
				
				p.load(Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("configuration.properties"));
		        String classForname = p.getProperty("db.classForName");
		        String url = p.getProperty("db.url") + "//" + p.getProperty("db.host") + ":" + p.getProperty("db.port") + "/" + p.getProperty("db.name");
		        String username = p.getProperty("db.username");
		        String password = p.getProperty("db.password");
				
				Class.forName(classForname);
				connection = DriverManager.getConnection(
						url,		
						username,	
						password);
				System.out.println(url + " - username: " + username + " password: " + password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;					
	}

}