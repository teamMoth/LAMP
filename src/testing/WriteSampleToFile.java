package testing;

import java.io.IOException;

import data.ReadAndWrite;
import data.Time;

import midend.Database;

import entities.Entity;
import entities.EntityGroup;

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
		entToChange.getSchedule().addEvent("Test", new Time(1,8,0), new Time(1,14,0));
		
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
