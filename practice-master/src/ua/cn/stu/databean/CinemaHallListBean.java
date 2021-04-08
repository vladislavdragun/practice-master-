package ua.cn.stu.databean;


import java.util.ArrayList;
import java.util.List;
import ua.cn.stu.dao.HibernateDAOFactory;
import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Product;
public class CinemaHallListBean {
	 public List<CinemaHall> cinemaHallList;
	 
	 public CinemaHallListBean() {
		 cinemaHallList = HibernateDAOFactory.getInstance().getCinemaHallDAO().getAllCinemaHalls();
	 }
	 
	 public List<CinemaHall> getCinemaHallList() {
		 return cinemaHallList;
	 }
	 public void setCinemaHallList(List<CinemaHall> cinemaHalls) {
		 this.cinemaHallList = cinemaHalls;
	 }
}