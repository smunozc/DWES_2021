package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Language;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class LanguageDAOImpl implements LanguageDAO {
	
	final static Logger logger = Logger.getLogger(LanguageDAOImpl.class);

	@Override
	public List<Language> getLanguages() {
		
		logger.info("getLanguages method has been invoked [LanguageDAOImpl.class]");
		
		List<Language> res = new ArrayList<Language>();

		//Connection con = DBConnection.getConnecttion();
		//Connection con = (new PoolDB()).getConnection();
		Connection con = null;
		try {
			con = ((BasicDataSource)
					((new InitialContext())
							.lookup("java:/comp/env/jdbc/mariaDBConnection")))
								.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
		if(con != null) {
			String sql = "select * from languages";
			PreparedStatement ps;
			
			try {
				ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					try {
						res.add(new Language(rs.getString(2)));								
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		logger.info("getLanguages method has retrieved "+(res.isEmpty()?"nothing":String.valueOf(res.size()))+" [LanguageDAOImpl.class]");
		
		return res;
	}
	
	@Override
	public boolean checkLanguage(String language) {
		
		logger.info("checkLanguage method has been invoked [LanguageDAOImpl.class]");
		
		boolean find = false;
		
		//Connection con = DBConnection.getConnecttion();
		//Connection con = (new PoolDB()).getConnection();
		Connection con = null;
		try {
			con = ((BasicDataSource)
					((new InitialContext())
							.lookup("java:/comp/env/jdbc/mariaDBConnection")))
								.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
		if(con != null) {
			String sql = "select language from languages where language = ?";
			PreparedStatement ps;
			
			try {
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1,language);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next())
					find = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("checkLanguage method has "+(find?"found coincidence":"not found coincidence")+" [LanguageDAOImpl.class]");
		
		return find;
	}
	
}
