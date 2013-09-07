package entities;

import java.util.ArrayList;

import data.Time;
import data.Tag;

public class Entity {

	private Schedule entitySchedule;
	private ArrayList<Tag> tags = new ArrayList<Tag>();

	/**
	 * Creates an entity with the schedule passed to it
	 * @param entitySchedule
	 */
	public Entity(Schedule entitySchedule){
		this.entitySchedule = entitySchedule;
	}

	/**
	 * Returns the schedule of the Entity.
	 * @return schedule of the Entity
	 */
	public Schedule getSchedule(){
		return entitySchedule;}

	/**
	 * Sets the entity's schedule to the schedule passed to it
	 */
	public void setSchedule(Schedule newSchedule){
		this.entitySchedule = newSchedule;
	}

	/**
	 * Returns what event the entity has at time t.
	 * @return what event, or null if none, the entity has at time t.
	 */
	public TimeEvent eventAtTime(Time t){
		return entitySchedule.getEventAtTime(t);
	}

	/**
	 * Returns the schedule, in array form, of the Entity in question ONLY including the available times.
	 */
	public Schedule timesFree(){
		return null;
	}

}