package ParkClasses;

import java.util.ArrayList;
import java.util.List;

public class guests {

	private int id;
	private String name;
	private double money;
	private double isHappy;
	private List<String> suvenirs = new ArrayList<String>();
	
	public guests() {
		this.id = 0;
		this.money = 0.0;
		this.isHappy = 0.0;
		this.suvenirs = new ArrayList<String>();
	}
	
	public guests(int ID) {
		this.id = ID;
		this.name = "";
		this.money = 0.0;
		this.isHappy = 0.0;
		this.suvenirs = new ArrayList<String>();
	}
	
	public guests(int ID, String n, double m, double ih, List<String> suvenires) {
		this.id = ID;
		this.name = n;
		this.money = m;
		this.isHappy = ih;
		this.suvenirs = suvenires;
	}
	
	@Override
	public String toString() {
		return id + ": " + name + ": " + money + ": " + isHappy + ": " + suvenirs;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getIsHappy() {
		return isHappy;
	}
	public void setIsHappy(double isHappy) {
		this.isHappy = isHappy;
	}

	public List<String> getSuvenirs() {
		return suvenirs;
	}

	public void setSuvenirs(List<String> suvenirs) {
		this.suvenirs = suvenirs;
	}
	
	public void addSuvenirs(List<String> suvenires) {
		this.suvenirs.addAll(suvenires);
	}
	
}
