package data;

import java.io.Serializable;

import exceptions.InvalidEventException;

/**
 * Stores hours and minute values
 * @author ganc
 *
 */
public class Time implements Serializable{

	private static final long serialVersionUID = -6188245283950867206L;
	public static final int DAYS_A_WEEK = 7;
	public static final int HOURS_A_DAY = 24;
	//the level of precision of time (i.e. 15 minutes, everything is rounded to the nearest 15.) 
	public static final int TIME_INTERVAL = 15;
	public static final int START_HOUR = 0;
	public static final int END_HOUR = START_HOUR + HOURS_A_DAY;
	private int hour;
	private int minute;
	private int weekday;

	/** 
	 * Empty constructor for Time
	 */
	public Time (){
	}

	/**
	 * Returns Time object corresponding to the weekday and interval
	 * 
	 * @param weekday
	 *            first Schedule array index, the weekday (0-6)
	 * @param interval
	 * @return Time Time object that corresponds to the indices in the Schedule
	 *         array
	 * @throws InvalidEventException
	 *             throws if invalid times given
	 */
	public Time (int weekday, int interval) {
		int hour = (interval * Time.TIME_INTERVAL) / 60;
		int minute = (interval * Time.TIME_INTERVAL) % 60;
		new Time(weekday, hour, minute);
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
	 * Time constructor that stores hours and minutes from input strings
	 * @param dayOfWeek
	 * @param timeOfDay
	 * @throws InvalidEventException throws if dayOfWeek is not recognized
	 */
	public Time(String dayOfWeek, String timeOfDay) throws InvalidEventException, NumberFormatException{
		if (dayOfWeek.equals("RightNow")){
			// method to get current time
		}
		else{
			switch (dayOfWeek){
			case ("Sunday"):
				weekday = 0;
				break;
			case ("Monday"):
				weekday = 1;
				break;
			case ("Tuesday"):
				weekday = 2;
				break;
			case ("Wednesday"):
				weekday = 3;
				break;
			case ("Thursday"):
				weekday = 4;
				break;
			case ("Friday"):
				weekday = 5;
				break;
			case ("Saturday"):
				weekday = 6;
				break;
			default:
				throw new InvalidEventException();
			}
			String[] brokenTime = timeOfDay.split(" ");
			if (brokenTime[0].contains(":")){
				String[] hoursMinutes = brokenTime[0].split(":");
				hour = Integer.parseInt(hoursMinutes[0]);
				setMinute(Integer.parseInt(hoursMinutes[1]));
			}
			else{
				hour = Integer.parseInt(brokenTime[0]);
				minute = 0;
			}
			if (brokenTime.length > 1){
				if (brokenTime[1].toLowerCase().trim().equals("pm")){
					hour = hour + 12;
				}
			}
		}
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
		
		if (hour > END_HOUR)
			hour = END_HOUR;
		
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
	public void setWeekday(int weekday) {
		while (weekday < 0)
			weekday += DAYS_A_WEEK;
		
		while (weekday > 6)
			weekday -= DAYS_A_WEEK;
		
		this.weekday = nearestInterval(weekday);
	}
	
	/**
	 * returns true if this Time is equal to parameter Time b
	 * @param b Time Object to compare to this object
	 * @return if the two Time objects are equal
	 */
	public boolean equals(Time b){
		if (this.getWeekday() == b.getWeekday() && this.getHour() == b.getHour() 
				&& this.getMinute() == b.getMinute())
			return true;
		else
			return false;
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
	
	/**
	 * Compares this current Time object to another Time object passed to the method.
	 * 
	 * @param Time t - the time object for "this" to be compared to
	 * @return 0 if they're equal, a positive number if THIS is greater than t, and 
	 * a negative number if t is greater than THIS
	 */
	public int compareTo(Time t){
		if(this.getWeekday() == t.getWeekday()){
			if(this.getHour() == t.getHour()){
				if (this.getMinute() == t.getMinute()){
					return 0;
				}
				else{
					return this.getMinute() - t.getMinute();
				}
			}
			else{
				return this.getHour() - t.getHour();
			}
		}
		else{
			return this.getWeekday() - t.getWeekday();
		}
	}
	
	/**
	 * Compares this current Time object to another Time object passed to the method.
	 * 
	 * @param Time t - the time object for "this" to be compared to
	 * @return a new Time object, with the absolute value of the difference in the times between the 
	 * two times objects.
	 */
	public Time timeDiff(Time t){
		if(this.getWeekday() == t.getWeekday()){
			if(this.getHour() == t.getHour()){
				if (this.getMinute() == t.getMinute()){
					return new Time(0,0,0);
				}
				else{
					return new Time(0,0,Math.abs(this.getMinute() - t.getMinute()));
				}
			}
			else{
				return new Time(0,Math.abs(this.getHour() - t.getHour()),Math.abs(this.getMinute() - t.getMinute()));
			}
		}
		else{
			return new Time(Math.abs(this.getWeekday() - t.getWeekday()),Math.abs(this.getHour() - t.getHour()),Math.abs(this.getMinute() - t.getMinute()));
		}
	}
	

	/**
	 * returns int corresponding to the interval that is Time t
	 * 
	 * @param t
	 *            Time to be converted into interval
	 * @return interval corresponding to Time t
	 * 
	 */
	public int toInterval() {
		int result = (this.getHour() * 60 + this.getMinute()) / Time.TIME_INTERVAL;
		return result;
	}

	private String[] weekdayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	@Override
	public String toString() {
		return weekdayNames[this.getWeekday()] + " " + ( this.getHour() % 12 ) + ":" + this.getMinute() + ((this.getHour() / 12 == 0) ? "am" : "pm");
	}
	
}