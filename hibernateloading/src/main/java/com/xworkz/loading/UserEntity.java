package com.xworkz.loading;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

	@Id
	private int userid;
	
	private String username;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid") 
	private List<LaptopEntity> laptops = new ArrayList<>();
	
	public UserEntity() {
		super();
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<LaptopEntity> getLaptops() {
		return laptops;
	}

	public void setLaptops(List<LaptopEntity> laptops) {
		this.laptops = laptops;
	}

	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", username=" + username + ", laptops=" + laptops + "]";
	}
	
}
