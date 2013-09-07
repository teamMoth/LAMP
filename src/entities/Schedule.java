package entities;

import data.Time;


public class Schedule {

	private TimeEvent[][] timeArray = new TimeEvent[DAYS_A_WEEK][INTERVALS_A_DAY];
	private static final int DAYS_A_WEEK = 7;
	private static final int HOURS_A_DAY = 24;
	private static final int INTERVALS_A_DAY = (HOURS_A_DAY * 60) / (Time.getInterval()); 
	
	public Schedule() {
	}
	
	public boolean addEvent(String eventName, int startTime, int endTime) {
		TimeEvent event = new TimeEvent (eventName, startTime, endTime);
		
		
		return true;
	}

	
	public TimeEvent getEventAtTime(Time t){
		return null;
	}
}

