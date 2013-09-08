package entities;

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
public class TimeEvent {

	private String name = null;
	private Map<String, String> properties;  
	private Time startTime;
	private Time endTime;
	public TimeEvent() {
	}
	
	public TimeEvent(String name, Time start, Time end) {
		this.name = name;
		this.startTime = start;
		this.endTime = end;
		properties = new HashMap<String, String>();
	}
	
	public void addProperty(String key, String value) {
		properties.put(key, value);
	}
	
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
