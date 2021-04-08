package ua.cn.stu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import ua.cn.stu.domain.CinemaHall;

import ua.cn.stu.domain.Visitor;
public class HibernateDAOFactory {
	
	 private static HibernateDAOFactory instance;
	 
	
	 private CinemaHallDAO cinemaHallDAO;
	 private VisitorDAO visitorDAO;
	 
	 private Session session;
	 
	 //Ініціалізація синглетону
	 public static HibernateDAOFactory getInstance() {
		if (null == instance) {
		 	instance = new HibernateDAOFactory();
	 	}
	 	return instance;
	 }
	 
	 //Створення обєкта Session для взаємодії з Hibernate
	 public Session getSession() {
		 if (null == session) {
			 Configuration configuration = new Configuration();
			 configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
			 configuration.setProperty(Environment.URL,"jdbc:postgresql://localhost:5432/cinema");
			 configuration.setProperty(Environment.USER, "postgres");
			 configuration.setProperty(Environment.PASS, "admin");
			 configuration.setProperty(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
			 configuration.setProperty(Environment.HBM2DDL_AUTO, "create");
			 configuration.setProperty(Environment.SHOW_SQL, "true");
			 
			 configuration.addAnnotatedClass(Visitor.class);
			 configuration.addAnnotatedClass(CinemaHall.class);
			 StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			 serviceRegistryBuilder.applySettings(configuration.getProperties());
			 ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			 SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			 session = sessionFactory.openSession();
		 }
		 return session;
	 }
	 
	 
	 
	 public CinemaHallDAO getCinemaHallDAO() {
		 if (null == cinemaHallDAO) {
			 cinemaHallDAO = new CinemaHallDAO(getSession());
		 }
		 return cinemaHallDAO;
	 }
	 
	 public VisitorDAO getVisitorDAO() {
		 if (null == visitorDAO) {
			 visitorDAO = new VisitorDAO(getSession());
		 }
		 return visitorDAO;
	 }
	 
	 public Session getSessionInstance() {
		 return session;
	 }
	 
	 public void closeSession() {
		 getSession().close();
	 }
	 
}