package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	// PARAMETRI DI SIMULAZIONE
	private int counter;
	private double tolleranza_sim;
	
	// MODELLO DEL MONDO
	private final LocalDateTime apertura = LocalDateTime.of(2020, 05, 20, 10, 00);
	private final LocalDateTime chiusura = LocalDateTime.of(2020, 05, 20, 22, 00);
	Map<Integer, Integer> tavoli_disp = new HashMap<Integer, Integer>();

	// VALORI DA CALCOLARE
	private int clienti;
	private int soddisfatti;
	private int insoddisfatti;
	
	public void init() {
		this.queue = new PriorityQueue<Event>();
		tavoli_disp.put(10, 2);
		tavoli_disp.put(8, 4);
		tavoli_disp.put(6, 4);
		tavoli_disp.put(4, 5);
		
		this.counter = 1;
		this.tolleranza_sim = 0.7;
		
		this.clienti = this.insoddisfatti = this.soddisfatti = 0;
	
	}
	
	public void run() {
		LocalDateTime oraArrivoGruppo = this.apertura;
		do{ int num_pers = (int) (Math.random()*10+1);
			double toll_gruppo = Math.random();
			Event e = new Event(oraArrivoGruppo, EventType.ARRIVO_GRUPPO_CLIENTI, num_pers, toll_gruppo);
			this.queue.add(e);
			int random = (int)(Math.random()*10+1);
			Duration arrivi = Duration.of(random, ChronoUnit.MINUTES);
			
			oraArrivoGruppo = oraArrivoGruppo.plus(arrivi);
			counter++;
		}while(counter<=2000);
		
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			if(e.getTime().isBefore(this.chiusura)) {
				System.out.println(e);
				processEvent(e);
			}
		}
	}
	
	private int checkTavolo(Event e) {
		int assegnato = -1;
		for(int i = e.getNum_persone(); i<2*e.getNum_persone(); i++) {
			for (int chiave : this.tavoli_disp.keySet()) {
				if(chiave == i) {
					int temp = this.tavoli_disp.get(chiave);
					if(temp>0) {
						this.tavoli_disp.put(i, this.tavoli_disp.get(i)-1);
						assegnato = i;
						return assegnato;
					}
				}
			}
		}
		return assegnato;
	}

	private void processEvent(Event e) {
		
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			
			if(checkTavolo(e) != -1) {
				this.clienti += e.getNum_persone();
				this.soddisfatti += e.getNum_persone();
				
				int random2 = (int)(Math.random()*61+60);
				Duration durata = Duration.of(random2, ChronoUnit.MINUTES);
				
				Event nuovo = new Event(e.getTime().plus(durata), EventType.TAVOLO_LIBERATO, checkTavolo(e), e.getToll_random());
				this.queue.add(nuovo);
				
			} else if(e.getToll_random()<=this.tolleranza_sim) {
				// si siedono al bancone
				this.clienti += e.getNum_persone();
				this.soddisfatti += e.getNum_persone();
				
			} else {
				this.clienti += e.getNum_persone();
				this.insoddisfatti += e.getNum_persone();
			}
			break;
			
		case TAVOLO_LIBERATO:
			for (int chiave : this.tavoli_disp.keySet()) {
				if(chiave == e.getNum_persone()) {
						this.tavoli_disp.put(chiave, this.tavoli_disp.get(chiave)+1);
					}
				}
			break;
		}
	}
	
	public int getClienti() {
		return this.clienti;
	}
	
	public int getSoddisfatti() {
		return this.soddisfatti;
	}
	
	public int getInsoddisfatti() {
		return this.insoddisfatti;
	}
	
	
	
	
}
