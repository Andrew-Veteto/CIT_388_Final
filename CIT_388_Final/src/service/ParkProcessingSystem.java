package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ParkClasses.park;
import ParkClasses.employee;
import ParkClasses.guests;
import RideClasses.flatRide;
import RideClasses.ride;
import RideClasses.rollerCoaster;
import RideClasses.waterRide;

public class ParkProcessingSystem {

	private static int guestNum = 1;
	private String locationOfinterest;
	private String file;
	private List<guests> guest = new ArrayList<guests>();
	private List<String> suvenires = new ArrayList<String>();
	private List<employee> employees = new ArrayList<>();
	private List<ride> rides = new ArrayList<>();
	private List<park> park = new ArrayList<>();

	public ParkProcessingSystem() {
	};

	public void load(Path pathToPark) throws IOException {
		// Purging local data
		guest.clear();
		suvenires.clear();
		employees.clear();
		rides.clear();
		park.clear();

		// Loading File
		try (Scanner input = new Scanner(pathToPark)) {
			String name = input.nextLine();
			String address = input.nextLine();
			String days = input.nextLine();
			int num = input.nextInt();
			park p = new park();
			p.setName(name);
			p.setLocation(address);
			p.setDatesOfOperation(days);
			p.setparkRaiting(num);
			park.add(p);

			String clear = input.nextLine();
			String IDArea = input.nextLine();
			int count = 0;
			if (IDArea.equals("EMPLOYEES")) {
				count = 1;
				while (input.hasNext()) {
					employee temp = new employee();
					name = input.next();
					if (name.equals("RIDES")) {
						break;
					} else {
						String job = input.next();
						temp.setEmployeeID(count);
						System.out.println(count);
						temp.setName(name);
						temp.setJob(job);
						count++;
						employees.add(temp);
					}
				}
			}

			if (name.equals("RIDES")) {
				count = 1;
				while (input.hasNext()) {
					Double ctm = 0.0;
					try {
						ctm = input.nextDouble();
					} catch (Exception e) {
						break;
					}
					String theme = input.next();
					Double ptb = input.nextDouble();
					name = input.next();
					clear = input.nextLine();
					String desc = input.nextLine();
					String manu = input.next();
					int height = input.nextInt();
					String type = input.next();

					if (type.equals("flatRide")) {
						clear = input.nextLine();
						String mt = input.nextLine();
						String ic = input.nextLine();
						flatRide temp = new flatRide();
						temp.setIntensityCat(ic);
						temp.setMovementType(mt);
						temp.setCostToMaintain(ctm);
						temp.setTheme(theme);
						temp.setPriceToBuild(ptb);
						temp.setName(name);
						temp.setDescription(desc);
						temp.setManufacturer(manu);
						temp.setHeightRequirement(height);
						rides.add(temp);
					}
					if (type.equals("waterRide")) {
						clear = input.nextLine();
						String bt = input.nextLine();
						String t = input.nextLine();
						boolean track = false;
						if (t.equals("true")) {
							track = true;
						}
						waterRide temp = new waterRide();
						temp.setBoatType(bt);
						temp.setTracked(track);
						temp.setCostToMaintain(ctm);
						temp.setTheme(theme);
						temp.setPriceToBuild(ptb);
						temp.setName(name);
						temp.setDescription(desc);
						temp.setManufacturer(manu);
						temp.setHeightRequirement(height);
						rides.add(temp);
					}
					if (type.equals("rollerCoaster")) {
						clear = input.nextLine();
						String tt = input.nextLine();
						String ic = input.next();
						String pet = input.next();
						rollerCoaster temp = new rollerCoaster();
						temp.setCostToMaintain(ctm);
						temp.setTheme(theme);
						temp.setPriceToBuild(ptb);
						temp.setName(name);
						temp.setDescription(desc);
						temp.setManufacturer(manu);
						temp.setHeightRequirement(height);
						temp.setTrackType(tt);
						temp.setIntensityCat(ic);
						temp.setPotentialEnergyType(pet);
						rides.add(temp);
					}
				}

			} // This is Rides

			// This loads the guests

			IDArea = input.nextLine();
			count = 1;
			while (input.hasNext()) {
				guests temp = new guests();
				name = input.next();
				Double money = input.nextDouble();
				double happyLevel = input.nextDouble();
				int amountOfSuvenires = input.nextInt();
				temp.setId(count);
				temp.setName(name);
				temp.setMoney(money);
				temp.setIsHappy(happyLevel);
				if (amountOfSuvenires > 0) {
					for (int i = 0; i < amountOfSuvenires; i++) {
						String item = input.next();
						suvenires.add(item);
					}
					temp.addSuvenirs(suvenires);
				}
				guest.add(temp);
				count++;
				suvenires.clear();
			}

		} // This is Park
	}

	// Park Stuff
	public park createPark() {
		park p = new park();
		return p;
	}
	
	public List<park> getPark() {
		return park;
	}

	// Guests Stuff
	public guests createGuests() {
		guests guest = new guests();
		return guest;
	}

	public List<guests> getGuests() {
		return guest;
	}

	// Employee Stuff
	public employee createEmployee() {
		employee e = new employee();
		return e;
	}

	public List<employee> getEmployees() {
		return employees;
	}

	// Ride Stuff
	public ride createRide() {
		ride rides = new ride();
		return rides;
	}

	public List<ride> getRides() {
		return rides;
	}

	public flatRide createFlatRide() {
		flatRide rides = new flatRide();
		return rides;
	}

	public waterRide createWaterRide() {
		waterRide rides = new waterRide();
		return rides;
	}

	public rollerCoaster createRollerCoaster() {
		rollerCoaster rides = new rollerCoaster();
		return rides;
	}

	// Display Stuff
	public String getLocationOfinterest() {
		return locationOfinterest;
	}

	public void setLocationOfinterest(String locationOfinterest) {
		this.locationOfinterest = locationOfinterest;
	}

	// File Stuff
	public String getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file.getName();
	}

	public void newFileSetUp() {
		// Purging local data
		guest.clear();
		suvenires.clear();
		employees.clear();
		rides.clear();
		park.clear();
	}
}
