package it.polito.tdp.bar.model;

public class Model {
	
	private Simulator sim;
	
	public Model() {
		this.sim = new Simulator();
	}
	
	public void simula() {
		sim.init();
		sim.run();
	}
	
	public int getClienti() {
		return sim.getClienti();
	}
	
	public int getSoddisfatti() {
		return sim.getSoddisfatti();
	}
	
	public int getInsoddisfatti() {
		return sim.getInsoddisfatti();
	}
}
