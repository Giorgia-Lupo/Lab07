package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<Nerc> nercList;
	private List<PowerOutages> powerOutagesDatoNerList;
	
	private int maxAnni;
	private int maxOre;
	
	private List<PowerOutages> soluzione;
	private int bestSomma;
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
		//nercList = this.podao.getNercList();
		//System.out.println(nercList);
	
		//for(Nerc n : this.nercList) 
			//powerOutagesDatoNerList = this.podao.getPowerOutagesDatoNerc(n);
			
	}
	
	public int getBestSomma() {
		return bestSomma;
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutages> getPowerOutagesDatoNerc(Nerc nerc) {
		powerOutagesDatoNerList = new ArrayList<>(podao.getPowerOutagesDatoNerc(nerc));
		return powerOutagesDatoNerList;
	}
	
	
	public List<PowerOutages> getSoluzione(Nerc nerc, int maxAnni, int maxOre ){
		
		this.soluzione = new ArrayList<>();		
		powerOutagesDatoNerList = new ArrayList<>(podao.getPowerOutagesDatoNerc(nerc));
	
		List<PowerOutages> parziale = new ArrayList<>();
		ricorsione(parziale, 0, maxAnni, maxOre);
		
		return soluzione;
	}

	private void ricorsione(List<PowerOutages> parziale, int livello, int maxAnni, int maxOre) {
	
		//caso terminale
		int ore = sommaOre(parziale);
		int anni = sommaAnni(parziale);
		int clienti = sommaClienti(parziale);
		
		if(ore>maxOre)
			return;
		
		if(anni>maxAnni)
			return;
	
		if(clienti> bestSomma) {
			soluzione = new ArrayList<>(parziale);
			bestSomma = clienti;
		}
		
		if(livello == powerOutagesDatoNerList.size())
			return;
		
		//provo ad aggiungere
		parziale.add(this.powerOutagesDatoNerList.get(livello));
		ricorsione(parziale, livello+1, maxAnni, maxOre);
		parziale.remove(this.powerOutagesDatoNerList.get(livello));
		
		//provo a non aggiungere
		ricorsione(parziale, livello+1, maxAnni, maxOre);
	

	}

	public int sommaClienti(List<PowerOutages> parziale) {
		int sommaClienti = 0;
		for(PowerOutages p : parziale) {
			sommaClienti += p.getClienti(); 
		}
		return sommaClienti;
	}
	
	public int sommaOre(List<PowerOutages> parziale) {
		int somma = 0;
		for(PowerOutages p : parziale) {
			somma += p.getDurataOre();
		}
		return somma;
	}

	public int sommaAnni(List<PowerOutages> parziale) {
		
		LocalDateTime prima = null;
		LocalDateTime dopo = null;
		int inizio = 0;
		int fine = 0;
		int anni = 0;
	
		for(PowerOutages p : parziale) {
			if(prima==null)
				prima = p.getDataInizio();
			else {
				if(p.getDataInizio().isBefore(prima))
					prima=p.getDataInizio();
			}
		}
		
		for(PowerOutages p : parziale) {
			if(dopo==null) 
				dopo = p.getDataFine();
			else {
				if(p.getDataFine().isAfter(dopo))
					dopo=p.getDataFine();
			}
			
		}
		
		if(prima!=null && dopo!=null) {
			inizio = prima.getYear();
			fine = dopo.getYear();
			
			anni = fine - inizio;
		}
		
		return anni;
	}

	
	

}
