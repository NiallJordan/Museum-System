package application;

import java.util.Comparator;

public class NameSorter implements Comparator<Museum>{
	public int compare(Museum museum, Museum museum2) {
		return museum.getName().compareTo(museum2.getName());
	}
}
