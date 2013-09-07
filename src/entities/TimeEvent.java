package entities;

public class TimeEvent {

	private String name = null;
	private int startTime;
	private int endTime;
	public TimeEvent() {
	}
	
	public TimeEvent(String name, int startTime, int endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * returns name of the event
	 * @return name of the event
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the starting time of the event
	 * @return the starting time of the event
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * returns the ending time of the event
	 * @return the ending time of the event
	 */
	public int getEndTime() {
		return endTime;
	}
}
