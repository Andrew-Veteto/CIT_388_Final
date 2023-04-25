package ParkClasses;

public class employee {

	private int employeeID;
	private String name;
	private String job;
	
	public employee() {
		this.employeeID = 0;
		this.name = "";
		this.job = "";
	}
	public employee(int id, String n, String j) {
		this.employeeID = id;
		this.name = n;
		this.job = j;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return employeeID + ": " + name + ": " + job;
	}
}
