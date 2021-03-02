package org.iesalixar.aleal.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.aleal.helper.HibernateUtil;
import org.iesalixar.aleal.model.Book;

public class BookDAO {
	
	final static Logger logger = Logger.getLogger(BookDAO.class);
	
	public static List<Book> getAllBooks(){
		
		List<Book> selectList = null;
		Session session = null;
		
		try {
			//HibernateUtil.buildSessionFactory();
			//HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();											

			selectList = session.createQuery("FROM Book", Book.class).list();
			
		}catch(Exception e) {
			logger.error("'BookDAO.getAllBooks' method has raised an exception: " + e.getMessage());
		}//finally {
		 //	HibernateUtil.closeSessionAndUnbindFromThread();
		//}
		
		return selectList;
		
	}
	
	public static Book getBookByIsbn(int isbn){
		HibernateUtil.buildSessionFactory();
		Book book = null;
		Session session = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			book = (Book) session.createQuery("SELECT b FROM Book As b WHERE b.isbn =:isbn", Book.class).
					setParameter("isbn",String.valueOf(isbn)).
					uniqueResult();
		}catch(Exception e) {
			logger.error("'BookDAO.getBookByIsbn' method has raised an exception: " + e.getMessage());
		}
		
		return book;
	}
	
	public static Book getBookById(int id){
		HibernateUtil.buildSessionFactory();
		Book book = null;
		Session session = null;
		try {
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			book = (Book) session.createQuery("SELECT b FROM Book As b WHERE b.id =:id", Book.class).
					setParameter("id",id).
					uniqueResult();
		}catch(Exception e) {
			logger.error("'BookDAO.getBookByIsbn' method has raised an exception: " + e.getMessage());
		}
		
		return book;
	}
	
}