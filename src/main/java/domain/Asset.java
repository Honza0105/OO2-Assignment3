package domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public abstract class Asset implements Comparable<Asset> {
	private final BigInteger id;
	private static BigInteger currentId = BigInteger.valueOf(2);
	private String name;
	private String description;
	private BigDecimal value;
	private BigDecimal rentPerWeek;
	
	public Asset(String name, String description, BigDecimal value, BigDecimal rentPerWeek) {
		super();
		this.id = currentId;
		currentId = currentId.nextProbablePrime();
		this.name = name;
		this.description = description;
		this.value = value;
		this.rentPerWeek = rentPerWeek;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
