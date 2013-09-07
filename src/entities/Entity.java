package entities;

import java.util.ArrayList;
import java.util.Set;

import data.Time;

public class Entity {

	private Schedule entitySchedule;
	private ArrayList<String> tags = new ArrayList<String>();
	private String ID;

	/**
	 * Creates an entity with the schedule passed to it
	 * 
	 * @param entitySchedule
	 */
	public Entity(Schedule entitySchedule) {
		this.entitySchedule = entitySchedule;
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

	/**
	 * Sets the entity's schedule to the schedule passed to it
	 */
	public void setSchedule(Schedule newSchedule) {
		this.entitySchedule = newSchedule;
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
		return null;
	}

}