package entities;

import java.util.HashSet;
import java.util.Set;

import data.Time;
import exceptions.InvalidEventException;


public class Schedule {
	
	private static final int DAYS_A_WEEK = Time.getDaysWeek();
	private static final int HOURS_A_DAY = Time.getHoursDay();
	private static final int INTERVALS_A_DAY = (HOURS_A_DAY * 60) / (Time.getInterval()); 
	private TimeEvent[][] timeArray = new TimeEvent[DAYS_A_WEEK][INTERVALS_A_DAY];
	
	public Schedule() {
	}
	
	public boolean addEvent(String eventName, Time start, Time end) {
		TimeEvent event = new TimeEvent (eventName, start, end);
		for (int wk = start.getWeekday(); wk < end.getWeekday(); wk++){
			for (int intrvl = timeToInterval(start); intrvl < timeToInterval(end); intrvl++) {
				timeArray[wk][intrvl] = event;
			}
		}
		return true;
	}
	
	public TimeEvent getEventAtTime(Time t){
		return null;
	}
	
	/**
	 * Returns the schedule, in array form, of the Entity in question ONLY
	 * including the available times.
	 */
	public Set<Time> timesFree() {
		Set<Time> times = new HashSet<Time>();
		for (int wk = 0; wk < Time.getDaysWeek(); wk++ ) {
			for (int intrvl = 0; intrvl < INTERVALS_A_DAY; intrvl++){
				TimeEvent event = timeArray[wk][intrvl]; 
				if ( (event != null) && (!times.contains(event)) ){
					times.add(event);
				}
			}
		}
	}
	
	/**
	 * returns int corresponding to the interval that is Time t
	 * @param t Time to be converted into interval 
	 * @return interval corresponding to Time t
	 *   
	 */
	private int timeToInterval(Time t) {
		int result = (t.getHour()*60 + t.getMinute())/Time.getInterval();
		return result;
	}
	
	/**
	 * Returns Time object corresponding to the weekday and interval
	 * @param weekday  first Schedule array index, the weekday (0-6)
	 * @param interval
	 * @return Time Time object that corresponds to the indices in the Schedule array
	 * @throws InvalidEventException  throws if invalid times given
	 */
	private Time intervalToTime(int weekday, int interval) {
		int hour = (interval * Time.getInterval()) / 60;
		int minute = (interval * Time.getInterval()) % 60;
		return new Time(weekday, hour, minute);
	}
	
}

