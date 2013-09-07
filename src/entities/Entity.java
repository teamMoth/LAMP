package entities;

public class Entity {

	private String entityName;
	private Schedule entitySchedule;

	/**
	 * Returns the schedule, in array form, of the Entity in question.
	 * 
	 * @return the entity's schedule
	 */
	public Schedule getSchedule() {
		return null;
	}

	/**
	 * Sets the schedule to "schedule" of the Entity in question.
	 * 
	 * @param schedule the schedule to be assigned to the entity
	 */
	public void setSchedule(Object[] schedule) {
	}

	/**
	 * Returns if entity has an event at time t.
	 * 
	 * @return if entity has an event at time t
	 */
	public TimeEvent eventAtTime(int t) {
		return null;
	}

	/**
	 * Returns the schedule, in array form, of the Entity in question ONLY
	 * including the available times.
	 * 
	 * @return
	 */
	public Schedule timesFree() {
		return null;
	}

}
