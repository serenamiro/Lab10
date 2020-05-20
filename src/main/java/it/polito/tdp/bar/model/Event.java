package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}

	private LocalTime time;
	private EventType type;
	private int num_persone;
	private double toll_random;
	
	public Event(LocalTime time, EventType type, int num_persone, double toll_random) {
		this.time = time;
		this.type = type;
		this.num_persone = num_persone;
		this.toll_random = toll_random;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
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

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", num_persone=" + num_persone + ", toll_random="
				+ toll_random + "]";
	}

	public double getToll_random() {
		return toll_random;
	}

	public void setToll_random(double toll_random) {
		this.toll_random = toll_random;
	}

	@Override
	public int compareTo(Event other) {
		// delega al comparatore di time
		return this.time.compareTo(other.time);
	}

	
	
	
}
