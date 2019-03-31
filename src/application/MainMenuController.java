package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainMenuController implements Comparable<Museum> {


	@FXML MenuItem quitMenuItem, alphabeticalButton, byCostMenuItem, byOpeningTimeMenuItem;
	@FXML Button addButton, deleteButton;

	@FXML TableView<Museum> museumTable;
	@FXML TableColumn<Museum, String> nameColumn, addressColumn, descriptionColumn;
	@FXML TableColumn<Museum, Double> openingTimeColumn;
	@FXML TableColumn<Museum,Double> costOfEntryColumn;

	@FXML TextField nameField,openingTimeField,costOfEntryField;
	@FXML TextArea addressTextArea, descriptionTextArea;

	@FXML Button sortAlphabeticallyButton, sortByCostButton, sortByTimeButton;

	private ObservableList<Museum> museumData = null;



	@FXML 
	public void addMuseum(ActionEvent e) {
		String name = nameField.getText();
		String address = addressTextArea.getText();
		String description = descriptionTextArea.getText();
		double openingTime = Double.parseDouble(openingTimeField.getText());
		Double cost = Double.parseDouble(costOfEntryField.getText());

		Museum newMuseum = new Museum(name,address,description,openingTime,cost);
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

	public ObservableList<Museum> getMuseums(){
		if(museumData != null) return museumData;

		return museumData;
	}

	public void setUpMuseumTable() {

		nameColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("address"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("description"));
		openingTimeColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("openingTime"));
		costOfEntryColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("costOfEntry"));

		museumTable.setItems(getInitialTableData());
	}


	private ObservableList<Museum> getInitialTableData() {
		if(museumData != null) return museumData;

		museumData = FXCollections.observableArrayList();

		museumData.add(new Museum("National","sdadsa","sadadjanfdfnd",10.00,123.00));

		return museumData;

	}

	/**
	 * When this button is pressed the program ends.
	 * 
	 * @param e
	 */
	@FXML public void exit(ActionEvent e) {
		Platform.exit();
	}

	@Override
	public int compareTo(Museum m) {
		return 0;
	}




	//================ SAVE AND LOAD =================\\

	public static void main(String[] args) {

	}


	@SuppressWarnings("unchecked")
	public void load() throws Exception
	{
		try {
			FileInputStream fis = new FileInputStream(new File("./museum.xml"));
			XMLDecoder decoder= new XMLDecoder(fis);

			Museum loadedMuseumData = (Museum)decoder.readObject();
			decoder.close();
			fis.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void save() throws Exception
	{
		Museum mus = new Museum("dassa","sdasfd","daskjnsajnjfnjfj",10.00,14.00);
		try {

			FileOutputStream fos = new FileOutputStream(new File("./museum.xml"));
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(mus);
			encoder.close();
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
