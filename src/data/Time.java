package data;

import exceptions.InvalidEventException;

/**
 * Stores hours and minute values
 * @author ganc
 *
 */
public class Time {

	private static final int DAYS_A_WEEK = 7;
	private static final int HOURS_A_DAY = 24;
	private static final int TIME_INTERVAL = 15;
	private int hour;
	private int minute;
	private int weekday;
	
	/** 
	 * Empty constructor for Time
	 */
	public Time (){
	}
	
	/**
	 * Time constructor that stores hours and minutes
	 * @param hours
	 * @param minutes
	 * @throws InvalidEventException throws if time entered is invalid (e.g. minutes = 100)
	 */
	public Time (int weekday, int hours, int minutes) {
		setWeekday(weekday);
		setHour(hours);
		setMinute(minutes);
	}

	/**
	 *  returns stored hour value
	 * @return stored hour value
	 */
	public int getHour(){
		return hour;
	}
	
	/**
	 * returns stored minute value
	 * @return stored minute value
	 */
	public int getMinute(){
		return minute;
	}
	
	/**
	 * returns stored day of the week as an integer (Sunday is 1, Saturday is 7)
	 * @return stored day of the week as an integer
	 */
	public int getWeekday(){
		return weekday;
	}
	
	/**
	 * changes the stored hour value to parameter
	 * @param hour the new hour value to be stored
	 */
	public void setHour(int hour) {
		while (hour < 0) {
			setWeekday(getWeekday() - 1);
			hour += 24; 
		}
		
		while (hour > 23) {
			setWeekday(getWeekday() + 1);
			hour -= 24;
		}
		
		this.hour = hour;
	}
	
	/**
	 * changes the stored minute value to parameter
	 * @param minute the new minute value to be stored
	 */
	public void setMinute(int minute) {
		while (minute < 0) {
			setHour(getHour() - 1);
			minute += 60;
		}
			
		while (minute > 59) {
			setHour(getHour() + 1);
			minute -= 60;
		}
			
		if ( minute > (60 - (TIME_INTERVAL/2)) ) {
			setHour(getHour() + 1);
			minute = 0;
		}
		
		this.minute = nearestInterval(minute);
	}
	
	/**
	 * changes the stored minute value to parameter
	 * @param minute the new minute value to be stored
	 */
	public void setWeekday(int minute) {
		while (weekday < 0)
			weekday += 7;
		
		while (weekday > 6)
			weekday -= 7;
		
		this.weekday = nearestInterval(weekday);
	}
	
	/**
	 * returns true if this Time is equal to parameter Time b
	 * @param b Time Object to compare to this object
	 * @return if the two Time objects are equal
	 */
	public boolean equals(Time b){
		if (this.weekday == b.weekday && this.hour == b.hour && this.minute == b.minute)
			return true;
		else
			return false;
	}
	
	
	/**
	 * returns the level of precision of time (i.e. 15 minutes, everything is rounded to the nearest 15.) 
	 * @return time interval
	 */
	public static int getInterval(){
		return TIME_INTERVAL;
	}
	
	/**
	 * returns the number of days a week in the calendar 
	 * @return number of days considered by the calendar every week
	 */
	public static int getDaysWeek(){
		return DAYS_A_WEEK;
	}
	
	/**
	 * returns the number of hours a day counted in the calendar 
	 * @return number of hours a day considered by the calendar
	 */
	public static int getHoursDay(){
		return HOURS_A_DAY;
	}
	
	
	/**
	 * returns the time rounded to the nearest TIME_INTERVAL
	 * @param time time to be rounded as
	 * @return rounded time
	 */
	private int nearestInterval(int minute) {
		if (minute % TIME_INTERVAL  == 0)
			return minute;
		else {
			int delta = minute % TIME_INTERVAL;
			if (delta > TIME_INTERVAL/2)
				minute += (TIME_INTERVAL-delta);
			 else 
				minute -= delta;
			return minute;
		}
	}
	
	
}
