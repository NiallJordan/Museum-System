package application;

import javafx.beans.property.SimpleStringProperty;

public class Museum {

	public Museum next = null;

	private SimpleStringProperty name;
	private SimpleStringProperty address;
	private SimpleStringProperty description;
	private double openingTime;
	private double cost;

	//This is a constructor for the Museum class
	public Museum(String name, String address, String description, double openingTime, double cost) {	
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.description = new SimpleStringProperty(description);
		this.openingTime = openingTime;
		this.cost = cost;
	}

	//==================GETTERS==================\\
	public String getName() {
		return name.get();
	}

	public String getAddress() {
		return address.get();
	}

	public String getDescription() {
		return description.get();
	}

	public double getOpeningTime() {
		return openingTime;
	}

	public double getCost() {
		return cost;
	}


	//==================SETTERS==================\\

	/*Simple string is a wrapper class that gives functionality of String 
	 *	with added functionality in table
	 */
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}

	public void setOpeningTime(double openingTime) {
		this.openingTime = openingTime;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
