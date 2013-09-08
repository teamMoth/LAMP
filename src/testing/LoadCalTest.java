package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.Calendar;
import entities.Entity;

public class LoadCalTest {

	public static Calendar readCal() throws FileNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Calendar ent = null;

		try {
			fis = new FileInputStream("lastDitch");
			ois = new ObjectInputStream(fis);
			ent = (Calendar) ois.readObject();
			ois.close();
		} catch (IOException e) {
			throw new FileNotFoundException();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ent;
		
	}
	
}
