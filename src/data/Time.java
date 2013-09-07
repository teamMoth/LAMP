package data;

public class Time {
	private int hour;
	private int minute;
	
	public Time (){
		
	}
	
	public Time (int hours, int minutes){
		this.hour = hours;
		this.minute = minutes;
	}
	
	public int getHour(){
		return hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
	public void setHour(int hour){
		this.hour = hour;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
}
