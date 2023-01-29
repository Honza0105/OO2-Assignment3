package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Swap {
	private Asset asset1;
	private Asset asset2;
	private LocalDate start;
	private LocalDate end;
	
	public Swap(Asset asset1, Asset asset2, LocalDate start, LocalDate end) {
		super();
		this.asset1 = asset1;
		this.asset2 = asset2;
		this.start = start;
		this.end = end;
	}
	
	public Asset getAsset1() {
		return asset1;
	}
	
	public void setAsset1(Asset asset1) {
		this.asset1 = asset1;
	}
	
	public Asset getAsset2() {
		return asset2;
	}
	
	public void setAsset2(Asset asset2) {
		this.asset2 = asset2;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(asset1, asset2, start);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Swap other = (Swap) obj;
		return Objects.equals(asset1, other.asset1) && Objects.equals(asset2, other.asset2)
				&& Objects.equals(start, other.start);
	}
	
	@Override
	public String toString() {
		return "Swap [asset1=" + asset1 + ", asset2=" + asset2 + ", start=" + start + ", end=" + end + "]";
	}
}
