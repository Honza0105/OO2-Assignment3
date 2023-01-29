package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rent {
	private Asset asset;
	private Heir occupant;
	private LocalDate start;
	private LocalDate end;
	private BigDecimal rent;
	private LocalDate dateOfPayment;
	
	public Rent(Asset asset, Heir occupant, LocalDate start, LocalDate end, BigDecimal rent,
			LocalDate dateOfPayment) {
		super();
		this.asset = asset;
		this.occupant = occupant;
		this.start = start;
		this.end = end;
		this.rent = rent;
		this.dateOfPayment = dateOfPayment;
	}
	
	public Asset getAsset() {
		return asset;
	}
	
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	public Heir getOccupant() {
		return occupant;
	}
	
	public void setOccupant(Heir occupant) {
		this.occupant = occupant;
	}
	
	public LocalDate getStart() {
		return start;
	}
	
	public void setStart(LocalDate start) {
		this.start = start;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	public BigDecimal getRent() {
		return rent;
	}
	
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	
	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}
	
	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
}
