package ua.cn.stu.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.transaction.spi.LocalStatus;

import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Product;
import ua.cn.stu.domain.Visitor;

public class CinemaHallDAO {

	private Session session;
	 
	 public CinemaHallDAO(Session session) {
		 this.session = session;
	 }

	 public CinemaHall createCinemaHall(CinemaHall cinemaHall) {
		 Transaction transaction = session.beginTransaction();
		 session.saveOrUpdate(cinemaHall);
		 transaction.commit();
		 return cinemaHall;
	 }

	 public CinemaHall updateCinemaHall(CinemaHall cinemaHall) {
		 Transaction transaction = session.beginTransaction();
		 session.merge(cinemaHall);
		 transaction.commit();
		 return cinemaHall;
	 }
	 
	 public void deleteCinemaHall(CinemaHall cinemaHal) {
		 Transaction transaction = session.beginTransaction();
		 cinemaHal.getVisitors().remove(0);
		 session.delete(cinemaHal);
		 
		 transaction.commit();
	 }

	 public void deleteCinemaHallById(Long cinemaHallId) {
		 CinemaHall cinemaHall = (CinemaHall) session.get(CinemaHall.class, cinemaHallId);
		 deleteCinemaHall(cinemaHall);
	 }

	 public List<CinemaHall> getAllCinemaHalls() {
		 SQLQuery query = session.createSQLQuery("select * from cinemaHall").addEntity(CinemaHall.class);
		 List<CinemaHall> sinemaHallList = query.list();
		 return sinemaHallList;
	 }
	 
	 public List<CinemaHall> getCinemaHallByName(String name) {
		 Criteria criteria = session.createCriteria(CinemaHall.class).add(Restrictions.eq("hallName", name));
		 return criteria.list();
	 }
	 
	 public CinemaHall getCinemaHallById(Long id) {
		 CinemaHall product = (CinemaHall) session.get(CinemaHall.class, id);
		 return product;
	}
	
	 public List<Visitor> getTopTen(Long id){
		 Criteria criteria = session.createCriteria(Visitor.class).add(Restrictions.eq("cinemaHall.id", id)).setMaxResults(10);
		 return criteria.list();
	 }
	 

}
