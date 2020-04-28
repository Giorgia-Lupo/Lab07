package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.Nerc;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;
		
			//for(Nerc n : dao.getNercList())
			Nerc n = new Nerc(1, "ERCOT");
			System.out.println(dao.getPowerOutagesDatoNerc(n));

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}

	}

}
