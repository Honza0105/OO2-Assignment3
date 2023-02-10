package domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Heir {
	private StringProperty name;
	private StringProperty address;
	private LocalDate dateOfBirth;
	private Gender gender;
	private BigDecimal netWorth;
	private BigDecimal income;
	private final BigInteger id;
	private static BigInteger currentId = BigInteger.valueOf(2);
	private final ObservableList<Asset> assets;



	public Heir(String name, String address, LocalDate dateOfBirth, Gender gender, BigDecimal netWorth,
				BigDecimal income) {
		super();
		this.id = currentId;
		currentId = currentId.nextProbablePrime();
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.netWorth = netWorth;
		this.income = income;
		this.assets = FXCollections.observableArrayList();
	}

	public String getName() {
		return name.get();
	}

	@Override
	public String toString() {
		return getName();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getAddress() {
		return address.get();
	}

	public StringProperty addressProperty() {
		return address;
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BigDecimal getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(BigDecimal netWorth) {
		this.netWorth = netWorth;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigInteger getId() {
		return id;
	}
	
	public boolean addAsset(Asset asset) {
		return assets.add(asset);
	}
	
	public boolean removeAsset(Asset asset) {
		return assets.remove(asset);
	}
	
	public Set<Asset> getAssets() {
		Set<Asset> result = new TreeSet<>();
		result.addAll(assets);
		return result;
	}

	public enum Gender { MALE, FEMALE, OTHER
		
	}
}
