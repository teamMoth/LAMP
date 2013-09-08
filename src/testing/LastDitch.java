package testing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import data.ReadAndWrite;
import data.Time;
import model.*;

public class LastDitch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Calendar newCal = new Calendar(Time.INTERVALS_A_DAY, Time.DAYS_A_WEEK);
		
		for(int i = 0; i < 5; i ++){
			TimeSlot t = new TimeSlot();
			t.setColor(java.awt.Color.BLUE);
			t.setName("WORK");
			newCal.setTimeSlot(i, 0, t);
		}

		for(int i = 5; i < 9; i ++){
			TimeSlot t = new TimeSlot();
			t.setColor(java.awt.Color.RED);
			t.setName("WORK");
			newCal.setTimeSlot(i, 2, t);
		}
		for(int i = 9; i < 15; i ++){
			TimeSlot t = new TimeSlot();
			t.setColor(java.awt.Color.BLACK);
			t.setName("WORK");
			newCal.setTimeSlot(i, 5, t);
		}
		for(int i = 9; i < 15; i ++){
			TimeSlot t = new TimeSlot();
			t.setColor(java.awt.Color.BLUE);
			t.setName("WORK");
			newCal.setTimeSlot(i, 6, t);
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			File fo = new File("lastDitch");
			fo.setLastModified(0); 
			fos = new FileOutputStream(fo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(newCal);
			oos.close();
		} catch (IOException e) {
			System.out.println("File Save Failed! Here");
            e.printStackTrace();
		}
	}

}
