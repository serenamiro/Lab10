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
	
	public String getSoddisfatti() {
		double div = (double)sim.getSoddisfatti()/(double)sim.getClienti()*100;
		String s = div+"%";
		return s;
	}
	
	public String getInsoddisfatti() {
		double div = (double)sim.getInsoddisfatti()/(double)sim.getClienti()*100;
		String s = div+"%";
		return s;
	}
}
