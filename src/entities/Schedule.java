package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import data.Time;
import exceptions.InvalidEventException;

public class Schedule implements Serializable{

	private static final long serialVersionUID = 5581511733693556836L;
	private static final int INTERVALS_A_DAY = (Time.HOURS_A_DAY * 60)
			/ (Time.TIME_INTERVAL);
	private TimeEvent[][] timeArray;
	private ArrayList<TimeEvent> eventList;

	/**
	 * empty constructor
	 */
	public Schedule() {
		timeArray =  new TimeEvent[Time.DAYS_A_WEEK][INTERVALS_A_DAY];
		eventList = new ArrayList<TimeEvent>();
	}

	/**
	 * Adds event to the Schedule
	 * @param eventName name of the event to add
	 * @param start starting time of the event
	 * @param end ending time of the event
	 */
	public void addEvent(String eventName, Time start, Time end) {
		TimeEvent event = new TimeEvent(eventName, start, end);
		eventList.add(event);
		for (int wk = start.getWeekday(); wk < end.getWeekday(); wk++) {
			for (int intrvl = start.toInterval(); intrvl < end.toInterval(); intrvl++) {
				timeArray[wk][intrvl] = event;
			}
		}
	}
	

	/**
	 * Returns the TimeEvent that occurs at Time t
	 * @param t Time that is checked for events
	 * @return the event that occurs at Time t, if none, returns null
	 */
	public TimeEvent getEventAtTime(Time t) {
		int wk = t.getWeekday();
		int intrvl = t.toInterval();
		return timeArray[wk][intrvl];
	}

	/**
	 * Returns the next free Time after the Time t given
	 * @param t starting point of the search for free time
	 * @return next free Time after the Time t given
	 */
	public Time nextFree(Time t) {
		Time nextFreeTime = null;
		int wk = t.getWeekday();
		int intrvl = t.toInterval();
		
		for (int wkday = wk; wkday < Time.DAYS_A_WEEK; wkday++) {
			for (int timeChunk = intrvl; timeChunk < INTERVALS_A_DAY; timeChunk++){
				if (timeArray[wk][timeChunk] == null){
					nextFreeTime = new Time(wk, timeChunk);
				}
			}
		}
		return nextFreeTime;
	}
	
	/**
	 * returns a set of all Time objects where the Entity is free in the entire
	 * week
	 * 
	 * @return a set of all Time objects where the Entity is free in the entire
	 *         week
	 */
	public Set<Time> timesFree() {
		Set<Time> times = new HashSet<Time>();
		for (int wk = 0; wk < Time.DAYS_A_WEEK; wk++) {
			for (int intrvl = 0; intrvl < INTERVALS_A_DAY; intrvl++) {
				TimeEvent event = timeArray[wk][intrvl];
				if (event == null) {
					Time t = new Time(wk, intrvl);
					times.add(t);
				}
			}
		}
		return times;
	}

	/**
	 * returns a set of all Time objects where the Entity is free in a given day
	 * 
	 * @param wkday
	 *            the day of the week looked in for free Time objects
	 * @return a set of all Time objects where the Entity is free in the given
	 *         day, wkday
	 */
	public Set<Time> timesFreeDay(int wkday) {
		Set<Time> times = new HashSet<Time>();
		for (int intrvl = 0; intrvl < INTERVALS_A_DAY; intrvl++) {
			TimeEvent event = timeArray[wkday][intrvl];
			if (event == null) {
				Time t = new Time(wkday, intrvl);
				times.add(t);
			}
		}
		return times;
	}

	/**
	 * Refresh the timeArray based off the eventList
	 */
	private void updateTimeArray() {
		clearTimeArray();
		for (TimeEvent event : eventList) {
			addEvent(event);
		}
	}
	
	/**
	 * Clears the timeArray
	 */
	private void clearTimeArray(){
		for (int i = 0; i < timeArray.length; i++) {
			for (int j = 0; j < timeArray[i].length; j++) {
				timeArray[i][j] = null;
			}
		}
	}
	
	/**
	 * Insert an existing event in the timeArray
	 * @param event TimeEvent to insert
	 */
	private void addEvent(TimeEvent event) {
		Time start = event.getStartTime();
		Time end = event.getEndTime();
		for (int wk = start.getWeekday(); wk < end.getWeekday(); wk++) {
			for (int intrvl = start.toInterval(); intrvl < end.toInterval(); intrvl++) {
				timeArray[wk][intrvl] = event;
			}
		}
	}
}
