package ua.cn.stu.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class CinemaHall {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String hallName;
	
	private String cinemaName;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cinemaHall", orphanRemoval = true)
	private Set<Visitor> visitors = new HashSet<Visitor>();

	public CinemaHall() {
		super();
	}
	
	

	public CinemaHall(String hallName, String cinemaName, Set<Visitor> visitors) {
		super();
		this.hallName = hallName;
		this.cinemaName = cinemaName;
		this.visitors = visitors;
	}



	public CinemaHall(Long id, String hallName, String cinemaName, Set<Visitor> visitors) {
		super();
		this.id = id;
		this.hallName = hallName;
		this.cinemaName = cinemaName;
		this.visitors = visitors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public Set<Visitor> getVisitors() {
		return visitors;
	}

	
	public void addVisitor(Visitor visitor) {
		this.visitors.add(visitor);
	}



	@Override
	public String toString() {
		return "CinemaHall [id=" + id + ", hallName=" + hallName + ", cinemaName=" + cinemaName + ", visitors="
				+ visitors + "]";
	}
	
}
