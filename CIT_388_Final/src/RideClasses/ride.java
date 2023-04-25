package RideClasses;

public class ride {
	private double costToMaintain;
	private String theme;
	private double priceToBuild;
	private String name;
	private String description;
	private String manufacturer;
	private int heightRequirement;
	
	public ride() {
		this.costToMaintain = 0.0;
		this.theme = "";
		this.priceToBuild = 0.0;
		this.name = "";
		this.description = "";
		this.manufacturer = "";
		this.heightRequirement = 0;
	}
	
	public ride(double c, String t, double p, String n, String d, String m, int h) {
		this.costToMaintain = c;
		this.theme = t;
		this.priceToBuild = p;
		this.name = n;
		this.description = d;
		this.manufacturer = m;
		this.heightRequirement = h;
	}

	
	public double getCostToMaintain() {
		return costToMaintain;
	}

	public void setCostToMaintain(double costToMaintain) {
		this.costToMaintain = costToMaintain;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public double getPriceToBuild() {
		return priceToBuild;
	}

	public void setPriceToBuild(double priceToBuild) {
		this.priceToBuild = priceToBuild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getHeightRequirement() {
		return heightRequirement;
	}

	public void setHeightRequirement(int heightRequirement) {
		this.heightRequirement = heightRequirement;
	}
}
