package it.polito.tdp.bar.model;

public class Statistiche {
	
	private int numClientiTot;
	private int numClientiSoddisfatti;
	private int numClientiInsoddisfatti;
	
	public Statistiche() {
		super();
		this.numClientiTot = 0;
		this.numClientiSoddisfatti = 0;
		this.numClientiInsoddisfatti = 0;
	}

	public int getNumClientiTot() {
		return numClientiTot;
	}

	public void setNumClientiTot(int numClientiTot) {
		this.numClientiTot = numClientiTot;
	}

	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}

	public void setNumClientiSoddisfatti(int numClientiSoddisfatti) {
		this.numClientiSoddisfatti = numClientiSoddisfatti;
	}

	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}

	public void setNumClientiInsoddisfatti(int numClientiInsoddisfatti) {
		this.numClientiInsoddisfatti = numClientiInsoddisfatti;
	}

	public void addNumClientiTot(int num_persone) {
		this.numClientiTot += num_persone;
	}

	public void addNumClientiSoddisfatti(int num_persone) {
		this.numClientiSoddisfatti += num_persone;
	}

	public void addNumClientiInsoddisfatti(int num_persone) {
		this.numClientiInsoddisfatti += num_persone;
	}
	
	
	

}
