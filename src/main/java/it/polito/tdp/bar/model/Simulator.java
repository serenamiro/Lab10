package it.polito.tdp.bar.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	// MODELLO DEL MONDO
	private List<Tavolo> tavoli;
	
	// PARAMETRI DI SIMULAZIONE
	private int NUM_EVENTI = 2000;
	private int T_MIN_ARRIVO_MAX = 10;
	private int NUM_PERSONE_MAX = 10; // gruppi max di 10 persone
	private int DURATA_MIN = 60;
	private int DURATA_MAX = 120;
	private double TOLLERANZA_MAX= 0.9;
	private double OCCUPAZIONE_MIN = 0.5;
	
	// VALORI DA CALCOLARE
	private Statistiche stat;
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	
	private void caricaTavoli() {
		this.tavoli = new ArrayList<>();
		
		aggiungiTavolo(2, 10);
		aggiungiTavolo(4, 8);
		aggiungiTavolo(4, 6);
		aggiungiTavolo(5, 4);
		
		// ordinare l'ArrayList in modo da poter "ciclare" sui tavoli per trovare il più piccolo tavolo disponibile
		Collections.sort(this.tavoli, new Comparator<Tavolo>() {
			@Override
			public int compare(Tavolo o1, Tavolo o2) {
				return o1.getnPosti()-o2.getnPosti();
			}
		});
	}
	

	private void aggiungiTavolo(int num, int nPosti) {
		for(int i = 0; i<num; i++) {
			Tavolo t = new Tavolo(nPosti, false);
			this.tavoli.add(t);
		}
	}
	
	
	private void caricaEventi() {
		Duration arrivo = Duration.ofMinutes(0); // non ci sono indicazioni di tempo, per cui partiamo al tempo 0
		
		for(int i = 0; i<this.NUM_EVENTI; i++) {
			// num persone = valore casuale tra 1 e 10
			int numPersone = (int)(Math.random()*this.NUM_PERSONE_MAX + 1); 
			
			// valore casuale tra 60 e 120 minuti
			Duration durata = Duration.ofMinutes(this.DURATA_MIN + (int)(Math.random()*(this.DURATA_MAX-this.DURATA_MIN)));
			
			// tolleranza = float tra 0.0 e 0.9
			double tolleranza = Math.random()*this.TOLLERANZA_MAX;
			
			// ATTENZIONE: il tavolo non viene assegnato in questo momento
			Event e = new Event(arrivo, EventType.ARRIVO_GRUPPO_CLIENTI, numPersone, durata, tolleranza, null);
			this.queue.add(e);
			
			// i gruppi arrivano al massimo ogni 10 minuti
			arrivo = arrivo.plusMinutes(1 + (int)(Math.random()*this.T_MIN_ARRIVO_MAX));
		}
	}
	
	
	
	public void init() {
		caricaTavoli();
		this.queue = new PriorityQueue<Event>();
		caricaEventi();
		this.stat = new Statistiche();
	}
	
	public void run() {		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}
	
	
	private void processEvent(Event e) {
		
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			
			stat.addNumClientiTot(e.getNum_persone());
			Tavolo trovato = null;
			// cerca un tavolo
			for(Tavolo t : this.tavoli) {
				if(!t.isOccupato() && t.getnPosti()>=e.getNum_persone() && t.getnPosti()*this.OCCUPAZIONE_MIN<=e.getNum_persone()){
					trovato = t;
					break;
				}
			}
			
			if(trovato != null) {
				System.out.format("Sedute %d persone a tavolo da %d posti", e.getNum_persone(), trovato.getnPosti());
				stat.addNumClientiSoddisfatti(e.getNum_persone());
				trovato.setOccupato(true);
				// un nuovo evento sarà generato perchè il gruppo libererà il tavolo
				Event ev = new Event(e.getTime().plus(e.getDurata()), EventType.TAVOLO_LIBERATO, e.getNum_persone(), e.getDurata(), e.getTolleranza(), trovato);
				this.queue.add(ev);
			} else {
				double bancone = Math.random(); // [0, 1)
				if(bancone <= e.getTolleranza()) {
					// Si, le persone accettano di andare al bancone
					stat.addNumClientiSoddisfatti(e.getNum_persone());
				} else {
					// Le persone se ne vanno
					stat.addNumClientiInsoddisfatti(e.getNum_persone());
				}
			}
			break;
			
		case TAVOLO_LIBERATO:
			e.getTavolo().setOccupato(false);
			// quindi il tavolo può ospitare altri clienti
			break;
		}
	}
	
	public Statistiche getStat() {
		return this.stat;
	}
	
	
	
}
