package ua.cn.stu.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Visitor;

public class VisitorDAO {

	private Session session;
	 
	 public VisitorDAO(Session session) {
		 this.session = session;
	 }

	 public Visitor createVisitor(Visitor visitor) {
		 Transaction transaction = session.beginTransaction();
		 session.saveOrUpdate(visitor);
		 transaction.commit();
		 return visitor;
	 }

	 public Visitor updateVisitor(Visitor visitor) {
		 Transaction transaction = session.beginTransaction();
		 session.merge(visitor);
		 transaction.commit();
		 return visitor;
	 }
	 
	 public void deleteVisitor(Visitor visitor) {
		 Transaction transaction = session.beginTransaction();
		 session.delete(visitor);
		 transaction.commit();
	 }

	 public void deleteVisitorId(Long visitorId) {
		 Visitor visitor = (Visitor) session.get(Visitor.class, visitorId);
		 deleteVisitor(visitor);
	 }
	 
	 public Visitor getVisitorById(Long visitorId) {
		 Visitor visitor = (Visitor) session.get(Visitor.class, visitorId);
		 return visitor;
	 }

	 public List<Visitor> getAllVisitors() {
		 SQLQuery query = session.createSQLQuery("select * from visitor").addEntity(Visitor.class);
		 List<Visitor> visitorList = query.list();
		 return visitorList;
	 }
	 
	 public List<Visitor> getAllVisitorsByCinemaHall(Long id) {
		 SQLQuery query = session.createSQLQuery("select * from visitor where cinemahallid = " + id).addEntity(Visitor.class);
		 List<Visitor> visitorList = query.list();
		 return visitorList;
	 }
	 
	
}
