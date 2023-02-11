package domain;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

public class Island extends Asset {
	private FloatProperty area;
	private Coordinate coordinate;
	private Climate climate;
	private ObservableSet<Asset> assets;


	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			Coordinate coordinate, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = FXCollections.observableSet(assets);
	}
	public Island(String name, String description, BigDecimal value, float area,
				  Coordinate coordinate, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = FXCollections.observableSet(assets);

	}
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			Coordinate coordinate, Climate climate) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = FXCollections.observableSet(assets);
	}

	public Island(String name, String description, BigDecimal value, float area,
				  Coordinate coordinate, Climate climate) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = FXCollections.observableSet(new TreeSet<>());
	}
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			float latitude, float longitude, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = FXCollections.observableSet(assets);
	}

	public Island(String name, String description, BigDecimal value, float area,
				  float latitude, float longitude, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = FXCollections.observableSet(assets);
	}
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			float latitude, float longitude, Climate climate) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = FXCollections.observableSet(new TreeSet<>());
	}

	public Island(String name, String description, BigDecimal value, float area,
				  float latitude, float longitude, Climate climate) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = FXCollections.observableSet(new TreeSet<>());
	}

	public float getArea() {
		return area.get();
	}

	public void setArea(float area) {
		this.area.set(area);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public float getLongitude() {
		return coordinate.longitude;
	}

	public float getLatitude() {
		return coordinate.latitude;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Climate getClimate() {
		return climate;
	}

	public void setClimate(Climate climate) {
		this.climate = climate;
	}

	public SortedSet<Asset> getAssets() {
		SortedSet<Asset> result = new TreeSet<>();
		result.addAll(assets);
		return result;
	}

	public boolean addAsset(Asset asset) {
		return assets.add(asset);
	}

	public boolean removeAsset(Asset asset) {
		System.out.println("Before removing: " + assets);
		boolean result = assets.remove(asset);
		System.out.println("After removing: " + assets);
		return result;
	}

	public void setLongitude(float newLongitude) {
		coordinate.setLongitude(newLongitude);
	}

	public void setLatitude(float newLatitude) {
		coordinate.setLatitude(newLatitude);
	}



	public enum Climate { TROPICAL, SUBTROPICAL, TEMPERATE, CONTINENTAL, ARCTIC
		
	}
	
	class Coordinate {
		private float latitude;
		private float longitude;
		
		public Coordinate(float latitude, float longitude) {
			super();
			this.latitude = latitude;
			this.longitude = longitude;
		}
		
		public float getLatitude() {
			return latitude;
		}
		
		public float getLongitude() {
			return longitude;
		}

		public void setLatitude(float latitude) {
			this.latitude = latitude;
		}

		public void setLongitude(float longitude) {
			this.longitude = longitude;
		}
	}

	@Override
	public String toString() {
		return "Island: "+ getName() +
				"being " + getArea() +
				"km2";
	}
}
