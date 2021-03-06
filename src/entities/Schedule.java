package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import data.Time;

/**
 * An arraylist of events and time array representing when an entity is
 * available
 * 
 * @author ganc
 * 
 */
public class Schedule implements Serializable {

	private static final long serialVersionUID = 5581511733693556836L;
	private static String SPLIT_CHAR = ";";
	private static String FREE_TIME_STRING = "FREE";

	private TimeEvent[][] timeArray;
	private ArrayList<TimeEvent> eventList;

	/**
	 * empty constructor
	 */
	public Schedule() {
		timeArray = new TimeEvent[Time.DAYS_A_WEEK][Time.INTERVALS_A_DAY];
		eventList = new ArrayList<TimeEvent>();
	}

	/**
	 * Replaces the current schedule with one stored in an array of strings,
	 * [weekday][interval] with each cell representing a Time block
	 * 
	 * @param strSched
	 *            string representation of the schedule to add to Entity
	 */
	public Schedule(String[][] strSched) {
		timeArray = new TimeEvent[Time.DAYS_A_WEEK][Time.INTERVALS_A_DAY];
		eventList = new ArrayList<TimeEvent>();
		int startwk = 0, startintrvl = 0;
		String schedStr = null, prevSchedStr;
		System.out.println("frmgui1: " + strSched[0][0]);
		System.out.println(toString(strSched));

		int day = 0; int interv = 0; boolean cont = false;
		
		TimeEvent newEvent;
		
		for(int c = 0; c < strSched[0].length-1; c ++){
			for(int r = 0; r < strSched.length-1; r ++){
				if(strSched[r][c] != null && strSched[r+1][c+1] != null){
					if(strSched[r][c].equalsIgnoreCase(strSched[r+1][c+1])){
						cont = true;
						interv = r;
						day = c;
						while(cont = true && r < strSched.length-2){
							r ++;
							if(strSched[r][c] != null && strSched[r+1][c+1] != null){
								if(!strSched[r][c].equalsIgnoreCase(strSched[r+1][c+1])){
									cont = false;
								}
							}
							else{
								cont = false;
							}
						}
						newEvent = new TimeEvent("event"+c,new Time(day, interv), new Time(c, r));
						eventList.add(newEvent);
					}
				}
			}
		}
		System.out.println("frmgui2: "
				+ getEventAtTime(new Time(0, 0)).getName());
	}

	/**
	 * Adds event to the Schedule
	 * 
	 * @param eventName
	 *            name of the event to add
	 * @param start
	 *            starting time of the event
	 * @param end
	 *            ending time of the event
	 * @return
	 */
	public TimeEvent addEvent(String eventName, Time start, Time end) {
		TimeEvent event = new TimeEvent(eventName, start, end);
		eventList.add(event);
		for (int wk = start.getWeekday(); wk <= end.getWeekday(); wk++) {
			for (int intrvl = start.toInterval(); intrvl < end.toInterval(); intrvl++) {
				timeArray[wk][intrvl] = event;
			}
		}
		return event;
	}

	public TimeEvent addEvent(TimeEvent event) {
		eventList.add(event);
		Time start = event.getStartTime();
		Time end = event.getEndTime();
		for (int wk = start.getWeekday(); wk <= end.getWeekday(); wk++) {
			for (int intrvl = start.toInterval(); intrvl < end.toInterval(); intrvl++) {
				timeArray[wk][intrvl] = event;
			}
		}
		return event;
	}
	
	/**
	 * Returns the TimeEvent that occurs at Time t
	 * 
	 * @param t
	 *            Time that is checked for events
	 * @return the event that occurs at Time t, if none, returns null
	 */
	public TimeEvent getEventAtTime(Time t) {
		int wk = t.getWeekday();
		int intrvl = t.toInterval();
		return timeArray[wk][intrvl];
	}

	/**
	 * Returns the next free Time after the Time t given
	 * 
	 * @param t
	 *            starting point of the search for free time
	 * @return next free Time after the Time t given
	 */
	public Time nextFree(Time t) {
		Time nextFreeTime = null;
		int wk = t.getWeekday();
		int intrvl = t.toInterval();

		for (int wkday = wk; wkday < Time.DAYS_A_WEEK; wkday++) {
			for (int timeChunk = intrvl; timeChunk < Time.INTERVALS_A_DAY; timeChunk++) {
				if (timeArray[wk][timeChunk] == null) {
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
			for (int intrvl = 0; intrvl < Time.INTERVALS_A_DAY; intrvl++) {
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
		for (int intrvl = 0; intrvl < Time.INTERVALS_A_DAY; intrvl++) {
			TimeEvent event = timeArray[wkday][intrvl];
			if (event == null) {
				Time t = new Time(wkday, intrvl);
				times.add(t);
			}
		}
		return times;
	}

	/**
	 * returns a version of the current schedule into an array of strings of
	 * [weekday][interval] with each cell presenting a Time block with event
	 * name and color for the GUI
	 * 
	 * @return an array of strings for the GUI
	 */
	public String[][] scheduleToGUI() {
		String[][] strSched = new String[Time.INTERVALS_A_DAY][Time.DAYS_A_WEEK];
		for (int wk = 0; wk < Time.DAYS_A_WEEK; wk++) {
			for (int intrvl = 0; intrvl < Time.INTERVALS_A_DAY; intrvl++) {
				TimeEvent event = getEventAtTime(new Time(wk, intrvl));
				String str;
				if (event != null) {
					str = event.getName() + ";" + event.getProperty("COLOR");
				} else {
					str = "FREE;0xFFFFFF";
				}
				strSched[intrvl][wk] = str;
			}
		}
		System.out.println("!" + strSched[0][0]);
		System.out.println(toString(strSched));
		return strSched;
	}

	
	public String toString(String[][] arr) {
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j =0; j < arr[i].length; j++) {
				str += arr[i][j] + "\t";
			}
			str += "\n";
		}
		return str;
	}
}
