package com.xworkz.laptop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "laptop_details")
@Entity
public class LaptopEntity {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="laptop")
	@GenericGenerator(name="laptop",strategy="increment")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="price")
	private int price;
	
	@Column(name="ram")
	private int ram;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public LaptopEntity(String name, String brand, int price, int ram) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.ram = ram;
	}
	
	public LaptopEntity() {
		
	}

	@Override
	public String toString() {
		return "LaptopEntity [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", ram=" + ram
				+ "]";
	}
	
	
	
}
