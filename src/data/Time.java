package data;

/**
 * Stores hours and minute values
 * @author ganc
 *
 */
public class Time {
	private int hour;
	private int minute;
	private static final int TIME_INTERVAL = 15;
	
	/** 
	 * Empty constructor for Time
	 */
	public Time (){
	}
	
	
	/**
	 * Time constructor that stores hours and minutes
	 * @param hours
	 * @param minutes
	 */
	public Time (int hours, int minutes){
		this.hour = hours;
		this.minute = nearestInterval(minutes);
	}
	
	/**
	 * returns the level of precision of time (i.e. 15 minutes, everything is rounded to the nearest 15.) 
	 * @return time interval
	 */
	public static int getInterval(){
		return TIME_INTERVAL;
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
	 * changes the stored hour value to parameter
	 * @param hour the new hour value to be stored
	 */
	public void setHour(int hour){
		this.hour = hour;
	}
	
	/**
	 * changes the stored minute value to parameter
	 * @param minute the new minute value to be stored
	 */
	public void setMinute(int minute) {
		this.minute = nearestInterval(minute);
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
