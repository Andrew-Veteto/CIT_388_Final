package RideClasses;

public class waterRide extends ride{

	private String boatType;
	private boolean isTracked;
	
	public waterRide() {
		super();
		this.boatType = "";
		this.isTracked = false;
	}
	public waterRide(double c, String t, double p, String n, String d, String m, int h, String bt, boolean is) {
		super(c, t, p, n, d, m, h);
		this.boatType = bt;
		this.isTracked = is;
	}
	public String getBoatType() {
		return boatType;
	}
	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}
	public boolean isTracked() {
		return isTracked;
	}
	public void setTracked(boolean isTracked) {
		this.isTracked = isTracked;
	}
}