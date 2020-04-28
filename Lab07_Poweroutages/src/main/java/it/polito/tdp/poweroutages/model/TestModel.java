package it.polito.tdp.poweroutages.model;

import java.util.*;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		System.out.println(model.getNercList());
		
		System.out.println("+++++++++++++++++");
		
		Nerc n = new Nerc(1, "ERCOT");
		System.out.println(model.getPowerOutagesDatoNerc(n));
		
		System.out.println("+++++++++++++++++");
		
		List<PowerOutages> soluzione = new ArrayList<>(model.getSoluzione(n, 2, 200));
		
		for(PowerOutages p : soluzione)
			System.out.println(p);
		
		System.out.println(model.sommaAnni(soluzione));
		System.out.println(model.getBestSomma());
		System.out.println(model.sommaOre(soluzione));
	

		
		

	}

}
