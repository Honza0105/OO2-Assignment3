package domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Asset implements Comparable<Asset> {
	private final BigInteger id;
	private static BigInteger currentId = BigInteger.valueOf(2);
	private StringProperty name;
	private StringProperty description;
	private BigDecimal value;
	private BigDecimal rentPerWeek;
	
	public Asset(String name, String description, BigDecimal value, BigDecimal rentPerWeek) {
		super();
		this.id = currentId;
		currentId = currentId.nextProbablePrime();
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.value = value;
		this.rentPerWeek = rentPerWeek;
	}

	public Asset(String name, String description, BigDecimal value) {
		super();
		this.id = currentId;
		currentId = currentId.nextProbablePrime();
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.value = value;
		this.rentPerWeek = value.multiply(new BigDecimal("0.01"));
	}
	public StringProperty getNameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name.set(name);
	}


	public StringProperty getDescriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public BigInteger getId() {
		return id;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getRentPerWeek() {
		return rentPerWeek;
	}
	
	public void setRentPerWeek(BigDecimal rentPerWeek) {
		this.rentPerWeek = rentPerWeek;
	}
	
	@Override
	public int compareTo(Asset other) {
		if (this.value.equals(other.value)) {
			return (other.id.min(this.id)).intValue();
		}
		else {
			return this.value.compareTo(other.value);
		}
	}
	
	@Override
	public String toString() {
		return "Asset [id=" + id + ", name=" + name + ", description=" + description + ", value=" + value
				+ ", rentPerWeek=" + rentPerWeek + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		return Objects.equals(id, other.id);
	}

	public BigDecimal getRent(LocalDate fromDate, LocalDate tillDate){
		BigDecimal dateDifference = new BigDecimal(String.valueOf(ChronoUnit.DAYS.between(fromDate, tillDate)));
		BigDecimal daysInWeek = new BigDecimal(7);
		BigDecimal weeks = dateDifference.divide(daysInWeek, RoundingMode.HALF_UP);
		return weeks.multiply(rentPerWeek);
	}

}
