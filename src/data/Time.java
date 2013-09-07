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
	public Time (int hours, int minutes) throws InvalidEventException{
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
	 * @throws InvalidEventException throws if hour is negative or greater than 23
	 */
	public void setHour(int hour) throws InvalidEventException{
		if (hour < 0 || hour > 23)
			throw new InvalidEventException("Invalid hour value (negative or greater than 23)");
		this.hour = hour;
	}
	
	/**
	 * changes the stored minute value to parameter
	 * @param minute the new minute value to be stored
	 * @throws InvalidEventException throws if minutes is negative or greater than 60
	 */
	public void setMinute(int minute) throws InvalidEventException {
		if (minute < 0 || minute > 59)
			throw new InvalidEventException("Invalid minute value (negative or greater than 59)");
		if ( minute > (60 - (TIME_INTERVAL/2)) ) {
			setHour(getHour() + 1);
			minute = 0;
		}
		
		this.minute = nearestInterval(minute);
	}
	
	/**
	 * changes the stored minute value to parameter
	 * @param minute the new minute value to be stored
	 * @throws InvalidEventException throws if day of the week is invalid
	 */
	public void setWeekday(int minute) throws InvalidEventException {
		if (weekday < 1 || weekday > 7)
			throw new InvalidEventException("Invalid Day of the Week");
		this.weekday = nearestInterval(weekday);
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
