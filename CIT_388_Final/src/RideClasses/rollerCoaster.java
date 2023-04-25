package RideClasses;

public class rollerCoaster extends ride{

	private String trackType;
	private String intensityCat;
	private String potentialEnergyType;
	
	public rollerCoaster() {
		super();
		this.trackType = "";
		this.intensityCat = "";
		this.potentialEnergyType = "";
	}
	public rollerCoaster(double c, String t, double p, String n, String d, String m, int h, String tt, String ic, String pet) {
		super(c, t, p, n, d, m, h);
		this.trackType = tt;
		this.intensityCat = ic;
		this.potentialEnergyType = pet;
	}
	public String getTrackType() {
		return trackType;
	}
	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}
	public String getIntensityCat() {
		return intensityCat;
	}
	public void setIntensityCat(String intensityCat) {
		this.intensityCat = intensityCat;
	}
	public String getPotentialEnergyType() {
		return potentialEnergyType;
	}
	public void setPotentialEnergyType(String potentialEnergyType) {
		this.potentialEnergyType = potentialEnergyType;
	}
}