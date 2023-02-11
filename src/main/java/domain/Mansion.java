package domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class Mansion extends Asset {
	private StringProperty address;



	public Mansion(String name, String description, String address, BigDecimal value, BigDecimal rentPerWeek) {
		super(name, description, value, rentPerWeek);
		System.out.println(address);
		this.address = new SimpleStringProperty(address);
	}

	public Mansion(String name, String description, String address, BigDecimal value) {
		super(name, description, value);
		System.out.println(address);
		this.address = new SimpleStringProperty(address);
	}

	
	public String getAddress() {
		return address.get();
	}
	
	public void setAddress(String address) {
		this.address.set(address);
	}
	
	@Override
	public String toString() {
		return "Mansion: " + getName() + ",  " + getAddress() + ", described as, " + getDescription();
	}

}
