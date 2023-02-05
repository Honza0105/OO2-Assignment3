package domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.math.BigDecimal;

public class Plane extends Asset {
	private IntegerProperty maxAltitude;
	private IntegerProperty numberOfPassengers;

	public Plane(String name, String description, BigDecimal value, BigDecimal rentPerWeek, int maxAltitude, int numberOfPassengers) {
		super(name, description, value, rentPerWeek);
		this.maxAltitude = new SimpleIntegerProperty(maxAltitude);
		this.numberOfPassengers = new SimpleIntegerProperty(numberOfPassengers);
	}

	public int getMaxAltitude() {
		return maxAltitude.get();
	}

	public void setMaxAltitude(int maxAltitude) {
		this.maxAltitude.set(maxAltitude);
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers.get();
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers.set(numberOfPassengers);
	}
}
