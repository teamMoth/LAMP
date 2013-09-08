package testing;

import java.io.FileNotFoundException;

import data.ReadAndWrite;
import entities.Entity;
import midend.Database;

public class ReadingSampleFromFile {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Database db = new Database();
		db.changeEntityGroup("default");
		
		Entity testEnt = ReadAndWrite.readEntityFromFile("newEntity3");
		System.out.println("Hello???????");
		System.out.println(testEnt.getSchedule().toString());
		System.out.println("tes?: " + testEnt.scheduleToGUI());
		System.out.println(db.freeNow());
		System.out.println(db.getOpenSchedule());

	}

}
