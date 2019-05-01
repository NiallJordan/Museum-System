package application;

import java.util.Comparator;

public class OpeningTimeSorter implements Comparator<Museum>{
	public int compare(Museum museum, Museum museum2) {
		return museum.getOpeningTime() - museum2.getOpeningTime();
	}
}