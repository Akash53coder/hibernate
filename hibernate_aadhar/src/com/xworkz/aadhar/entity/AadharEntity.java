package com.xworkz.aadhar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "aadhar_details")
@Entity
public class AadharEntity {
	@Id
	@Column(name = "AADAR_NUMBER")
	private int aadharNo;
	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;
	@Column(name = "QR_PRESENT")
	private boolean isQrPresent;

	public AadharEntity() {

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
