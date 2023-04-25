package RideClasses;

public class flatRide extends ride{

	private String movementType;
	private String intensityCat;
	
	public flatRide() {
		super();
		this.movementType = "";
		this.intensityCat = "";
	}
	public flatRide(double c, String t, double p, String n, String d, String m, int h, String mt, String ic) {
		super(c, t, p, n, d, m, h);
		this.movementType = mt;
		this.intensityCat = ic;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public String getIntensityCat() {
		return intensityCat;
	}
	public void setIntensityCat(String intensityCat) {
		this.intensityCat = intensityCat;
	}
}
