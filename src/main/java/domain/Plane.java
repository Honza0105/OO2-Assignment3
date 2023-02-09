package domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.math.BigDecimal;

public class Plane extends Asset {
	private IntegerProperty maxAltitude;
	private IntegerProperty numberOfPassengers;
	private Island homeIsland;


	public Plane(String name, String description, BigDecimal value, BigDecimal rentPerWeek, int maxAltitude, int numberOfPassengers) {
		super(name, description, value, rentPerWeek);
		this.maxAltitude = new SimpleIntegerProperty(maxAltitude);
		this.numberOfPassengers = new SimpleIntegerProperty(numberOfPassengers);
	}

	public Plane(String name, String description, BigDecimal value, int maxAltitude, int numberOfPassengers) {
		super(name, description, value);
		this.maxAltitude = new SimpleIntegerProperty(maxAltitude);
		this.numberOfPassengers = new SimpleIntegerProperty(numberOfPassengers);
	}

	@Override
	public String toString() {
		return "Plane: " + getName() + ", described as, " + getDescription() + ", with maximum altitude "+ getMaxAltitude()+" m and " + getNumberOfPassengers()+ " passengers";
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

	public Island getHomeIsland() {
		return homeIsland;
	}

	public void setHomeIsland(Island homeIsland) {
		this.homeIsland = homeIsland;
	}
}
