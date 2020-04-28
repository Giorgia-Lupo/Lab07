package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages {

	private int id;
	private Nerc nerc;
	private int clienti;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private long durataOre;
	
	public PowerOutages(int id, Nerc nerc, int clienti, LocalDateTime dataInizio, LocalDateTime dataFine) {
		super();
		this.id = id;
		this.nerc = nerc;;
		this.clienti = clienti;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		
		
		this.durataOre = Duration.between(dataInizio, dataFine).getSeconds()/3600;
		
	}

	public int getId() {
		return id;
	}

	public long getDurataOre() {
		return durataOre;
	}

	public void setDurataOre(long durataOre) {
		this.durataOre = durataOre;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc nerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getClienti() {
		return clienti;
	}

	public void setClienti(int clienti) {
		this.clienti = clienti;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	@Override
	public String toString() {
		return String.format("%-20s %-20s %-10s %-20s", dataInizio, dataFine, durataOre, clienti);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
