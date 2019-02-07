package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private StringProperty name;
	private StringProperty lastname;
	private ObjectProperty<Integer> age;
	private StringProperty phone;
	private StringProperty city;
	private StringProperty email;
	
	public Person (String name, String lastname, int age, String city, String phone, String email) {
		this.name = new SimpleStringProperty(name);
		this.lastname = new SimpleStringProperty(lastname);
		this.age = new SimpleIntegerProperty(age).asObject();
		this.city = new SimpleStringProperty(city);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
	}
	
	public Person (String name, String lastname) {
		this.name = new SimpleStringProperty(name);
		this.lastname = new SimpleStringProperty(lastname);
		this.age = new SimpleIntegerProperty(22).asObject();
		this.city = new SimpleStringProperty("Bucaramanga");
		this.phone = new SimpleStringProperty("3185022214");
		this.email = new SimpleStringProperty("test@gmail.com");
	}
	
	public String getName () {
		return name.get();
	}
	
	public StringProperty getNameProperty () {
		return name;
	}
	
	public void setName (String name) {
		this.name.set(name);
	}
	
	public String getLastname () {
		return lastname.get();
	}
	
	public StringProperty getLastnameProperty () {
		return lastname;
	}
	
	public void setLastname (String lastname) {
		this.lastname.set(lastname);
	}
	
	public int getAge() {
		return age.get();
	}
	
	public ObjectProperty<Integer> getAgeProperty () {
		return age;
	}
	
	public void setAge (int age) {
		this.age.set(age);
	}
	
	public String getCity () {
		return city.get();
	}
	
	public StringProperty getCityProperty () {
		return city;
	}
	
	public void setCity (String city) {
		this.city.set(city);
	}
	
	public String getPhone () {
		return phone.get();
	}
	
	public StringProperty getPhoneProperty () {
		return phone;
	}
	
	public void setPhone (String phone) {
		this.phone.set(phone);
	}
	
	public String getEmail () {
		return email.get();
	}
	
	public StringProperty getEmailProperty () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email.set(email);
	}
}
