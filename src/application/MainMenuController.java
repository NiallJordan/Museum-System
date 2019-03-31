package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainMenuController implements Comparable<Museum> {


	@FXML MenuItem quitMenuItem;
	@FXML Button addButton, deleteButton, sortAlphabeticallyButton, sortByCostButton, sortByTimeButton;
	@FXML TextField nameField,openingTimeField,costOfEntryField;
	@FXML TextArea addressTextArea, descriptionTextArea;


	//configure the table
	@FXML TableView<Museum> museumTable;
	@FXML TableColumn<Museum, String> nameColumn, addressColumn, descriptionColumn;
	@FXML TableColumn<Museum, Double> openingTimeColumn;
	@FXML TableColumn<Museum,Double> costOfEntryColumn;


	@FXML 
	public void addMuseum(ActionEvent e) {
		String name = nameField.getText();
		String address = addressTextArea.getText();
		String description = descriptionTextArea.getText();
		double openingTime = Double.parseDouble(openingTimeField.getText());
		double cost = Double.parseDouble(costOfEntryField.getText());
		
		Museum newMuseum = new Museum(name, address, description, openingTime, cost);
		newMuseum.setName(name);
		newMuseum.setAddress(address);
		newMuseum.setDescription(description);
		newMuseum.setOpeningTime(openingTime);
		newMuseum.setCost(cost);
		newMuseum.next = Main.headMuseum;
		Main.headMuseum = newMuseum;
		
		Museum temp = Main.headMuseum;
		while(temp != null) {
			temp = temp.next;
		}
		
		museumTable.getItems().add(newMuseum);
	}

	
	@FXML 
	public void deleteMuseum(ActionEvent e) {
		ObservableList<Museum> selectedMuseum, allMuseums;

		selectedMuseum = museumTable.getSelectionModel().getSelectedItems();
		allMuseums = museumTable.getItems();

		for(Museum museumTable: selectedMuseum) {
			allMuseums.remove(museumTable);
		}
	}



	/**
	 * Initializes the controller class.
	 * 
	 * @throws Exception
	 */
	@FXML 
	private void initialize() throws Exception {
//		load();
		
		//set up columns in the table
		nameColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("address"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("description"));
		openingTimeColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("openingTime"));
		costOfEntryColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("cost"));
		
		//loading dummy data
		museumTable.setItems(getDummyData());
	}


	/**
	 * This method will return an ObservableList of the Museum Object
	 */
	@FXML
	private ObservableList<Museum> getDummyData() {
		ObservableList<Museum> museums= FXCollections.observableArrayList();
		museums.add(new Museum("National History Museum", "Dublin", "Big Museum", 8.30, 7.00));
		museums.add(new Museum("National History Museum", "New York", "Bigger Museum", 8.30, 14.00));
		
		
		return museums;
	}
	
	
	@Override
	public int compareTo(Museum m) {
		return 0;
	}

	
	/**
	 * This method will allow the user to double click on a cell and update cell
	 * 
	 * @param args
	 */
	
//	public void updateFirstNameCell(CellEditEvent editedCell) {
//		Museum selectedMuseum = (Museum) museumTable.getSelectionModel().getSelectedItems();
//		selectedMuseum.setName(editedCell.getNewValue().toString());
//	}



	//================ SAVE AND LOAD =================\\

	public static void main(String[] args) {

	}


//	@SuppressWarnings("unchecked")
//	public void load() throws Exception
//	{
//		try {
//			FileInputStream fis = new FileInputStream(new File("./museum.xml"));
//			XMLDecoder decoder= new XMLDecoder(fis);
//
//			ObservableList<Museum> loadedMuseumData = (ObservableList<Museum>)decoder.readObject();
//			decoder.close();
//			fis.close();
//			
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	public void save() throws Exception
//	{
//		ObservableList<Museum> mus = museumData;
//		try {
//
//			FileOutputStream fos = new FileOutputStream(new File("./museum.xml"));
//			XMLEncoder encoder = new XMLEncoder(fos);
//			encoder.writeObject(mus);
//			encoder.close();
//			fos.close();
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
	
	/**
	 * When this button is pressed the program ends.
	 * 
	 * @param e
	 * @throws Exception 
	 */
	@FXML public void exit(ActionEvent e) throws Exception {
		// save();
		Platform.exit();
	}
}
