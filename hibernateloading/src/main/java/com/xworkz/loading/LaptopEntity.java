package com.xworkz.loading;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="laptops")
public class LaptopEntity {

	@Id
	private int laptopid;
	private String laptopname;
	//here userid is optional if we use @JoinColumn(name = "userid") in UserEntity
	private int userid;
	
	public LaptopEntity() {
		super();
	}

	public int getLaptopid() {
		return laptopid;
	}

	public void setLaptopid(int laptopid) {
		this.laptopid = laptopid;
	}

	public String getLaptopname() {
		return laptopname;
	}

	public void setLaptopname(String laptopname) {
		this.laptopname = laptopname;
	}
	

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return laptopid + " - " + laptopname ;
	}
	
}
