package domain;

import java.math.BigDecimal;

public class Plane extends Asset {
	private int maxAltitude;
	private int numberOfPassengers;

	public Plane(String name, String description, BigDecimal value, BigDecimal rentPerWeek, int maxAltitude, int numberOfPassengers) {
		super(name, description, value, rentPerWeek);
		this.maxAltitude = maxAltitude;
		this.numberOfPassengers = numberOfPassengers;
	}

	public int getMaxAltitude() {
		return maxAltitude;
	}

	public void setMaxAltitude(int maxAltitude) {
		this.maxAltitude = maxAltitude;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
}
