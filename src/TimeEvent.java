
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
	
	public String getName() {
		return name;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
}
