package org.iesalixar.aleal.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.iesalixar.aleal.helper.HibernateUtil;
import org.iesalixar.aleal.model.Student;

public class StudentDAO {

	final static Logger logger = Logger.getLogger(StudentDAO.class);

	@SuppressWarnings("unchecked")
	public static List<Object[]> getStudentNames() {

		List<Object[]> list = null;
		Session session = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			list = session.createQuery("SELECT s.name,s.id FROM Student As s").list();
		} catch (Exception e) {
			logger.error("'StudentDAO.getStudentNames' method has raised an exception: " + e.getMessage());
		}
		return list;

		/*
		 * other way to get the same result: return Hibernate.getPersistenceSession().
		 * createQuery("SELECT s.name,s.id FROM Student As s").list();
		 */
	}

	public static Student getStudentById(int classroomId) {

		Student classroom = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			classroom = session.get(Student.class, classroomId);
			/*
			 * other way to get the same result:
			 * session.createQuery("SELECT c FROM Classroom As c WHERE c.id =:param1",
			 * Classroom.class). setParameter("param1",classroomId). uniqueResult();
			 */
		} catch (Exception e) {
			logger.error("'StudentDAO.getClassroomById' method has raised an exception: " + e.getMessage());
		}
		return classroom;
	}

}
