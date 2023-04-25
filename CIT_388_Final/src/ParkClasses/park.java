package ParkClasses;
import api.generateParkInfo;

public class park implements generateParkInfo{

	private String location;
	private String name;
	private String datesOfOperation;
	private int parkRaiting;
	
	public park() {
		this.location = "";
		this.name = "";
		this.datesOfOperation = "";
		this.parkRaiting = 0;
	}
	public park(String l, String n, String dop, int gc) {
		this.location = l;
		this.name = n;
		this.datesOfOperation = dop;
		this.parkRaiting = gc;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatesOfOperation() {
		return datesOfOperation;
	}
	public void setDatesOfOperation(String datesOfOperation) {
		this.datesOfOperation = datesOfOperation;
	}
	public int getparkRaiting() {
		return parkRaiting;
	}
	public void setparkRaiting(int parkRaiting) {
		this.parkRaiting = parkRaiting;
	}
	
	@Override
	public String displayAllParkInfo() {
		return null;
	}
}
