package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO
	}

	private LocalDateTime time;
	private EventType type;
	private int num_persone;
	private double toll_random;
	
	public Event(LocalDateTime time, EventType type, int num_persone, double toll_random) {
		this.time = time;
		this.type = type;
		this.num_persone = num_persone;
		this.toll_random = toll_random;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num_persone;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		long temp;
		temp = Double.doubleToLongBits(toll_random);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Event other = (Event) obj;
		if (num_persone != other.num_persone)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (Double.doubleToLongBits(toll_random) != Double.doubleToLongBits(other.toll_random))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	
	
}
