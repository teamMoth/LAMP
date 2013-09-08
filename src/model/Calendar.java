package model;


public class Calendar {
	public TimeSlot[][] schedule;
	
	public Calendar(int maxRows, int maxCols){
		schedule = new TimeSlot[maxRows][maxCols];
		for (int row = 0; row < maxRows; row ++){
			for (int col = 0; col < maxCols; col ++){
				schedule[row][col] = new TimeSlot();
			}
		}
	}
	
	public void setTimeSlot(int rowIndex, int colIndex, TimeSlot timeSlot){
		schedule[rowIndex][colIndex] = timeSlot;
	}
	
	public TimeSlot getTimeSlot(int rowIndex, int colIndex){
		return schedule[rowIndex][colIndex];
	}
	
	
	/* could be used to clear the board using a new TimeSlot() */
	public void setScheduleWithColor(TimeSlot timeSlot){
		for (int row = 0; row < schedule.length; row ++){
			for (int col = 0; col < schedule[0].length; col ++){
				schedule[row][col] = timeSlot;
			}
		}
	}
	
}
