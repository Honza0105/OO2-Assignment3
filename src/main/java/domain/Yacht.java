package domain;

import java.math.BigDecimal;

public class Yacht extends Asset {
	private double length;
	private int numberOfDecks;
	private double speed;
	private int huts;
	
	public Yacht(String name, String description, BigDecimal value, BigDecimal rentPerWeek, double length,
			int numberOfDecks, double speed, int huts) {
		super(name, description, value, rentPerWeek);
		this.length = length;
		this.numberOfDecks = numberOfDecks;
		this.speed = speed;
		this.huts = huts;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getNumberOfDecks() {
		return numberOfDecks;
	}

	public void setNumberOfDecks(int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getHuts() {
		return huts;
	}

	public void setHuts(int huts) {
		this.huts = huts;
	}
}
