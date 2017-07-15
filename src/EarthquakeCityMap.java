import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet {

	private UnfoldingMap map;
	
	public void setup(){
		//create the canvas
		size(950,600,OPENGL);
		//create the map into the canvas
		map = new UnfoldingMap(this,200,50,700,500, new Google.GoogleMapProvider());
		//zoom the map in with level 2
		map.zoomToLevel(2);
		//make the map interactive
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location valLoc = new Location(-38.14f,-73.03f);
		
		
		Feature valEq = new PointFeature(valLoc);
		valEq.addProperty("title", "Valdivia, Chile");
		valEq.addProperty("magnitude", "9.5");
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", "1960");
		
		//represent a single point location (a little circle). Interface Marker (ADT promise between the programmer and the person who uses it
		//Marker val = new SimplePointMarker(valLoc);
		//map.addMarker(val);
		Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		map.addMarker(valMk);
	}
	
	public void draw(){
		//draw the background
		background(10);
		//draw the map
		map.draw();
		//helper private method for our legend
		addKey();
	}

	private void addKey() {
		// TODO Auto-generated method stub
		
	}
}
