package ua.cn.stu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.cn.stu.dao.HibernateDAOFactory;
import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Product;

public class CinemaHallServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	 
	 @Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String actionType = req.getParameter("actiontype");
		 //Обробка запиту на додання продукту
		 if ("addcinemahall".equalsIgnoreCase(actionType)) {
			 String cinemaHallName = req.getParameter("cinemahallhallname");
			 String cinemaName = req.getParameter("cinemahallcinemaname");
			 CinemaHall cinemaHall = new CinemaHall();
			 cinemaHall.setCinemaName(cinemaName);
			 cinemaHall.setHallName(cinemaHallName);
			 HibernateDAOFactory.getInstance().getCinemaHallDAO().createCinemaHall(cinemaHall);
		 //Обробка запиту на видалення продукту
		 } else if ("deletecinemahall".equalsIgnoreCase(actionType)) {
			 String cinemaHallId = req.getParameter("cinemahallid");
			 Long id = Long.parseLong(cinemaHallId);
			 HibernateDAOFactory.getInstance().getCinemaHallDAO().deleteCinemaHallById(id);
		 //Обробка запиту на оновлення продукту
		 } else if ("updatecinemahall".equalsIgnoreCase(actionType)) {
			 String cinemaHallId = req.getParameter("cinemahallid");
			 Long cinemaId = Long.parseLong(cinemaHallId);
			 String cinemahallname = req.getParameter("cinemahallname");
			 String cinemaname = req.getParameter("cinemaname");
			 CinemaHall cinemaHall = HibernateDAOFactory.getInstance().getCinemaHallDAO().getCinemaHallById(cinemaId);
			 cinemaHall.setCinemaName(cinemaname);
			 cinemaHall.setHallName(cinemahallname);
			 HibernateDAOFactory.getInstance().getCinemaHallDAO().updateCinemaHall(cinemaHall);
		 } 
		 //Після обробки запиту перейти на сторінку продукту
		 resp.sendRedirect("cinemahall.jsp");
	 }

}
