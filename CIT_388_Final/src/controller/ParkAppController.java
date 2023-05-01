package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import ParkClasses.employee;
import ParkClasses.guests;
import ParkClasses.park;
import RideClasses.flatRide;
import RideClasses.ride;
import RideClasses.rollerCoaster;
import RideClasses.waterRide;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import javafx.util.Callback;
import service.ParkProcessingSystem;

public class ParkAppController {

	// FXML References
	@FXML
	private Button employeesBtn;
	@FXML
	private Button guestsBtn;
	@FXML
	private Button ridesBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private Button loadBtn;
	@FXML
	private TextArea middleDisplay;
	@FXML
	private ListView<String> lineDisplay;
	@FXML
	private Button newObjBtn;
	@FXML
	private Button removeBtn;
	@FXML
	private Button allInfoBtn;
	@FXML
	private Button parkInfoBtn;
	@FXML
	private TextField numEmployees;
	@FXML
	private TextField numGuests;
	@FXML
	private TextField numRides;
	@FXML
	private TextField parkName;
	@FXML
	private TextField parkLocation;
	@FXML
	private TextField datesOfOperation;
	@FXML
	private TextField parkScore;
	@FXML
	private Button updatePark;
	@FXML
	private Button newFileBtn;

	@FXML
	void initialize() {
		// Need File Path to start
		ParkProcessingSystem system = new ParkProcessingSystem();

		// Hiding Buttons until a file is open
		changeButtonVis(true);
		System.out.println(system.getFile());
		// All the Buttons
		employeesBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> name = FXCollections.observableArrayList();
				ObservableList<String> job = FXCollections.observableArrayList();
				if (name.size() == 0) {
					for (int i = 0; i < system.getEmployees().size(); i++) {
						name.addAll(system.getEmployees().get(i).getName());
					}
				}
				if (job.size() == 0) {
					for (int i = 0; i < system.getEmployees().size(); i++) {
						job.addAll(system.getEmployees().get(i).getJob());
					}
				}
				system.setLocationOfinterest("employees");
				lineDisplay.setItems(name);
				lineDisplay.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
					@Override
					public ListCell<String> call(ListView<String> param) {
						return new ListCell<String>() {
							@Override
							protected void updateItem(String order, boolean empty) {
								super.updateItem(order, empty);
								if (order == null || empty) {
									setText(null);
								} else {
									// what will show in the UI
									setText(name.get(getIndex()) + "\n" + job.get(getIndex()));
								}
							}
						};
					}
				});

			}
		});
		guestsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> name = FXCollections.observableArrayList();
				ObservableList<String> money = FXCollections.observableArrayList();
				if (name.size() == 0) {
					for (int i = 0; i < system.getGuests().size(); i++) {
						name.addAll(system.getGuests().get(i).getName());
					}
				}
				if (money.size() == 0) {
					for (int i = 0; i < system.getGuests().size(); i++) {
						Double x = system.getGuests().get(i).getMoney();
						String y = Double.toString(x);
						money.addAll(y);
					}
				}
				system.setLocationOfinterest("guests");
				lineDisplay.setItems(name);
				lineDisplay.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
					@Override
					public ListCell<String> call(ListView<String> param) {
						return new ListCell<String>() {
							@Override
							protected void updateItem(String order, boolean empty) {
								super.updateItem(order, empty);
								if (order == null || empty) {
									setText(null);
								} else {
									// what will show in the UI
									setText(name.get(getIndex()) + "\n" + "$" + money.get(getIndex()));
								}
							}
						};
					}
				});

			}
		});
		ridesBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> name = FXCollections.observableArrayList();
				ObservableList<String> desc = FXCollections.observableArrayList();
				if (name.size() == 0) {
					for (int i = 0; i < system.getRides().size(); i++) {
						name.addAll(system.getRides().get(i).getName());
					}
				}
				if (desc.size() == 0) {
					for (int i = 0; i < system.getRides().size(); i++) {
						String x = system.getRides().get(i).getDescription();
						desc.addAll(x);
					}
				}
				system.setLocationOfinterest("rides");
				lineDisplay.setItems(name);
				lineDisplay.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
					@Override
					public ListCell<String> call(ListView<String> param) {
						return new ListCell<String>() {
							@Override
							protected void updateItem(String order, boolean empty) {
								super.updateItem(order, empty);
								if (order == null || empty) {
									setText(null);
								} else {
									// what will show in the UI
									setText(name.get(getIndex()) + "\n" + desc.get(getIndex()));
								}
							}
						};
					}
				});

			}
		});
		newObjBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog input = new TextInputDialog();
				input.setTitle("New object");
				input.setHeaderText("Choose what object you want to add:" + "\n" + "1. Employee" + "\n" + "2. Guest"
						+ "\n" + "3. Ride");
				input.setContentText("(1-3)");
				Optional<String> result = input.showAndWait();

				int choice = 0;
				boolean loop = false;
				boolean safe = false;
				Alert a = new Alert(null, "Invalid Input!", ButtonType.CLOSE);
				Alert a4 = new Alert(null, "Name already Exists!", ButtonType.CLOSE);
				Alert a5 = new Alert(null, "ID already Exists!", ButtonType.CLOSE);
				if (result.isPresent()) {
					try {
						String temp = result.get();
						choice = Integer.parseInt(temp);
						if (choice >= 4 || choice <= 0) {
							a.showAndWait();
						} else {
							switch (choice) {
							case 1:
								int EID = 0;
								String EName = "";
								String EJob = "";
								while (loop == false) {
									input.setTitle("New Employee");
									input.setHeaderText("Enter in the employee ID:");
									input.setContentText("ID: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											EID = Integer.parseInt(Eresult.get());

											if (system.getEmployees().size() == 0) {
												EID = Integer.parseInt(Eresult.get());
												safe = true;
											} else {
												for (int i = 0; i < system.getEmployees().size(); i++) {
													if (system.getEmployees().get(i).getEmployeeID() == EID) {
														System.out.println(system.getEmployees().size());
														safe = false;
														break;
													} else {
														safe = true;
													}
												}
											}

											if (safe) {
												EID = Integer.parseInt(Eresult.get());
												loop = true;
											} else {
												a5.showAndWait();
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								safe = false;
								while (loop == false) {
									input.setHeaderText("Enter in the employee Name:");
									input.setContentText("Name: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										if (system.getEmployees().size() == 0) {
											EName = Eresult.get();
											loop = true;
										}
										try {
											for (int i = 0; i < system.getEmployees().size(); i++) {
												if (system.getEmployees().get(i).getName().equals(Eresult.get())) {
													safe = false;
													break;
												} else {
													safe = true;
												}
											}
											if (safe) {
												EName = Eresult.get();
												loop = true;
											} else {
												a4.showAndWait();
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									ChoiceDialog<String> input2 = new ChoiceDialog<>("custodian", "mechanic",
											"security", "ride operator");
									input2.setTitle("New Employee");
									input2.setHeaderText("Enter in the employees Job:");
									input2.setContentText("Job: ");
									Optional<String> Eresult = input2.showAndWait();
									if (Eresult.isPresent()) {
										try {
											EJob = Eresult.get();
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								employee b = system.createEmployee();
								b.setEmployeeID(EID);
								b.setName(EName);
								b.setJob(EJob);
								system.getEmployees().add(b);
								numEmployees.setText(system.getEmployees().size() + "");
								break;
							case 2:
								int GID = 0;
								String GName = "";
								double GMoney = 0.0;
								double GHappy = 0.0;
								List<String> items = new ArrayList<String>();

								while (loop == false) {
									input.setTitle("New Guest");
									input.setHeaderText("Enter in the guest ID:");
									input.setContentText("ID: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {

											if (system.getGuests().size() == 0) {
												GID = Integer.parseInt(Eresult.get());
												safe = true;
											}

											GID = Integer.parseInt(Eresult.get());
											for (int i = 0; i < system.getGuests().size(); i++) {
												if (system.getGuests().get(i).getId() == GID) {
													safe = false;
													break;
												} else {
													safe = true;
												}
											}
											if (safe) {
												GID = Integer.parseInt(Eresult.get());
												loop = true;
											} else {
												a5.showAndWait();
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								safe = false;
								while (loop == false) {
									input.setHeaderText("Enter in the guest Name:");
									input.setContentText("Name: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {

											if (system.getGuests().size() == 0) {
												GName = Eresult.get();
												safe = true;
											}

											GName = Eresult.get();
											for (int i = 0; i < system.getGuests().size(); i++) {
												if (system.getGuests().get(i).getName().equals(Eresult.get())) {
													safe = false;
													break;
												} else {
													safe = true;
												}
											}
											if (safe) {
												GName = Eresult.get();
												loop = true;
											} else {
												a4.showAndWait();
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the guests money:");
									input.setContentText("money: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											int temp2 = Integer.parseInt(Eresult.get());
											if (temp2 < 0) {
												a.showAndWait();
											} else {
												GMoney = temp2;
												loop = true;
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the guests happines level:");
									input.setContentText("level: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											int temp2 = Integer.parseInt(Eresult.get());
											if (temp2 < 0) {
												a.showAndWait();
											} else {
												GHappy = temp2;
												loop = true;
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Do you want to add a suvenir?");
									input.setContentText("(y/n)");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										if (Eresult.get().equals("y")) {
											input.setHeaderText("Enter in any suvenirs:");
											input.setContentText("suvenirs: ");
											Optional<String> Aresult = input.showAndWait();
											if (Aresult.isPresent()) {
												try {
													items.add(Aresult.get());
												} catch (Exception e) {
													a.showAndWait();
												}
											}
										} else if (Eresult.get().equals("n")) {
											loop = true;
										} else {
											a.showAndWait();
										}
									}
								}
								guests c = system.createGuests();
								c.setId(GID);
								c.setName(GName);
								c.setMoney(GMoney);
								c.setIsHappy(GHappy);
								c.addSuvenirs(items);
								system.getGuests().add(c);
								numGuests.setText(system.getGuests().size() + "");
								break;
							case 3:
								double CTM = 0.0;
								String THEME = "";
								double PTB = 0.0;
								String RName = "";
								String RDesc = "";
								String Manu = "";
								int Height = 0;
								while (loop == false) {
									input.setTitle("New Ride");
									input.setHeaderText("Enter in the Rides name:");
									input.setContentText("name: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {

											if (system.getRides().size() == 0) {
												RName = Eresult.get();
												safe = true;
											}

											RName = Eresult.get();
											for (int i = 0; i < system.getRides().size(); i++) {
												if (system.getRides().get(i).getName().equals(Eresult.get())) {
													safe = false;
													break;
												} else {
													safe = true;
												}
											}
											if (safe) {
												RName = Eresult.get();
												loop = true;
											} else {
												a4.showAndWait();
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the rides theme:");
									input.setContentText("theme: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											THEME = Eresult.get();
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the rides description:");
									input.setContentText("description: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											RDesc = Eresult.get();
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the rides manufacturer:");
									input.setContentText("manufacturer: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											Manu = Eresult.get();
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the height requirement:");
									input.setContentText("height: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											temp = Eresult.get();
											Height = Integer.parseInt(temp);
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the cost to build:");
									input.setContentText("cost: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											temp = Eresult.get();
											PTB = Double.parseDouble(temp);
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("Enter in the cost to maintain:");
									input.setContentText("height: ");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											temp = Eresult.get();
											CTM = Double.parseDouble(temp);
											loop = true;
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								loop = false;
								while (loop == false) {
									input.setHeaderText("What type of ride is this: " + "\n" + "1. Flat Ride" + "\n"
											+ "2. Water Ride" + "\n" + "3. RollerCoaster");
									input.setContentText("(1-3)");
									Optional<String> Eresult = input.showAndWait();
									if (Eresult.isPresent()) {
										try {
											choice = Integer.parseInt(Eresult.get());
											if ((choice >= 4 || choice <= 0)) {
												a.showAndWait();
											} else {
												loop = true;
												switch (choice) {
												case 1:
													String mt = "";
													String ic = "";
													input.setHeaderText("Enter in the movment type:");
													input.setContentText("type: ");
													Optional<String> Eresult2 = input.showAndWait();
													mt = Eresult2.get();
													input.setHeaderText("Enter in intensity category:");
													input.setContentText("category: ");
													Optional<String> Eresult3 = input.showAndWait();
													ic = Eresult3.get();
													flatRide d = system.createFlatRide();
													d.setCostToMaintain(CTM);
													d.setDescription(RDesc);
													d.setHeightRequirement(Height);
													d.setManufacturer(Manu);
													d.setName(RName);
													d.setPriceToBuild(PTB);
													d.setTheme(THEME);
													d.setMovementType(mt);
													d.setIntensityCat(ic);
													system.getRides().add(d);
													numRides.setText(system.getRides().size() + "");
													break;
												case 2:
													loop = false;
													String bt = "";
													boolean tracked = false;
													input.setHeaderText("What type of boat are used");
													input.setContentText("desc: ");
													Optional<String> Eresult4 = input.showAndWait();
													bt = Eresult4.get();
													while (loop == false) {
														input.setHeaderText("Does the ride tracked?");
														input.setContentText("(y/n)");
														Optional<String> Eresult5 = input.showAndWait();
														if (Eresult5.get().equals("y")) {
															tracked = true;
															loop = true;
														} else {
															a.showAndWait();
														}
													}
													waterRide e = system.createWaterRide();
													e.setBoatType(bt);
													e.setCostToMaintain(CTM);
													e.setDescription(RDesc);
													e.setHeightRequirement(Height);
													e.setManufacturer(Manu);
													e.setName(RName);
													e.setPriceToBuild(PTB);
													e.setTheme(THEME);
													e.setTracked(tracked);
													system.getRides().add(e);
													numRides.setText(system.getRides().size() + "");
													break;
												case 3:
													String tt = "";
													ic = "";
													String pet = "";
													input.setHeaderText("What is the orientation of the track?");
													input.setContentText("desc: ");
													Optional<String> Eresult5 = input.showAndWait();
													tt = Eresult5.get();
													input.setHeaderText("Enter in intensity category:");
													input.setContentText("category: ");
													Optional<String> Eresult6 = input.showAndWait();
													ic = Eresult6.get();
													input.setHeaderText("How does the ride get energy?");
													input.setContentText("desc: ");
													Optional<String> Eresult7 = input.showAndWait();
													pet = Eresult7.get();
													rollerCoaster f = system.createRollerCoaster();
													f.setCostToMaintain(CTM);
													f.setDescription(RDesc);
													f.setHeightRequirement(Height);
													f.setIntensityCat(ic);
													f.setManufacturer(Manu);
													f.setName(RName);
													f.setPotentialEnergyType(pet);
													f.setPriceToBuild(PTB);
													f.setTheme(THEME);
													f.setTrackType(tt);
													system.getRides().add(f);
													numRides.setText(system.getRides().size() + "");
													break;
												}
											}
										} catch (Exception e) {
											a.showAndWait();
										}
									}
								}
								break;
							}
						}
					} catch (Exception e) {
						a.showAndWait();
					}
				}
			}

		});
		removeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog input = new TextInputDialog();
				input.setTitle("Remove object");
				input.setHeaderText("Choose what object you want to remove:" + "\n" + "1. Employee" + "\n" + "2. Guest"
						+ "\n" + "3. Ride");
				input.setContentText("(1-3)");
				Optional<String> result = input.showAndWait();

				int choice = 0;
				boolean isFound = false;
				Alert a = new Alert(null, "Invalid Input!", ButtonType.CLOSE);
				Alert a1 = new Alert(null, "No object found", ButtonType.CLOSE);
				Alert a2 = new Alert(null, "Object removed", ButtonType.CLOSE);
				if (result.isPresent()) {
					try {
						String temp = result.get();
						choice = Integer.parseInt(temp);
						if (choice >= 4 || choice <= 0) {
							a.showAndWait();
						} else {
							switch (choice) {
							case 1:
								input.setTitle("Remove Employee");
								input.setHeaderText("Enter in the name of the Employee you want to remove");
								input.setContentText("Name: ");
								Optional<String> Rresult = input.showAndWait();
								for (int i = 0; i < system.getEmployees().size(); i++) {
									if (system.getEmployees().get(i).getName().equals(Rresult.get())) {
										system.getEmployees().remove(i);
										numEmployees.setText(system.getEmployees().size() + "");
										isFound = true;
										a2.showAndWait();
										break;
									} else {
										isFound = false;
									}
								}
								if (!isFound) {
									a1.showAndWait();
								}
								break;
							case 2:
								input.setTitle("Remove Guest");
								input.setHeaderText("Enter in the name of the Guest you want to remove");
								input.setContentText("Name: ");
								Optional<String> Rresult1 = input.showAndWait();
								for (int i = 0; i < system.getGuests().size(); i++) {
									if (system.getGuests().get(i).getName().equals(Rresult1.get())) {
										system.getGuests().remove(i);
										numGuests.setText(system.getGuests().size() + "");
										isFound = true;
										a2.showAndWait();
										break;
									} else {
										isFound = false;
									}
								}
								if (!isFound) {
									a1.showAndWait();
								}
								break;
							case 3:
								input.setTitle("Remove Ride");
								input.setHeaderText("Enter in the name of the Ride you want to remove");
								input.setContentText("Name: ");
								Optional<String> Rresult11 = input.showAndWait();
								for (int i = 0; i < system.getRides().size(); i++) {
									if (system.getRides().get(i).getName().equals(Rresult11.get())) {
										system.getRides().remove(i);
										numRides.setText(system.getRides().size() + "");
										isFound = true;
										a2.showAndWait();
										break;
									} else {
										isFound = false;
									}
								}
								if (!isFound) {
									a1.showAndWait();
								}
								break;
							}
						}
					} catch (Exception e) {
						a.showAndWait();
					}
				}
			}

		});
		lineDisplay.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (system.getLocationOfinterest().equals("employees")) {
					for (int i = 0; i < system.getEmployees().size(); i++) {
						if (system.getEmployees().get(i).getName().equals(newValue)) {
							middleDisplay.setText("Name: " + system.getEmployees().get(i).getName() + "\n" + "ID: "
									+ system.getEmployees().get(i).getEmployeeID() + "\n" + "Job: "
									+ system.getEmployees().get(i).getJob());
						}
					}
				} else if (system.getLocationOfinterest().equals("rides")) {
					for (int i = 0; i < system.getRides().size(); i++) {
						if (system.getRides().get(i).getName().equals(newValue)) {
							if (system.getRides().get(i).getClass().getSimpleName().equals("ride")) {
								middleDisplay.setText("Name: " + system.getRides().get(i).getName() + "\n" + "Type: "
										+ system.getRides().get(i).getClass().getSimpleName() + "\n" + "Description: "
										+ system.getRides().get(i).getDescription() + "\n" + "Theme: "
										+ system.getRides().get(i).getTheme() + "\n" + "Manufacturer: "
										+ system.getRides().get(i).getManufacturer() + "\n" + "Height Requirement: "
										+ system.getRides().get(i).getHeightRequirement() + "in \n" + "Price to Build: "
										+ system.getRides().get(i).getPriceToBuild() + "\n" + "Cost to Maintain: "
										+ system.getRides().get(i).getCostToMaintain());
							}
							if (system.getRides().get(i).getClass().getSimpleName().equals("flatRide")) {
								middleDisplay.setText("Name: " + system.getRides().get(i).getName() + "\n" + "Type: "
										+ system.getRides().get(i).getClass().getSimpleName() + "\n" + "Description: "
										+ system.getRides().get(i).getDescription() + "\n" + "Theme: "
										+ system.getRides().get(i).getTheme() + "\n" + "Manufacturer: "
										+ system.getRides().get(i).getManufacturer() + "\n" + "Height Requirement: "
										+ system.getRides().get(i).getHeightRequirement() + "in \n" + "Price to Build: "
										+ system.getRides().get(i).getPriceToBuild() + "\n" + "Cost to Maintain: "
										+ system.getRides().get(i).getCostToMaintain() + "\n" + "Movment Type: "
										+ ((flatRide) system.getRides().get(i)).getMovementType() + "\n"
										+ "Intensity Category: "
										+ ((flatRide) system.getRides().get(i)).getIntensityCat());
							}
							if (system.getRides().get(i).getClass().getSimpleName().equals("waterRide")) {
								middleDisplay.setText("Name: " + system.getRides().get(i).getName() + "\n" + "Type: "
										+ system.getRides().get(i).getClass().getSimpleName() + "\n" + "Description: "
										+ system.getRides().get(i).getDescription() + "\n" + "Theme: "
										+ system.getRides().get(i).getTheme() + "\n" + "Manufacturer: "
										+ system.getRides().get(i).getManufacturer() + "\n" + "Height Requirement: "
										+ system.getRides().get(i).getHeightRequirement() + "in \n" + "Price to Build: "
										+ system.getRides().get(i).getPriceToBuild() + "\n" + "Cost to Maintain: "
										+ system.getRides().get(i).getCostToMaintain() + "\n" + "Boat type: "
										+ ((waterRide) system.getRides().get(i)).getBoatType() + "\n" + "On a Track: "
										+ ((waterRide) system.getRides().get(i)).isTracked());
							}
							if (system.getRides().get(i).getClass().getSimpleName().equals("rollerCoaster")) {
								middleDisplay.setText("Name: " + system.getRides().get(i).getName() + "\n" + "Type: "
										+ system.getRides().get(i).getClass().getSimpleName() + "\n" + "Description: "
										+ system.getRides().get(i).getDescription() + "\n" + "Theme: "
										+ system.getRides().get(i).getTheme() + "\n" + "Manufacturer: "
										+ system.getRides().get(i).getManufacturer() + "\n" + "Height Requirement: "
										+ system.getRides().get(i).getHeightRequirement() + "in \n" + "Price to Build: "
										+ system.getRides().get(i).getPriceToBuild() + "\n" + "Cost to Maintain: "
										+ system.getRides().get(i).getCostToMaintain() + "\n" + "Track Type: "
										+ ((rollerCoaster) system.getRides().get(i)).getTrackType() + "\n"
										+ "Intensity Category: "
										+ ((rollerCoaster) system.getRides().get(i)).getIntensityCat() + "\n"
										+ "Energy Type: "
										+ ((rollerCoaster) system.getRides().get(i)).getPotentialEnergyType() + "\n");
							}
						}
					}
				} else if (system.getLocationOfinterest().equals("guests")) {
					for (int i = 0; i < system.getGuests().size(); i++) {
						if (system.getGuests().get(i).getName().equals(newValue)) {
							middleDisplay.setText("Name: " + system.getGuests().get(i).getName() + "\n"
									+ "Guest Number: " + system.getGuests().get(i).getId() + "\n" + "$"
									+ system.getGuests().get(i).getMoney() + "\n" + "Happiness Level: "
									+ system.getGuests().get(i).getIsHappy() + "\n" + "Suvenires: "
									+ system.getGuests().get(i).getSuvenirs());
						}
					}
				}
			}
		});
		allInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
			Alert a = new Alert(null, "", ButtonType.CANCEL);
			Alert b = new Alert(null, "File already exists.", ButtonType.CANCEL);
			Alert c = new Alert(null, "File Saved", ButtonType.CANCEL);
			Alert d = new Alert(null, "An error occurred.");

			@Override
			public void handle(ActionEvent event) {
				try {
					String fileName = system.getPark().get(0).getName() + ".txt";
					File myObj = new File(fileName);
					if (myObj.createNewFile()) {
						a.setContentText("File created: " + myObj.getName());
						a.showAndWait();

						try {
							FileWriter myWriter = new FileWriter(fileName);
							// Park Info
							myWriter.write(system.getPark().get(0).getName() + "\n");
							myWriter.write(system.getPark().get(0).getLocation() + "\n");
							myWriter.write(system.getPark().get(0).getDatesOfOperation() + "\n");
							myWriter.write(system.getPark().get(0).getparkRaiting() + "\n");

							myWriter.write("`````````````````````````````````````");
							myWriter.write("`````````````````````````````````````");
							myWriter.write("`````````````````````````````````````" + "\n");

							// Employees
							for (int i = 0; i < system.getEmployees().size(); i++) {
								myWriter.write(system.getEmployees().get(i).getName() + ": ");
								myWriter.write(system.getEmployees().get(i).getEmployeeID() + ": ");
								myWriter.write(system.getEmployees().get(i).getJob() + "\n");
							}

							myWriter.write("`````````````````````````````````````");
							myWriter.write("`````````````````````````````````````");
							myWriter.write("`````````````````````````````````````" + "\n");

							// Rides
							for (int i = 0; i < system.getRides().size(); i++) {
								myWriter.write(system.getRides().get(i).getName() + "\n");
								myWriter.write(system.getRides().get(i).getDescription() + "\n");
								myWriter.write(system.getRides().get(i).getManufacturer() + "\n");
								myWriter.write(system.getRides().get(i).getTheme() + "\n");
								myWriter.write(system.getRides().get(i).getPriceToBuild() + "" + "\n");
								myWriter.write(system.getRides().get(i).getCostToMaintain() + "" + "\n");
								myWriter.write(system.getRides().get(i).getHeightRequirement() + "" + "\n");
								if (system.getRides().get(i).getClass().getSimpleName().equals("flatRide")) {
									myWriter.write(((flatRide) system.getRides().get(i)).getMovementType() + "\n");
									myWriter.write(((flatRide) system.getRides().get(i)).getIntensityCat() + "\n");
								}
								if (system.getRides().get(i).getClass().getSimpleName().equals("waterRide")) {
									myWriter.write(((waterRide) system.getRides().get(i)).getBoatType() + "\n");
									myWriter.write(((waterRide) system.getRides().get(i)).isTracked() + "" + "\n");
								}
								if (system.getRides().get(i).getClass().getSimpleName().equals("rollerCoaster")) {
									myWriter.write(((rollerCoaster) system.getRides().get(i)).getIntensityCat() + "\n");
									myWriter.write(
											((rollerCoaster) system.getRides().get(i)).getPotentialEnergyType() + "\n");
								}
							}

							myWriter.write("`````````````````````````````````````");
							myWriter.write("`````````````````````````````````````");
							myWriter.write("`````````````````````````````````````" + "\n");

							// Guests
							for (int i = 0; i < system.getGuests().size(); i++) {
								myWriter.write(system.getGuests().get(i).getName() + ": ");
								myWriter.write(system.getGuests().get(i).getId() + ": ");
								myWriter.write(system.getGuests().get(i).getMoney() + ": ");
								myWriter.write(system.getGuests().get(i).getIsHappy() + ": ");
								myWriter.write(system.getGuests().get(i).getSuvenirs() + "\n");
							}

							myWriter.close();
							c.showAndWait();
						} catch (IOException e) {
							System.out.println("test");
							d.showAndWait();
						}

					} else {
						b.showAndWait();
					}
				} catch (IOException e) {
					d.showAndWait();
				}
			}
		});
		updatePark.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog input = new TextInputDialog();
				input.setTitle("Update Park");
				input.setHeaderText("Choose what value you want to update:" + "\n" + "1. Name" + "\n" + "2. Address"
						+ "\n" + "3. Dates of Operation" + "\n" + "4. Park Raiting");
				input.setContentText("(1-4)");
				Optional<String> result = input.showAndWait();

				Alert a = new Alert(null, "Invalid Input!", ButtonType.CLOSE);
				if (result.isPresent()) {
					try {
						int choice = Integer.parseInt(result.get());
						if (choice > 4 || choice < 1) {
							a.showAndWait();
						} else {
							switch (choice) {
							case 1:
								input.setHeaderText("Current name: " + system.getPark().get(0).getName());
								input.setContentText("Name: ");
								Optional<String> result1 = input.showAndWait();
								system.getPark().get(0).setName(result1.get());
								parkName.setText(system.getPark().get(0).getName());
								break;
							case 2:
								input.setHeaderText("Current Address: " + system.getPark().get(0).getLocation());
								input.setContentText("Address: ");
								Optional<String> result2 = input.showAndWait();
								system.getPark().get(0).setLocation(result2.get());
								parkLocation.setText(system.getPark().get(0).getLocation());
								break;
							case 3:
								input.setHeaderText(
										"Current Schedule: " + system.getPark().get(0).getDatesOfOperation());
								input.setContentText("Schedule: ");
								Optional<String> result3 = input.showAndWait();
								system.getPark().get(0).setDatesOfOperation(result3.get());
								datesOfOperation.setText(system.getPark().get(0).getDatesOfOperation());
								break;
							case 4:
								input.setHeaderText("Current Raiting: " + system.getPark().get(0).getparkRaiting());
								input.setContentText("Value: ");
								Optional<String> result4 = input.showAndWait();
								try {
									int temp = Integer.parseInt(result4.get());
									if (temp < 0 || temp > 999) {
										a.showAndWait();
									} else {
										system.getPark().get(0).setparkRaiting(temp);
										parkScore.setText(system.getPark().get(0).getparkRaiting() + "");
									}
								} catch (Exception e) {
									a.showAndWait();
								}
							}

						}
					} catch (Exception e) {
						a.showAndWait();
					}
				}
			}
		});
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				saveFile(system);
			}
		});
		loadBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadFile(system);
				refreshScreen(system);
			}
		});
		newFileBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Alert b = new Alert(AlertType.WARNING, "Save Current File If You Want to Keep Data.", ButtonType.NO,
						ButtonType.APPLY, ButtonType.CANCEL);
				Optional<ButtonType> andy = b.showAndWait();

				if (andy.isPresent()) {
					if (andy.get() == ButtonType.APPLY) {
						saveFile(system);
					} else if (andy.get() == ButtonType.CANCEL) {

					} else {
						Alert a = new Alert(null, "Invalid Input!", ButtonType.CANCEL);
						String name = "";
						String address = "";
						String dates = "";
						int pr = 0;
						try {
							TextInputDialog input = new TextInputDialog();
							input.setTitle("Setup Park");
							input.setHeaderText("Enter in the name of your park: ");
							input.setContentText("name: ");
							Optional<String> result = input.showAndWait();
							if (result.isPresent()) {
								name = result.get();
							}

							input.setHeaderText("Enter in the address of your park: ");
							input.setContentText("address: ");
							result = input.showAndWait();
							if (result.isPresent()) {
								address = result.get();
							}

							input.setHeaderText("Enter in the start date to end state of operation: ");
							input.setContentText("12/12/2000 - 12/12/2001: ");
							result = input.showAndWait();
							if (result.isPresent()) {
								dates = result.get();
							}

							boolean loop = false;
							while (loop == false) {
								input.setHeaderText("Enter in the park raiting: ");
								input.setContentText("001-999: ");
								result = input.showAndWait();
								if (result.isPresent()) {
									try {
										int num = Integer.parseInt(result.get());
										if (num < 1 || num > 999) {
											a.showAndWait();
										} else {
											pr = num;
											loop = true;
										}
									} catch (Exception e) {
										a.showAndWait();
									}
								}
							}

							system.newFileSetUp();

							park dog = system.createPark();
							dog.setName(name);
							dog.setLocation(address);
							dog.setDatesOfOperation(dates);
							dog.setparkRaiting(pr);
							system.getPark().add(dog);

							refreshScreen(system);
							changeButtonVis(false);

						} catch (Exception e) {
							a.showAndWait();
						}
					}
				}
			}
		});

	}

	public void loadFile(ParkProcessingSystem system) {
		TextInputDialog input = new TextInputDialog();
		input.setTitle("load file");
		input.setHeaderText("Please enter in the file path of your park file: ");
		input.setContentText("path: ");
		Optional<String> result = input.showAndWait();

		Alert a = new Alert(null, "Something Went Wrong!" + "\n" + "Please check file path", ButtonType.CANCEL);
		try (Scanner fileFiner = new Scanner(result.get())) {
			// File Path
			Path parkFile = Paths.get(result.get());
			File file = new File(result.get());
			system.setFile(file);
			// Getting data
			system.load(parkFile);
			changeButtonVis(false);

		} catch (Exception e) {
			a.showAndWait();
		}
	}

	public void refreshScreen(ParkProcessingSystem system) {
		numEmployees.setText(system.getEmployees().size() + "");
		numGuests.setText(system.getGuests().size() + "");
		numRides.setText(system.getRides().size() + "");
		parkName.setText(system.getPark().get(0).getName());
		parkLocation.setText(system.getPark().get(0).getLocation());
		datesOfOperation.setText(system.getPark().get(0).getDatesOfOperation());
		parkScore.setText(system.getPark().get(0).getparkRaiting() + "");
	}

	public void changeButtonVis(Boolean state) {
		employeesBtn.setDisable(state);
		guestsBtn.setDisable(state);
		ridesBtn.setDisable(state);
		newObjBtn.setDisable(state);
		removeBtn.setDisable(state);
		allInfoBtn.setDisable(state);
		updatePark.setDisable(state);
		saveBtn.setDisable(state);
	}

	public void saveFile(ParkProcessingSystem system) {
		Alert a2 = new Alert(null, "", ButtonType.CANCEL);
		Alert b = new Alert(null, "File already exists.", ButtonType.CANCEL);
		Alert c = new Alert(null, "File Saved", ButtonType.CANCEL);
		Alert d = new Alert(null, "An error occurred.", ButtonType.CANCEL);

		String fileName = system.getPark().get(0).getName() + ".txt";
		try {
			TextInputDialog input = new TextInputDialog();
			input.setTitle("Save File");
			input.setHeaderText("Current Name: " + system.getFile() + "\n" + "Do not add file extension!");
			input.setContentText("Name:");
			Optional<String> result = input.showAndWait();

			Alert a = new Alert(null, "Invalid Input!", ButtonType.CLOSE);
			if (result.isPresent()) {
				fileName = result.get() + ".txt";
				try {
					File myObj = new File(fileName);
					if (myObj.createNewFile()) {
						a.setContentText("File created: " + myObj.getName());
						a.showAndWait();
						try {
							FileWriter myWriter = new FileWriter(fileName);
							// Park Info
							myWriter.write(system.getPark().get(0).getName() + "\n");
							myWriter.write(system.getPark().get(0).getLocation() + "\n");
							myWriter.write(system.getPark().get(0).getDatesOfOperation() + "\n");
							myWriter.write(system.getPark().get(0).getparkRaiting() + "\n");
							myWriter.write("EMPLOYEES" + "\n");
							// Employees
							for (int i = 0; i < system.getEmployees().size(); i++) {
								myWriter.write(system.getEmployees().get(i).getName() + " ");
								myWriter.write(system.getEmployees().get(i).getJob() + "\n");
							}
							myWriter.write("RIDES" + "\n");
							// Rides
							for (int i = 0; i < system.getRides().size(); i++) {
								myWriter.write(system.getRides().get(i).getCostToMaintain() + " ");
								myWriter.write(system.getRides().get(i).getTheme() + " ");
								myWriter.write(system.getRides().get(i).getPriceToBuild() + " ");
								myWriter.write(system.getRides().get(i).getName() + "\n");
								myWriter.write(system.getRides().get(i).getDescription() + "\n");
								myWriter.write(system.getRides().get(i).getManufacturer() + " ");
								myWriter.write(system.getRides().get(i).getHeightRequirement() + " ");
								myWriter.write(system.getRides().get(i).getClass().getSimpleName() + "\n");
								if (system.getRides().get(i).getClass().getSimpleName().equals("flatRide")) {
									myWriter.write(((flatRide) system.getRides().get(i)).getMovementType() + "\n");
									myWriter.write(((flatRide) system.getRides().get(i)).getIntensityCat() + "\n");
								}
								if (system.getRides().get(i).getClass().getSimpleName().equals("waterRide")) {
									myWriter.write(((waterRide) system.getRides().get(i)).getBoatType() + "\n");
									myWriter.write(((waterRide) system.getRides().get(i)).isTracked() + "" + "\n");
								}
								if (system.getRides().get(i).getClass().getSimpleName().equals("rollerCoaster")) {
									myWriter.write(((rollerCoaster) system.getRides().get(i)).getTrackType() + "\n");
									myWriter.write(((rollerCoaster) system.getRides().get(i)).getIntensityCat() + " ");
									myWriter.write(
											((rollerCoaster) system.getRides().get(i)).getPotentialEnergyType() + "\n");
								}
							}
							myWriter.write("GUESTS" + "\n");
							// Guests
							for (int i = 0; i < system.getGuests().size(); i++) {
								myWriter.write(system.getGuests().get(i).getName() + " ");
								myWriter.write(system.getGuests().get(i).getMoney() + " ");
								myWriter.write(system.getGuests().get(i).getIsHappy() + " ");
								myWriter.write(system.getGuests().get(i).getSuvenirs().size() + "\n");
								for (int x = 0; x < system.getGuests().get(i).getSuvenirs().size(); x++) {
									myWriter.write(system.getGuests().get(i).getSuvenirs().get(x) + "\n");
								}
							}
							myWriter.close();
							c.showAndWait();
						} catch (IOException e) {
							System.out.println("test");
							d.showAndWait();
						}

					} else {
						b.showAndWait();
					}
				} catch (IOException e) {
					d.showAndWait();
				}
			}
		} catch (Exception e) {
			a2.showAndWait();
		}
	}

}
