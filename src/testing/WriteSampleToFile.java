package testing;

import java.io.IOException;

import data.ReadAndWrite;
import data.Time;
import midend.Database;
import entities.Entity;
import entities.EntityGroup;
import entities.TimeEvent;

public class WriteSampleToFile {

	public static void main(String[] args) throws IOException {
		
		Database db = new Database();
		
		db.addEntity("newEntity1");
		db.addEntity("newEntity2");
		
		db.changeEntityGroup("newGrp");
		db.addEntity("newEntity1");
		db.addEntity("newEntity3");
		
		String entityID = "newEntity3";
		Entity entToChange = null;
		try {
			entToChange = ReadAndWrite.readEntityFromFile(entityID);
		}
		catch (IOException e) {
			entToChange = new Entity();
		}
		TimeEvent ev = new TimeEvent("Test", new Time(0,0,0), new Time(0,14,0));
		ev.addProperty("COLOR", "-1");
		entToChange.getSchedule().addEvent(ev);
		
		try{
			ReadAndWrite.writeEntityToFile(entToChange);
		}
		catch (IOException e){
			System.out.println("Lament! All Progress Missing!");
			e.printStackTrace();
			throw new IOException();
		}
		
		
	}

}
