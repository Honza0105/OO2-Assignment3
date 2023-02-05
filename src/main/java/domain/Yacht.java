package domain;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class Yacht extends Asset {
	private DoubleProperty length;
	private IntegerProperty numberOfDecks;
	private DoubleProperty speed;
	private IntegerProperty huts;
	
	public Yacht(String name, String description, BigDecimal value, BigDecimal rentPerWeek, double length,
				 int numberOfDecks, double speed, int huts) {
		super(name, description, value, rentPerWeek);
		this.length = new SimpleDoubleProperty(length);
		this.numberOfDecks = new SimpleIntegerProperty(numberOfDecks);
		this.speed = new SimpleDoubleProperty(speed);
		this.huts = new SimpleIntegerProperty(huts);
	}

	public double getLength() {
		return length.get();
	}

	public void setLength(double length) {
		this.length.set(length);
	}

	public int getNumberOfDecks() {
		return numberOfDecks.get();
	}

	public void setNumberOfDecks(int numberOfDecks) {
		this.numberOfDecks.set(numberOfDecks);
	}

	public double getSpeed() {
		return speed.get();
	}

	public void setSpeed(double speed) {
		this.speed.set(speed);
	}

	public int getHuts() {
		return huts.get();
	}

	public void setHuts(int huts) {
		this.huts.set(huts);
	}
}
