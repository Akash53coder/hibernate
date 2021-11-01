package com.xworkz.mobile.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "mobile_table")
@Entity
public class MobileEntity {

	@Id
	@Column(name = "MOBILE_ID")
	private int mobileId;

	@Column(name = "MOBILE_BRAND")
	private String mobileBrand;

	@Column(name = "MOBILE_PRICE")
	private double mobilePrice;

	@Column(name = "MOBILE_ROM")
	private String mobileRom;

	@Column(name = "MOBILE_COLOR")
	private String mobileColor;

	public int getMobileId() {
		return mobileId;
	}

	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileBrand() {
		return mobileBrand;
	}

	public void setMobileBrand(String mobileBrand) {
		this.mobileBrand = mobileBrand;
	}

	public double getMobilePrice() {
		return mobilePrice;
	}

	public void setMobilePrice(double mobilePrice) {
		this.mobilePrice = mobilePrice;
	}

	public String getMobileRom() {
		return mobileRom;
	}

	public void setMobileRom(String mobileRom) {
		this.mobileRom = mobileRom;
	}

	public String getMobileColor() {
		return mobileColor;
	}

	public void setMobileColor(String mobileColor) {
		this.mobileColor = mobileColor;
	}

	public MobileEntity() {
		
	}
	
	public MobileEntity(int mobileId, String mobileBrand, double mobilePrice, String mobileRom, String mobileColor) {
		super();
		this.mobileId = mobileId;
		this.mobileBrand = mobileBrand;
		this.mobilePrice = mobilePrice;
		this.mobileRom = mobileRom;
		this.mobileColor = mobileColor;
	}

	@Override
	public String toString() {
		return "MobileEntity [mobileId=" + mobileId + ", mobileBrand=" + mobileBrand + ", mobilePrice=" + mobilePrice
				+ ", mobileRom=" + mobileRom + ", mobileColor=" + mobileColor + "]";
	}
	
	

}
