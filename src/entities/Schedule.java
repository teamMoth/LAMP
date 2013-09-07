package entities;

import data.Time;


public class Schedule {
	
	private static final int DAYS_A_WEEK = Time.getDaysWeek();
	private static final int HOURS_A_DAY = Time.getHoursDay();
	private static final int INTERVALS_A_DAY = (HOURS_A_DAY * 60) / (Time.getInterval()); 
	private TimeEvent[][] timeArray = new TimeEvent[DAYS_A_WEEK][INTERVALS_A_DAY];
	
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

