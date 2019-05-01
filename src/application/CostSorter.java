package application;

import java.util.Comparator;

public class CostSorter implements Comparator<Museum>{
	public int compare(Museum museum, Museum museum2) {
		return museum.getCost() - museum2.getCost();
	}
}
