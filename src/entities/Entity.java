package entities;

import java.util.ArrayList;
import java.util.Set;

import data.Time;

public class Entity {

	private Schedule entitySchedule;
	private ArrayList<String> tags;
	private String Name;
	private String ID;

	/**
	 * Creates an entity
	 */
	public Entity() {
		entitySchedule = new Schedule();
		tags = new ArrayList<String>();
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
	 * @param tag tag to be deleted from tag list
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

	public void addEvent(String eventName, int startTime, int endTime) {
		
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
	 * Returns the Identifier of the Entity Object, to be used for saving purposes.
	 */
	public String getID(){
		return ID;
	}

}