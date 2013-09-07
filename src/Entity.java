
public interface Entity {
	
	/*
	 * Returns the schedule, in array form, of the Entity in question.
	 */
	public Schedule getSchedule();
	
	/*
	 * Sets the schedule to "schedule" of the Entity in question.
	 */
	public boolean setSchedule(Object[] schedule);
	
	/*
	 * Returns the entity which has an event at time t.
	 */
	public Entity eventAtTime(int t);
	
	/*
	 * Returns the schedule, in array form, of the Entity in question ONLY including the available times.
	 */
	public Schedule timesFree();
	
}
