package controller;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Person;

public class PersonOverviewCtrl {

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputLastname;

    @FXML
    private TextField inputAge;

    @FXML
    private TextField inputCity;

    @FXML
    private TextField inputPhone;

    @FXML
    private TextField inputEmail;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Person> tablePersons;
    
    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colLastname;

    @FXML
    private TableColumn<Person, Integer> colAge;

    @FXML
    private TableColumn<Person, String> colCity;

    @FXML
    private TableColumn<Person, String> colPhone;

    @FXML
    private TableColumn<Person, String> colEmail;
    
    private MainApp mainApp;
    private int selectedIndex;
    
    @FXML
    public void initialize() {
    	colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	colLastname.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
    	colAge.setCellValueFactory(cellData -> cellData.getValue().getAgeProperty());
    	colCity.setCellValueFactory(cellData -> cellData.getValue().getCityProperty());
    	colPhone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
    	colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
    }
    
    @FXML
    void addNewPerson() {
    	if (validateFileds()) {
    		Person p = new Person (inputName.getText(),
								inputLastname.getText(),
								Integer.parseInt(inputAge.getText()),
								inputCity.getText(),
								inputPhone.getText(),
								inputEmail.getText());
    		mainApp.addPerson(p);
    	}
    }
    
    @FXML
    void selectPerson() {
    	selectedIndex = tablePersons.getSelectionModel().getSelectedIndex();
    	Person selectedPerson = mainApp.getPerson(selectedIndex);
    	inputName.setText(selectedPerson.getName());
    	inputLastname.setText(selectedPerson.getLastname());
    	inputAge.setText(String.valueOf(selectedPerson.getAge()));
    	inputCity.setText(selectedPerson.getCity());
    	inputPhone.setText(selectedPerson.getPhone());
    	inputEmail.setText(selectedPerson.getEmail());    	
    }
    
    @FXML
    public void deletePerson () {
    	mainApp.removePerson(selectedIndex);
    	clearInputs();
    }
    
    @FXML
    public void updatePerson () {
    	if (validateFileds()) {
    		Person person = mainApp.getPerson(selectedIndex);
    		person.setName(inputName.getText());
    		person.setLastname(inputLastname.getText());
    		person.setAge(Integer.valueOf(inputAge.getText()));
    		person.setCity(inputCity.getText());
    		person.setPhone(inputPhone.getText());
    		person.setEmail(inputEmail.getText());
    	}
    }
    
    @FXML   
    public void clearInputs() {
    	inputName.setText("");
    	inputLastname.setText("");
    	inputAge.setText("");
    	inputCity.setText("");
    	inputPhone.setText("");
    	inputEmail.setText("");  
    }
    
    public boolean validateFileds () {
    	if (inputName.getText().length() == 0 ||
    		inputLastname.getText().length() == 0 || 
    		inputAge.getText().length() == 0 || 
    		inputCity.getText().length() == 0 ||
    		inputPhone.getText().length() == 0 || 
    		inputEmail.getText().length() == 0) {
    			return false;
    		} else {
    			return true;
    		}
    	
    }
    
    public void setMainApp (MainApp mainApp) {
    	this.mainApp = mainApp;
    	tablePersons.setItems(this.mainApp.getPersonList());
    }
    

}
