package ua.cn.stu.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String fullName;
	
	private Integer age;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CinemaHall cinemaHall;

	public Visitor() {
		super();
	}

	public Visitor(String fullName, Integer age, CinemaHall cinemaHall) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.cinemaHall = cinemaHall;
	}

	public Visitor(Long id, String fullName, Integer age, CinemaHall cinemaHall) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.cinemaHall = cinemaHall;
	}

	public Visitor(String string, Integer i) {
		// TODO Auto-generated constructor stub
		this.fullName = string;
		this.age = i;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}

	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", fullName=" + fullName + ", age=" + age + "]";
	}
	
	
	
	

}
