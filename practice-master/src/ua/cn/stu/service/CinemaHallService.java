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

@Path("cinemahall")
public class CinemaHallService {
	 @GET
	 @Path("getAllCinemaHalls")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<CinemaHall> getAllCInemaHalls() {
		 return HibernateDAOFactory.getInstance().getCinemaHallDAO().getAllCinemaHalls();
	 }
	 
	 @GET
	 @Path("getTopTenVisitors/id/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Visitor> getTopTenVisitors(@PathParam("id") Long id) {
		
		 return HibernateDAOFactory.getInstance().getCinemaHallDAO().getTopTen(id);
	 }
	 
	 @PUT
	 @Path("addCinemaHall/name/{cinemaname}/hallname/{cinemahallname}")
	 public void addProduct(@PathParam("cinemaname") String cinemaname, @PathParam("cinemahallname") String cinemahallname) {
		 CinemaHall cinemaHall = new CinemaHall();
		 cinemaHall.setCinemaName(cinemaname);
		 cinemaHall.setHallName(cinemahallname);
		 HibernateDAOFactory.getInstance().getCinemaHallDAO().createCinemaHall(cinemaHall);
	 }
	 
	 @DELETE
	 @Path("deleteCinemaHall/{cinemahallid}")
	 public void deleteCinemaHall(@PathParam("cinemahallid") String cinemahallid) {
		 Long id = Long.parseLong(cinemahallid);
		 HibernateDAOFactory.getInstance().getCinemaHallDAO().deleteCinemaHallById(id);
	 }
	 
	 @POST
	 @Path("updateCinemaHall/id/{cinemahallid}/name/{cinemaname}/hallname/{cinemahallname}")
	 public void updateProduct(@PathParam("cinemahallid") String cinemahallid, @PathParam("cinemaname") String cinemaname, @PathParam("cinemahallname") String cinemahallname) {
		 Long id = Long.parseLong(cinemahallid);
		 CinemaHall cinemaHall = HibernateDAOFactory.getInstance().getCinemaHallDAO().getCinemaHallById(id);
		 cinemaHall.setCinemaName(cinemaname);
		 cinemaHall.setHallName(cinemahallname);
		 HibernateDAOFactory.getInstance().getCinemaHallDAO().updateCinemaHall(cinemaHall);
	 }
}