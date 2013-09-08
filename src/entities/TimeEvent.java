package entities;

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
	private Time startTime;
	private Time endTime;
	public TimeEvent() {
	}
	
	public TimeEvent(String name, Time start, Time end) {
		this.name = name;
		this.startTime = start;
		this.endTime = end;
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
}
