package com.xworkz.aadhar;

import java.io.IOException;

import com.xworkz.aadhar.dao.AadharDAOImpl;

public class AadahrTester {
	public static void main(String[] args) throws IOException {
		AadharDAOImpl aadhar = new AadharDAOImpl();
		aadhar.saveAadharEntity();
		//aadhar.readAadharEntity();
		//aadhar.updateAadharEntity();
		//aadhar.deleteAadharEntity();
	}
}
