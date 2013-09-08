package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import data.Time;

/**
 * Represents a person or building or collection of events, etc.
 * stores its name, its schedule, a list of tags, and a unique ID
 * 
 * @author ganc
 *
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = -6326844684848713032L;
	private static String SPLIT_CHAR = ";";
	private static String FREE_TIME_STRING = "FREE"; 
	private Schedule entitySchedule;
	private ArrayList<String> tags;
	private String name;
	private String ID;

	/**
	 * Creates an entity
	 */
	public Entity() {
		entitySchedule = new Schedule();
		tags = new ArrayList<String>();
		ID = String.valueOf(Integer.toHexString(this.hashCode()).toString()
				.substring(4, 8));
	}

	/**
	 * Adds the parameter "tag" to the entity's list of tags
	 * 
	 * @param tag
	 */
	public void addTag(String tag) {
		if (!tags.contains(tag.toUpperCase()))
			tags.add(tag.toUpperCase());
	}

	/**
	 * returns an ArrayList of all the entity's tags
	 * 
	 * @return an ArrayList of all the entity's tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * deletes the tag from the entity's tag list, returns true if the deletion
	 * actually modified the list.
	 * 
	 * @param tag
	 *            tag to be deleted from tag list
	 * @return if the list was modified by the deletion
	 */
	public boolean deleteTag(String tag) {
		return tags.remove(tag);
	}

	/**
	 * Returns the schedule of the Entity.
	 * 
	 * @return schedule of the Entity
	 */
	public Schedule getSchedule() {
		return entitySchedule;
	}

	/**
	 * returns the name of the entity
	 * 
	 * @return name of this entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns what event the entity has at time t.
	 * 
	 * @return what event, or null if none, the entity has at time t.
	 */
	public TimeEvent eventAtTime(Time t) {
		return entitySchedule.getEventAtTime(t);
	}

	/**
	 * Returns the schedule, in array form, of the Entity in question ONLY
	 * including the available times.
	 */
	public Set<Time> timesFree() {
		return entitySchedule.timesFree();
	}

	/**
	 * Returns the next free Time for this entity after the Time t given
	 * 
	 * @param t
	 *            starting point of the search for free time
	 * @return next free Time after the Time t given
	 */
	public Time nextFree(Time t) {
		return entitySchedule.nextFree(t);
	}

	/**
	 * Returns the Identifier of the Entity Object, to be used for saving
	 * purposes.
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * Replaces the current schedule with one stored in an array of strings, [weekday][interval]
	 * with each cell representing a Time block
	 * @param strSched string representation of the schedule to add to Entity
	 */
	public void scheduleMaker(String[][] strSched) {
		int startwk = 0, startintrvl = 0;
		String schedStr = null, prevSchedStr;
		clearSchedule();
		
		for (int wk = 0; wk < strSched.length; wk++) {
			for (int intrvl = 0; intrvl < strSched[wk].length; intrvl++) {
				prevSchedStr = schedStr;
				schedStr = strSched[wk][intrvl];
				
				if (!schedStr.equalsIgnoreCase(prevSchedStr)) {
					String eventName = schedStr.split(SPLIT_CHAR)[0];
					if (!eventName.equalsIgnoreCase(FREE_TIME_STRING)) {
						addEvent(eventName, new Time(startwk, startintrvl),
								new Time(wk, intrvl - 1));
						startwk = wk;
						startintrvl = intrvl;
					}
				}

			}
		}
	}
	
	/**
	 * replaces the existing schedule with a new empty one
	 */
	private void clearSchedule() {
		entitySchedule = new Schedule();
	}

	/**
	 * adds an event to this entity's schedule
	 * 
	 * @param eventName
	 *            name of the event to day
	 * @param startTime
	 *            starting time of the event to add
	 * @param endTime
	 *            ending time of the event to add
	 */
	private void addEvent(String eventName, Time startTime, Time endTime) {
		entitySchedule.addEvent(eventName, startTime, endTime);
	}



}