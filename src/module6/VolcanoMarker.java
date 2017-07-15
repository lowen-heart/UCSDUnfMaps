package module6;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Implements a visual marker for volcanos on an earthquake map
 * 
 * @author Loris Previtali
 * 
 */
public class VolcanoMarker extends CommonMarker {

	private int X_SPACE = 5;
	private int Y_SPACE = 10;

	public VolcanoMarker(Location location) {
		super(location);
	}

	public VolcanoMarker(Feature volcano) {
		super(((PointFeature) volcano).getLocation(), volcano.getProperties());
		// Volcanos have properties: "name" (volcano name), "elevation" (volcano
		// elevation),
		// "type" (type of volcano), "fipscode" (FIPS country code), "country"
		// (country name)
	}

	// pg is the graphics object on which you call the graphics
	// methods. e.g. pg.fill(255, 0, 0) will set the color to red
	// x and y are the center of the object to draw.
	// They will be used to calculate the coordinates to pass
	// into any shape drawing methods.
	// e.g. pg.rect(x, y, 10, 10) will draw a 10x10 square
	// whose upper left corner is at position x, y
	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void drawMarker(PGraphics pg, float x, float y) {
		// System.out.println("Drawing a volcano");
		// Save previous drawing style
		pg.pushStyle();

		// IMPLEMENT: drawing a diamond for each volcano with different color
		// for each type
		switch (getType()) {

		case "Active":
			pg.fill(124, 252, 0);
			break;
		case "Solfatara stage":
			pg.fill(32,178,170);
			break;
		case "Potentially active":
			pg.fill(255,215,0);
			break;
		default: pg.fill(255, 255, 255);
		}

		pg.quad(x - X_SPACE, y, x, y + Y_SPACE, x + X_SPACE, y, x, y - Y_SPACE);

		// Restore previous drawing style
		pg.popStyle();
	}

	/** Show the title of the volcano if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y) {
		String name = getVolcanoName() + " - " + getCountry() + ", " + getFipsCode();
		String elev = "Elevation: " + getElevation() + " Type: " + getType();

		pg.pushStyle();

		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y - Y_SPACE - 39, Math.max(pg.textWidth(name), pg.textWidth(elev)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x + 3, y - Y_SPACE - 33);
		pg.text(elev, x + 3, y - Y_SPACE - 18);

		pg.popStyle();
	}

	private String getVolcanoName() {
		return getStringProperty("name");
	}

	private String getCountry() {
		Country country = (Country) getProperty("country");
		return country.getName();
	}

	private String getFipsCode() {
		Country country = (Country) getProperty("country");
		return country.getFips();
	}

	private String getType() {
		return getStringProperty("type");
	}

	private String getElevation() {
		return getStringProperty("elevation");
	}
}
