package application;

public class Museum implements Comparable<Museum> {

	public Museum next = null;

	private String name;
	private String address;
	private String description;
	private int openingTime;
	private int cost;

	public Museum() {}
	
	//This is a constructor for the Museum class
	public Museum(String name, String address, String description, int openingTime, int cost) {	
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

	public int getOpeningTime() {
		return openingTime;
	}

	public int getCost() {
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

	public void setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
//	@Override
//	public int compareTo(Museum museum) {
//		return  cost - museum.getCost();
//	}
	
	@Override
	public int compareTo(Museum museum) {
		return name.compareTo(museum.getName());
	}

}