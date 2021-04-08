package ua.cn.stu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.cn.stu.dao.HibernateDAOFactory;
import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Visitor;

public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionType = req.getParameter("actiontype");
//Обробка запиту на додання продукту
		
		if ("addvisitor".equalsIgnoreCase(actionType)) {
			
			String visitorname = req.getParameter("visitorfullname");
			
			String visitorcinema = req.getParameter("visitorhallid");
			String age = req.getParameter("visitorage");
			CinemaHall cinemaHall = HibernateDAOFactory.getInstance().getCinemaHallDAO().getCinemaHallById(Long.parseLong(visitorcinema));
			Visitor visitor = new Visitor();
			
			visitor.setFullName(visitorname);
			
			visitor.setAge(Integer.parseInt(age));
			visitor.setCinemaHall(cinemaHall);
			
			HibernateDAOFactory.getInstance().getVisitorDAO().createVisitor(visitor);
			
			
//Обробка запиту на видалення продукту
		} 
		
		else if ("deletevisitor".equalsIgnoreCase(actionType)) {
			
			String visitornameid = req.getParameter("visitorid");	
			Long visitor = Long.parseLong(visitornameid);
			HibernateDAOFactory.getInstance().getVisitorDAO().deleteVisitorId(visitor);
			
			
//Обробка запиту на оновлення продукту
		} 
		else if ("updatevisitor".equalsIgnoreCase(actionType)) {	
			String visitoridString = req.getParameter("visitorid");
			Long visitorId = Long.parseLong(visitoridString);
			String visitorname = req.getParameter("visitorname");
			Visitor visitor = HibernateDAOFactory.getInstance().getVisitorDAO().getVisitorById(visitorId);
			visitor.setFullName(visitorname);
			HibernateDAOFactory.getInstance().getVisitorDAO().updateVisitor(visitor);
			
		}
////Після обробки запиту перейти на сторінку продукту
		resp.sendRedirect("visitor.jsp");
	}

	private CinemaHall parseint(String visitorcinema) {
		// TODO Auto-generated method stub
		return null;
	}
}
