package com.xwrokz.ibank.customerdemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;

/*
 * 
 * About Named query
 * Named query should be defined with Entity class with @NamedQuery.
 * name should have by EntityClass.anyKeyword
 * to invoke them use Query qry = session.getNamedQuery("CustomerEntity.retreive");
 * and use @NamedQueries to have multiple queries defined
 * @NamedQueries({
 * 	  @NamedQuery(name = "", query=""),
 * 	  @NamedQuery(name = "", query="")
 * })
 * 
 */
@Entity
@Table(name = "Customer_Details")
@GenericGenerator(name ="idgen", strategy = "increment")
@NamedQuery(name="CustomerEntity.retreive", query="from CustomerEntity c where c.customerName=:name")
public class CustomerEntity {
	//Identifier Generator Strategy of hibernate
	//Increment : Takes the maximum value from the primary key column and increments it
	//Identity: uses an auto_increment column of database, this is DB dependent
	//it works for MYSQL but not for oracle
	//for this we need not to add @GenericGenerator as above
	//just use @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GeneratedValue(generator = "idgen")
	@Column(name = "CUSTOMERID")
	private int customerId;
	@Column(name = "CUSTOMERNAME")
	private String customerName;
	
	@Column(name = "CUSTOMERAGE")
	private int age;
	
	private String city;

	public CustomerEntity() {
	}

	public CustomerEntity(int custId, String custName, int age, String city) {
		this.customerId = custId;
		this.customerName = custName;
		this.age = age;
		this.city = city;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "[customerId=" + customerId + ", customerName=" + customerName + ", age=" + age
				+ ", city=" + city + "]";
	}

}
