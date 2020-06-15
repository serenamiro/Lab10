package it.polito.tdp.bar.model;

import java.time.Duration;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, // arriva un gruppo, verrà sistemato al tavolo oppure al bancone se accettano il bancone
		TAVOLO_LIBERATO // un gruppo precedentemente posto in un tavolo esce e libera il tavolo stesso
	}

	private Duration time; // orario di arrivo rispetto all'inizio della simulazione
	private EventType type;
	private int num_persone;
	private Duration durata;
	private double tolleranza; // quanto il gruppo è tollerante ad accettare il bancone o no
	private Tavolo tavolo;
	
	public Event(Duration time, EventType type, int num_persone, Duration durata, double tolleranza, Tavolo tavolo) {
		this.time = time;
		this.type = type;
		this.num_persone = num_persone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo = tavolo;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.time);
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", num_persone=" + num_persone + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + ", tavolo=" + tavolo + "]";
	}
	
	

	
}
