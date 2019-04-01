package application;

public class Museum {

	public Museum next = null;

	private String name;
	private String address;
	private String description;
	private double openingTime;
	private double cost;

	public Museum() {}
	
	//This is a constructor for the Museum class
	public Museum(String name, String address, String description, double openingTime, double cost) {	
		this.name = name;
		this.address = address;
		this.description = description;
		this.openingTime = openingTime;
		this.cost = cost;
	}

	//==================GETTERS==================\\
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
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
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOpeningTime(double openingTime) {
		this.openingTime = openingTime;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}