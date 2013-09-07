package entities;

import java.sql.Time;


public class Schedule {

	private TimeEvent[][] timeArray = new TimeEvent[DAYS_A_WEEK][INTERVALS_A_DAY];
	private static final int TIME_INTERVAL = 15;
	private static final int DAYS_A_WEEK = 7;
	private static final int HOURS_A_DAY = 24;
	private static final int INTERVALS_A_DAY = (HOURS_A_DAY * 60) / (TIME_INTERVAL); 
	
	public Schedule() {
	}
	
	public boolean addEvent(String eventName, int startTime, int endTime) {
		TimeEvent event = new TimeEvent (eventName, startTime, endTime);
		
		
		return true;
	}
	
	/**
	 * returns the time rounded to the nearest TIME_INTERVAL
	 * @param time time to be rounded as
	 * @return rounded time
	 */
	private int nearestInterval(Time t) {
		if (time % TIME_INTERVAL  == 0)
			return time;
		else {
			int delta = time % TIME_INTERVAL;
			if (delta > TIME_INTERVAL/2)
				time += (TIME_INTERVAL-delta);
			 else 
				time -= delta;
			return time;
		}
	}
	
	public TimeEvent getEventAtTime(data.Time t){
		return null;
	}
}

