package org.iesalixar.aleal.main;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.iesalixar.aleal.helper.HibernateUtil;
import org.iesalixar.aleal.model.Book;
import org.iesalixar.aleal.model.Student;

public class Main {

	public static void main(String[] args) {
				
		Student student = new Student();
		student.setName("Pepito");
		student.setSurnames("Apellido 1 Apellido 2");
		student.setBirthyear(1920);
		student.setCourse("advance - 2nd grade");
		student.setNid(12345678);
		
		Book book = new Book();
		book.setAuthor("Christian Bauer");
		book.setTitle("Java Persistence with Hibernate");
		book.setIsbn("17290459");//book.setIsbn("9781617290459");
		book.setYear(2016);
		book.setStudent(student);
		
		Book book2 = new Book();
		book2.setAuthor("Joseth Ottinger");
		book2.setTitle("Hibernate Recipes, 2nd Edition - A Problem-Solution Approach");
		book2.setIsbn("84201282");//book2.setIsbn("9781484201282");
		book2.setYear(2015);
		book2.setStudent(student);
		
		Book book3 = new Book();
		book3.setAuthor("Mitesh Soni");
		book3.setTitle("Jenkins 2.x Continuous integration - Cookbook - 3rd edition");
		book3.setIsbn("88297943");//book3.setIsbn("9781788297943"); 
		book3.setYear(2017);
		book3.setStudent(student);
		
		
		//student.addBook(book);
		
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		System.out.println(book.toString());
		System.out.println(book2.toString());
		System.out.println(book3.toString());
		
		session.beginTransaction();
			session.save(student);
			session.save(book);
			session.save(book2);
			session.save(book3);
		session.getTransaction().commit();
		
		session.close();
		
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		//Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		List<Student> students = loadAllData(Student.class, session);
		students.listIterator().forEachRemaining(s -> {s.getBooks();});
			
//		session.getTransaction().commit();
		
		
		session.close();
		
		for(Student stu : students) {
			System.out.println(stu.toString());
		}
		
	}
	
	
	private static <T> List<T> loadAllData(Class<T> type, Session session) {
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<T> criteria = builder.createQuery(type);
	    criteria.from(type);
	    List<T> data = session.createQuery(criteria).getResultList();
	    return data;
	  }

}
