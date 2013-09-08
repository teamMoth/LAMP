package testing;

import gui.ScheduleGUI;

public class strTimeTest {

	public static void main (String[] args) {
		String[][] arr = new String[7][36];
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 36; j++){
				arr[i][j] = "FREE;99999";
			}
		arr[0][0] = "CLASS;0x555555";
		
		ScheduleGUI scheGUI = new ScheduleGUI("Test", arr);
		scheGUI.main(args);
		
	}
	
}
