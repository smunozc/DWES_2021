package config;

public class DBConfig {
	
	private static String host = "172.18.0.2";
	private static String port = "3306";
	private static String URL = "jdbc:mariadb:";
	private static String db = "nation";
	private static String dbUser = "root";
	private static String dbPassword = "root";
	private static String classForName = "org.mariadb.jdbc.Driver";
	
	public static String getHost() {
		return host;
	}
	
	public static String getPort() {
		return port;
	}
	
	public static String getURL() {
		return URL;
	}

	public static String getClassForName() {
		return classForName;
	}

	public static String getDb() {
		return db;
	}

	public static String getDbUser() {
		return dbUser;
	}

	public static String getDbPassword() {
		return dbPassword;
	}
	
	
}
