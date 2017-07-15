package module6;

import de.fhpotsdam.unfolding.geo.Location;

public class Country {
	private String fips;
	private String name;
	private Location loc;
	
	public Country(String fips, String name, Location loc) {
		super();
		this.fips = fips;
		this.name = name;
		this.loc = loc;
	}

	public String getFips() {
		return fips;
	}

	public String getName() {
		return name;
	}

//	public float getLat() {
//		return loc.getLat();
//	}
//
//	public float getLon() {
//		return loc.getLon();
//	}

	public Location getLocation() {
		// TODO Auto-generated method stub
		return loc;
	}
	
}
