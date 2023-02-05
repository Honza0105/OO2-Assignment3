package domain;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

public class Island extends Asset {
	private FloatProperty area;
	private Coordinate coordinate;
	private Climate climate;
	private SortedSet<Asset> assets;
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			Coordinate coordinate, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = assets;
	}
	public Island(String name, String description, BigDecimal value, float area,
				  Coordinate coordinate, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = assets;
	}
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			Coordinate coordinate, Climate climate) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = new TreeSet<>();
	}

	public Island(String name, String description, BigDecimal value, float area,
				  Coordinate coordinate, Climate climate) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = coordinate;
		this.climate = climate;
		this.assets = new TreeSet<>();
	}
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			float latitude, float longitude, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = assets;
	}

	public Island(String name, String description, BigDecimal value, float area,
				  float latitude, float longitude, Climate climate, SortedSet<Asset> assets) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = assets;
	}
	
	public Island(String name, String description, BigDecimal value, BigDecimal rentPerWeek, float area,
			float latitude, float longitude, Climate climate) {
		super(name, description, value, rentPerWeek);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = new TreeSet<>();
	}

	public Island(String name, String description, BigDecimal value, float area,
				  float latitude, float longitude, Climate climate) {
		super(name, description, value);
		this.area = new SimpleFloatProperty(area);
		this.coordinate = new Coordinate(latitude, longitude);
		this.climate = climate;
		this.assets = new TreeSet<>();
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
		return assets.remove(asset);
	}

	public enum Climate { TROPICAL, SUBTROPICAL, TEMPERATE, CONTINENTAL, ARCTIC
		
	}
	
	class Coordinate {
		private final float latitude;
		private final float longitude;
		
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
	}
}
