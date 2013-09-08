package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import data.Time;

/**
 * Stores name of the event, start time (first occupied block of time caused by the end) 
 * and end time (first non-occupied block of time after the event)
 * 
 * @author ganc
 *
 */
public class TimeEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 175872779829265752L;
	// Name of the event
	private String name = null;
	// Collection of properties of the event
	// as of 09/08/2013 used to store information for the GUI
	private Map<String, String> properties;  
	private Time startTime;
	private Time endTime;
	

	/**
	 * constructor with event name, start time and end time
	 * @param name name of the event
	 * @param start time event starts (first occupied time block by event)
	 * @param end time event ends (first unoccupied time block afterwards)
	 */
	public TimeEvent(String name, Time start, Time end) {
		this.name = name;
		this.startTime = start;
		this.endTime = end;
		properties = new HashMap<String, String>();
	}
	
	/**
	 * Add a property to the event of a given name and value 
	 * @param key name of property being assigned (ie. color)
	 * @param value value being assigned (ie. blue)
	 */
	public void addProperty(String key, String value) {
		properties.put(key, value);
	}
	
	/**
	 * return the value of the property of a given name (ie. given type "color" return "blue)
	 * @param key property name (ie. "color") 
	 * @return value of the property in String (ie. if name is "color", returns "blue")
	 */
	public String getProperty(String key) {
		return properties.get(key);
	}
	
	/**
	 * returns name of the event
	 * @return name of the event
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the starting time of the event
	 * @return the starting time of the event
	 */
	public Time getStartTime() {
		return startTime;
	}
	
	/**
	 * returns the ending time of the event
	 * @return the ending time of the event
	 */
	public Time getEndTime() {
		return endTime;
	}
	
	@Override
	public String toString() {
		return name + "(Start: " + startTime + " End: " + endTime + ")";
	}
}
