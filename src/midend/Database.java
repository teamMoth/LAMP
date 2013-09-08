package midend;

import java.io.IOException;
import java.util.ArrayList;

import comparison.scheduleComparison;
import data.ReadAndWrite;
import data.Time;
import entities.Entity;
import entities.EntityGroup;

public class Database{
	
	private EntityGroup grouplist;
	
	/**
	 * Default constructor for the Database Object. Creates a new, default EntityGroup.
	 */
	public Database(){
		grouplist = new EntityGroup("default", null, new ArrayList<Entity>());
	}
	
	/**
	 * returns a Time object which contains the current system time.
	 * @return toReturn
	 */
	public String freeNow(){
		Time now = MidEndFormatting.systemTime();
		return freeDuring(now);
	}
	
	/**
	 * returns a string which contains the currently busy people in EntityGroup
	 * @return output
	 */
	public String busyNow(){
		Time now = MidEndFormatting.systemTime();
		return busyDuring(now);
	}
	
	/**
	 * returns a string which contains the currently busy avaiable in EntityGroup
	 * @param String type
	 * @return output
	 */
	public String openNow(String type){
		Time now = MidEndFormatting.systemTime();
		return openDuring(now, type);
	}
	
	/**
	 * returns a string which contains the free people in EntityGroup during a certain time block
	 * @param Time t
	 * @return output
	 */
	public String freeDuring(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.freePeople(free, t);
		return output;
	}
	
	/**
	 * returns a string which contains the busy people in EntityGroup during a certain time block
	 * @param Time t
	 * @return output
	 */
	public String busyDuring(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.busyPeople(free, t);
		return output;
	}
	
	/**
	 * returns a string which contains the open buildings in EntityGroup during a certain time block of a specified type.
	 * @param Time t
	 * @param String type
	 * @return output
	 */
	public String openDuring(Time t, String type){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.openBuildings(free, t, type);
		return output;
	}
	
	/**
	 * returns a string which contains the open buildings in EntityGroup during a certain time block that are Eateries.
	 * @param Time t
	 * @return output
	 */
	public String openEateries(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.openBuildings(free, t, "eateries");
		return output;
	}
	
	/**
	 * returns a string which contains the open buildings in EntityGroup during a certain time block that are REC buildings.
	 * @param Time t
	 * @return output
	 */
	public String openREC(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.openBuildings(free, t, "rec");
		return output;
	}
	
	/**
	 * returns a string which contains the open buildings in EntityGroup during a certain time block that are Help Buildings.
	 * @param Time t
	 * @return output
	 */
	public String openHelp(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.openBuildings(free, t, "help");
		return output;
	}
	
	/**
	 * returns an array of strings, which contains the open schedule for all users in the grouplist.
	 * @return String[][]
	 */
	public String[][] getOpenSchedule(){
		scheduleComparison Scheduler = new scheduleComparison(grouplist);
		return Scheduler.freeTimes().scheduleToGUI();
	}
	
	/**
	 * Changes the EntityGroup to the specified group (newEntityGroup). If the group is not found on the local 
	 * disc, the group is created.
	 * @param String newEntityGroup
	 */
	public void changeEntityGroup(String newEntityGroup){
		try {
			grouplist = ReadAndWrite.readEntityGroupFromFile(newEntityGroup);
		} catch (IOException e) {
			grouplist = new EntityGroup(newEntityGroup, null, new ArrayList<Entity>());
			grouplist.setName(newEntityGroup);
		}
	}
	
	public String[][] getEntitySchedule(String entityID) {
		Entity ent;
		try {
			ent = ReadAndWrite.readEntityFromFile(entityID);
		}
		catch (IOException e) {
			ent = new Entity();
		}
		return ent.scheduleToGUI();
	}
	
	/**
	 * Changes a specific entity's schedule to newSchedule. If the entity is not found on the file system,
	 * it will create the entity, with the passed Schedule
	 * @param String entityID
	 * @param String[][] newSchedule
	 * @return output
	 * @throws IOException 
	 */
	public void changeEntitySchedule(String entityID, String[][] newSchedule) throws IOException{
		Entity entToChange = null;
		try {
			entToChange = ReadAndWrite.readEntityFromFile(entityID);
			System.out.println(entToChange.getSchedule());
		}
		catch (IOException e) {
			entToChange = new Entity();
			entToChange.setName(entityID);
		}
		entToChange.scheduleFromGUI(newSchedule);

		grouplist.addEntity(entToChange);
		try{
			ReadAndWrite.writeEntityToFile(entToChange);
			ReadAndWrite.writeEntityGroupToFile(grouplist);
		}
		catch (IOException e){
			System.out.println("Lament! All Progress Missing!");
			e.printStackTrace();
			throw new IOException();
		}
	}
	
	/**
	 * adds the Entity specified with the ID to the active grouplist. If the entity already exists on the disc,
	 * it will use the existing entity. if not, it will create a new entity.
	 * @param String entityID
	 * @return exists
	 * @throws IOException 
	 */
	public boolean addEntity(String entityID) throws IOException{
		boolean exists = false;
		
		Entity newEntity = null;
		
		try{
			newEntity = ReadAndWrite.readEntityFromFile(entityID);
			exists = true;
		}
		catch(IOException e){
			newEntity = new Entity(entityID);
		}
		
		grouplist.addEntity(newEntity);
		
		try{
			ReadAndWrite.writeEntityToFile(newEntity);
			ReadAndWrite.writeEntityGroupToFile(grouplist);
		}
		catch (IOException e){
			System.out.println("Lament! All Progress Missing!");
			e.printStackTrace();
			throw new IOException();
		}
		
		return exists;
	}
	
	/**
	 * returns a Time object which contains the current system time.
	 * @return Time t
	 */
	public Time getCurrentTime(){
		return MidEndFormatting.systemTime();
	}
}
