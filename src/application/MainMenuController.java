package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;

public class MainMenuController{


	@FXML MenuItem quitMenuItem;
	@FXML Button addButton, deleteButton, sortAlphabeticallyButton, sortByCostButton, sortByTimeButton;

	//These instance variables are used to create new Museum Objects
	@FXML TextField nameField,openingTimeField,costOfEntryField;
	@FXML TextArea addressTextArea, descriptionTextArea;


	//configure the table
	@FXML TableView<Museum> museumTable;
	@FXML TableColumn<Museum, String> nameColumn, addressColumn, descriptionColumn;
	@FXML TableColumn<Museum, Double> openingTimeColumn;
	@FXML TableColumn<Museum,Double> costOfEntryColumn;

	ObservableList<Museum> museums= FXCollections.observableArrayList();

	/**
	 * This method will create a new Museum object and add it to the table
	 * @param e
	 */
	@FXML 
	public void addMuseum(ActionEvent e) {
		String name = nameField.getText();
		String address = addressTextArea.getText();
		String description = descriptionTextArea.getText();
		double openingTime = Double.parseDouble(openingTimeField.getText());
		double cost = Double.parseDouble(costOfEntryField.getText());

		//Creating a new Museum item on the custom Linked List.
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

		//Get all items from the table as a list, then add the new Museum
		museumTable.getItems().add(newMuseum);
	}


	/**
	 * This method will delete a Museum object from the table
	 * that has been clicked/selected by the user. This removes 
	 * that selected Museum from the observable list of Museums.
	 * 
	 * @param e
	 */
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

		//set up columns in the table
		nameColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("address"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("description"));
		openingTimeColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("openingTime"));
		costOfEntryColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("cost"));

		List<Museum> loadedMuseums = load();
		museumTable.setItems(FXCollections.observableList(loadedMuseums));
		//loading dummy data
		// museumTable.setItems(getDummyData());


		//Update Table to allow for editing
		museumTable.setEditable(true);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		openingTimeColumn.setCellFactory(TextFieldTableCell.<Museum, Double>forTableColumn(new DoubleStringConverter()));
		costOfEntryColumn.setCellFactory(TextFieldTableCell.<Museum, Double>forTableColumn(new DoubleStringConverter()));

	}


	/**
	 * This method will return an ObservableList of the Museum Object
	 * with some dummy objects that will be printed to the table upon 
	 * calling of this method.
	 */
	@FXML
	private ObservableList<Museum> getDummyData() {

		museums.add(new Museum("National History Museum", "Dublin", "Big Museum", 8.30, 7.00));
		museums.add(new Museum("National History Museum", "New York", "Bigger Museum", 8.30, 14.00));

		return museums;
	}



	//================ UPDATING METHODS =================\\
	/**
	 * This method will allow the user to double click on a cell and update cell
	 * 
	 * @param args
	 */
	public void updateNameCell(CellEditEvent editedCell) {
		Museum museumSelected = museumTable.getSelectionModel().getSelectedItem();
		museumSelected.setName(editedCell.getNewValue().toString());
	}

	public void updateAddressCell(CellEditEvent editedCell) {
		Museum museumSelected = museumTable.getSelectionModel().getSelectedItem();
		museumSelected.setAddress(editedCell.getNewValue().toString());
	}

	public void updateDescriptionCell(CellEditEvent editedCell) {
		Museum museumSelected = museumTable.getSelectionModel().getSelectedItem();
		museumSelected.setDescription(editedCell.getNewValue().toString());
	}

	public void updateOpeningTimeCell(CellEditEvent editedCell) {
		Museum museumSelected = museumTable.getSelectionModel().getSelectedItem();
		museumSelected.setOpeningTime(Double.parseDouble(editedCell.getNewValue().toString()));
	}

	public void updateCostCell(CellEditEvent editedCell) {
		Museum museumSelected = museumTable.getSelectionModel().getSelectedItem();
		museumSelected.setCost(Double.parseDouble(editedCell.getNewValue().toString()));
	}


	//================ SAVE AND LOAD =================\\

	@SuppressWarnings("unchecked")
	public List<Museum> load() throws Exception
	{
		List<Museum> loadedMuseums = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(new File("./museum.xml"));
			XMLDecoder decoder= new XMLDecoder(fis);
			ArrayList<Museum> decodedMuseums = (ArrayList<Museum>)decoder.readObject();
			for(Museum m : decodedMuseums) {
				loadedMuseums.add(m);
			}
			decoder.close();
			fis.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return loadedMuseums;
	}


	@SuppressWarnings("unchecked")
	public void save(List<Museum> museums) throws Exception
	{
		try {
			FileOutputStream fos = new FileOutputStream("./museum.xml");
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
			encoder.writeObject(museums);
			encoder.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * When this button is pressed the program ends and the table
	 * data is saved .
	 * 
	 * @param e
	 * @throws Exception 
	 */
	@FXML public void exit(ActionEvent e) throws Exception {
		save(new ArrayList<Museum>(museumTable.getItems()));
		Platform.exit();
	}
}
