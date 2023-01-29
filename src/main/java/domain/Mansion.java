package domain;

import java.math.BigDecimal;

public class Mansion extends Asset {
	private String address;

	public Mansion(String name, String description, String address, BigDecimal value, BigDecimal rentPerWeek) {
		super(name, description, value, rentPerWeek);
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Mansion [address=" + address + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getId()=" + getId() + "]";
	}
}
