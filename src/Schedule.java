
public class Schedule {

	private TimeEvent[][] timeArray = new TimeEvent[7][96];
	private String entityName;
	
	public Schedule() {
	}
	
	public Schedule(String entityName){
		this.entityName = entityName;
	}
	
}
