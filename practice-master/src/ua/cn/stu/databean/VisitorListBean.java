package ua.cn.stu.databean;


import java.util.ArrayList;
import java.util.List;
import ua.cn.stu.dao.HibernateDAOFactory;
import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Product;
import ua.cn.stu.domain.Visitor;
public class VisitorListBean {
	
	 private List<Visitor> visitorList;
	 
	 public VisitorListBean() {
		 visitorList = HibernateDAOFactory.getInstance().getVisitorDAO().getAllVisitors();
	 }

	public List<Visitor> getVisitorList() {
		return visitorList;
	}

	public void setVisitorList(List<Visitor> visitorList) {
		this.visitorList = visitorList;
	}
	 
	 
}