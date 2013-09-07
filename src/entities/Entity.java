package entities;

import data.Time;

public class Entity {

	private Schedule entitySchedule;

	public Entity(Schedule entitySchedule){
		this.entitySchedule = entitySchedule;
	}

	/*
	 * Returns the schedule, in array form, of the Entity in question.
	 */
	public Schedule getSchedule(){
		return entitySchedule;}

	/*
	 * Sets the schedule to "schedule" of the Entity in question.
	 */
	public void setSchedule(Schedule newSchedule){
		this.entitySchedule = newSchedule;
	}

	/*
	 * Returns the entity which has an event at time t.
	 */
	public TimeEvent eventAtTime(Time t){
		return entitySchedule.getEventAtTime(t);
	}

	/*
	 * Returns the schedule, in array form, of the Entity in question ONLY including the available times.
	 */
	public Schedule timesFree(){
		return null;
		}

}