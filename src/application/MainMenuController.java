package application;

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
	@FXML private TableColumn<Museum, String> nameColumn, addressColumn, descriptionColumn;
	@FXML private TableColumn<Museum, Double> openingTimeColumn;
	@FXML private TableColumn<Museum,Integer> costOfEntryColumn;

	@FXML TextField nameField,openingTimeField,costOfEntryField;
	@FXML TextArea addressTextArea, descriptionTextArea;

	@FXML Button sortAlphabeticallyButton, sortByCostButton, sortByTimeButton;

	private ObservableList<Museum> museumData = null;

	@FXML public void addMuseum() {
		String name = nameField.getText();
		String address = addressTextArea.getText();
		String description = descriptionTextArea.getText();
		double openingTime = Double.parseDouble(openingTimeField.getText());
		int cost = Integer.parseInt(costOfEntryField.getText());

		Museum newMuseum = new Museum(name,address,description,openingTime,cost);
		museumTable.getItems().add(newMuseum);
	}

	@FXML public void deleteMuseum() {
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
		//
		nameColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("address"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Museum, String>("description"));
		openingTimeColumn.setCellValueFactory(new PropertyValueFactory<Museum, Double>("openingTime"));
		costOfEntryColumn.setCellValueFactory(new PropertyValueFactory<Museum, Integer>("costOfEntry"));
		museumTable.setItems(getMuseums());
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
}
