package application;

public class Museum {

	private String name;
	private String address;
	private String description;
	private double openingTime;
	private double cost;
	
	public Museum(String name, String address, String description, double openingTime, double cost) {		
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
