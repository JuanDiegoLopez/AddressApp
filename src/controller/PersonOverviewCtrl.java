package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.OracleConnection;
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
    private OracleConnection connection;
    private ObservableList<Person> personList;
    
    @FXML
    public void initialize() {
    	colName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	colLastname.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
    	colAge.setCellValueFactory(cellData -> cellData.getValue().getAgeProperty());
    	colCity.setCellValueFactory(cellData -> cellData.getValue().getCityProperty());
    	colPhone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
    	colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
    	
    	btnSave.setDisable(true);
    	btnDelete.setDisable(true);
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
    	
    	btnSave.setDisable(false);
    	btnDelete.setDisable(false);
    	btnNew.setDisable(true);
    }
    
    @FXML
    void addNewPerson() {
    	if (validateFileds()) {
    		Person person = new Person (inputName.getText(),
								inputLastname.getText(),
								Integer.parseInt(inputAge.getText()),
								inputCity.getText(),
								inputPhone.getText(),
								inputEmail.getText());
    		try {
    			this.connection.startConnection();
    			Statement s = connection.getConnection().createStatement();
    			s.executeQuery("INSERT INTO users VALUES ("+ (personList.size() + 1) +", '"+ person.getName() +"', '"+ person.getLastname() +"', '"+ person.getCity() +"', '"+ person.getPhone() +"', '"+ person.getEmail() +"', "+ person.getAge() +")");
    			personList.add(person);
    			connection.closeConnection();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		clearInputs();
    	}
    }
    
    @FXML
    public void deletePerson () {
		try {
			connection.startConnection();
			Statement s = connection.getConnection().createStatement();
			s.executeQuery("DELETE FROM users where id = "+ (selectedIndex + 1));
			personList.remove(selectedIndex);
			connection.closeConnection();
	    	clearInputs();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    public void updatePerson () {
    	if (validateFileds()) {
			try {
				connection.startConnection();
				Statement s = connection.getConnection().createStatement();
				s.executeQuery("UPDATE users SET name = '" + inputName.getText() + "', lastname = '" + inputLastname.getText() + "', age = '" + inputAge.getText() + "', city = '" + inputCity.getText() + "', phone = '" + inputPhone.getText() + "', email = '" + inputEmail.getText() + "' WHERE id = " + selectedIndex + 1);
				Person person = personList.get(selectedIndex);
				person.setName(inputName.getText());
				person.setLastname(inputLastname.getText());
				person.setAge(Integer.valueOf(inputAge.getText()));
				person.setCity(inputCity.getText());
				person.setPhone(inputPhone.getText());
				person.setEmail(inputEmail.getText());
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
    	
    	btnSave.setDisable(true);
    	btnDelete.setDisable(true);
    	btnNew.setDisable(false);
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
    	personList = mainApp.getPersonList();
    	
    	connection = new OracleConnection();
		connection.startConnection();
		
		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM USERS");
			
			while (result.next()) {
				personList.add(new Person(result.getString(2), result.getString(3), result.getInt(7), result.getString(4), result.getString(5), result.getString(6)));
			}
			
			connection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tablePersons.setItems(personList);
    }
    

}
