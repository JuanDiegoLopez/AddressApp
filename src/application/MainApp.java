package application;
	
import java.io.IOException;

import controller.PersonOverviewCtrl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Person;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<Person> personList = FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		initRootLayout();
		showPersonOverview();
		
		personList.add(new Person("Juan", "Lopez"));
		personList.add(new Person("Diego", "Triana"));
	}
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
			rootLayout = loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showPersonOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PersonOverview.fxml"));
			AnchorPane personOverview  = loader.load();
			
			rootLayout.setCenter(personOverview);
			
			PersonOverviewCtrl personOverviewCtrl = loader.getController();
			personOverviewCtrl.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Person> getPersonList() {
		return personList;
	}
	
	public Person getPerson(int index) {
		return personList.get(index);
	}
	
	public void addPerson(Person person) {
		personList.add(person);
	}
	
	public void removePerson (int index) {
		personList.remove(index);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
