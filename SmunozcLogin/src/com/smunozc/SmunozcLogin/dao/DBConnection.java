package com.smunozc.SmunozcLogin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConnection {

	private static Connection connection = null;

	final static Logger logger = Logger.getLogger(DBConnection.class);

	public static Connection getConnection() {

		// DB properties variables
		Properties prop = new Properties();

		logger.info("Client attempting connection to database [DBConnection.class]");

		try {

			// DB properties load
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties"));

			if (connection == null || connection.isClosed()) {
				String classForname = prop.getProperty("db.classForName");
				String url = prop.getProperty("db.url") + "//" + prop.getProperty("db.host") + ":"
						+ prop.getProperty("db.port") + "/" + prop.getProperty("db.name");
				String username = prop.getProperty("db.username");
				String password = prop.getProperty("db.password");

				Class.forName(classForname);
				connection = DriverManager.getConnection(url, username, password);
				// System.out.println(url + " - username: " + username + " password: " +
				// password);
				logger.info("Connection successful [DBConnection.class]");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;

	}

}