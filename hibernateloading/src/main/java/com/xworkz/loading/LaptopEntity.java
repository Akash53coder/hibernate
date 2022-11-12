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

	@Override
	public String toString() {
		return laptopid + " - " + laptopname ;
	}
	
}
