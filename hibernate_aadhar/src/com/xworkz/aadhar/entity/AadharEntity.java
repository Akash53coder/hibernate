package com.xworkz.aadhar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "aadhar_details")
@Entity
public class AadharEntity {
	@Id // annotation to define primary key
	@Column(name = "AADAR_NUMBER")
	@GeneratedValue(generator = "abc") // present in jpa for generating unique and auto increment value
	@GenericGenerator(name = "abc", strategy = "increment")
	// like some alogorithm
	// @GeneratedValue(strategy=GenerationType.SEQUENCE)// "
	private int aadharNo;
	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;
	@Column(name = "QR_PRESENT")
	private boolean isQrPresent;

	public AadharEntity() {

	}

	public AadharEntity(String cardHolderName, boolean isQrPresent) {
		super();
		this.cardHolderName = cardHolderName;
		this.isQrPresent = isQrPresent;
	}
	
	public AadharEntity(int aadharNo, String cardHolderName, boolean isQrPresent) {
		super();
		this.aadharNo = aadharNo;
		this.cardHolderName = cardHolderName;
		this.isQrPresent = isQrPresent;
	}

	public int getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(int aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public boolean getIsQrPresent() {
		return isQrPresent;
	}

	@Override
	public String toString() {
		return "AadharEntity [aadharNo=" + aadharNo + ", cardHolderName=" + cardHolderName + ", isQrPresent="
				+ isQrPresent + "]";
	}

	public void setQrPresent(boolean isQrPresent) {
		this.isQrPresent = isQrPresent;
	}

}
