package entities;

import data.Time;

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
