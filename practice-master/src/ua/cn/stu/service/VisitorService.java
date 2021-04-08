package ua.cn.stu.service;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ua.cn.stu.dao.HibernateDAOFactory;
import ua.cn.stu.domain.CinemaHall;
import ua.cn.stu.domain.Visitor;

@Path("visitor")
public class VisitorService {
	 @GET
	 @Path("getAllVisitors")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Visitor> getAllVisitorss() {
		 return HibernateDAOFactory.getInstance().getVisitorDAO().getAllVisitors();
	 }
	 
	 @PUT
	 @Path("addvisitor/name/{fullname}/age/{age}/cinemahallid/{cinemahallid}")
	 public void addVisitor(@PathParam("fullname") String fullname, @PathParam("age") String age, @PathParam("cinemahallid") String cinemaHallId) {
		 Visitor visitor = new Visitor();
		 visitor.setFullName(fullname);
		 visitor.setAge(Integer.parseInt(age));
		 CinemaHall ch = HibernateDAOFactory.getInstance().getCinemaHallDAO().getCinemaHallById(Long.parseLong(cinemaHallId));
		 visitor.setCinemaHall(ch);
		 HibernateDAOFactory.getInstance().getVisitorDAO().createVisitor(visitor);
	 }
	 
	 @DELETE
	 @Path("deleteVisitor/{visitorid}")
	 public void deleteVisitor(@PathParam("visitorid") String visitorid) {
		 Long id = Long.parseLong(visitorid);
		 HibernateDAOFactory.getInstance().getVisitorDAO().deleteVisitorId(id);
	 }
	 
	 @POST
	 @Path("updateVisitor/id/{visitorid}/name/{fullname}/age/{age}/cinemahallid/{cinemahallid}")
	 public void updateVisitor(@PathParam("visitorid") String visitorid, @PathParam("fullname") String fullname, @PathParam("age") String age, @PathParam("cinemahallid") String cinemahallid) {
		 Long id = Long.parseLong(visitorid);
		 Visitor visitor = HibernateDAOFactory.getInstance().getVisitorDAO().getVisitorById(id);
		 visitor.setAge(Integer.parseInt(age));
		 visitor.setFullName(fullname);
		 CinemaHall ch = HibernateDAOFactory.getInstance().getCinemaHallDAO().getCinemaHallById(Long.parseLong(cinemahallid));
		 visitor.setCinemaHall(ch);
		 HibernateDAOFactory.getInstance().getVisitorDAO().updateVisitor(visitor);
	 }
}