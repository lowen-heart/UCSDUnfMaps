package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Loris Previtali
 * Date: March 30, 2017
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.RoadProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	// PointFeatures also have a getLocation method
	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    
	    //adding more colors
	    int red = color(255,0,0);
	    int blue = color(0,0,255);
	    
	    //TODO: Add code here as appropriate
	    //with this for I create a link from all the earthquakes of the RSS feed and a marker using the helper method
	    for(PointFeature eq : earthquakes){
	    	if((float) eq.getProperty("magnitude") < THRESHOLD_LIGHT){
	    		markers.add(createMarker(eq,5f,blue));
	    	}else{
	    		if((float) eq.getProperty("magnitude") >= THRESHOLD_MODERATE){
	    			markers.add(createMarker(eq,15f,red));
	    		}else{
	    			markers.add(createMarker(eq,10f,yellow));
	    		}
	    	}
	    }
	    //add the markers to the map
	    map.addMarkers(markers);
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// finish implementing and use this method, if it helps.
		//return new SimplePointMarker(feature.getLocation());
		//add the getProperties to end this method
		return new SimplePointMarker(feature.getLocation(),feature.getProperties());
	}
	
	//overload of the method createMarker
	private SimplePointMarker createMarker(PointFeature feature,float radius,int color)
	{
		// finish implementing and use this method, if it helps.
		//return new SimplePointMarker(feature.getLocation());
		//add the getProperties to end this method
		//return new SimplePointMarker(feature.getLocation(),feature.getProperties());
		SimplePointMarker spm = new SimplePointMarker(feature.getLocation(),feature.getProperties());
		spm.setRadius(radius);
		spm.setColor(color);
		spm.setStrokeWeight(1);
		return spm;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		//width and height of the background rectangle
		float rectWidth = 160.0f;
		float rectHeight = 250.0f;
		// Remember you can use Processing's graphics methods here
		// Stroke to lime green
		stroke(205, 255, 81);
		strokeWeight(4);
		strokeJoin(ROUND);
		// Fill with cream color
		fill(255,250,240);
		// Create the background rectangle at position: x=20px,y=50px,width and height defined before and corner radius 4
		rect(20.0f,50.0f,rectWidth,rectHeight,4);
		// Create title Earthquake Key
		fill(0,0,0);
		// text size to 20px
		textSize(20);
		// Align text to the CENTER
		textAlign(CENTER);
		// Draw title of the key "Earthquake Key" at position x=100px, y=80px
		text("Earthquake Key",100.0f,80.0f);
		// Draw text, color and create circles for the 3 earthquakes types
		// Red, 5.0+ Magnitude
		createKeyEntry(50.0f,120.0f,15.0f,color(255,0,0), "5.0+ Magnitude");
		// Yellow, 4.0+ Magnitude
		createKeyEntry(50.0f,170.0f,10.0f,color(255,255,0), "4.0+ Magnitude");
		// Blue, Below 4.0 Magnitude
		createKeyEntry(50.0f,220.0f,5.0f,color(0,0,255), "Below 4.0");
		

	}
	
	private void createKeyEntry(float posX,float posY,float diameter,int rgb, String text){
		
		// stroke to black and weight to 1
		stroke(0,0,0);
		strokeWeight(1);
		// fill the circle with the rgb value passed
		fill(rgb);
		// create the circle with posX, posY for the position and the diameter of the circle
		ellipse(posX,posY,diameter,diameter);
		// fill to black for the text
		fill(0,0,0);
		// text size to 12px
		textSize(12);
		// align text to the LEFT and CENTER
		textAlign(LEFT,CENTER);
		// create the text with the String passed and move it of 20px to the right on the X axis
		this.text(text,posX+20.0f,posY);
		
		
	}
}
