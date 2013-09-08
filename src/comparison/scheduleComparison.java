package comparison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import data.Time;

import entities.Entity;
import entities.EntityGroup;
import entities.Schedule;
import exceptions.InvalidEventException;

public class scheduleComparison {

	EntityGroup LOE; //List Of Entities
	ArrayList<Entity> AOE;
	
	/**
	 * scheduleComparison constructor that stores a list of entities
	 * @param EntityGroup LOE
	 */
	public scheduleComparison(EntityGroup LOE){
		this.LOE = LOE;
		this.AOE = LOE.getEntities();
	}
	/**
	 * returns a Schedule, which contains all the free times for all members of the Entity Group LOE.
	 * @return Schedule
	 */
	public Schedule freeTimes(){
		
		Schedule newSched = new Schedule();
		HashSet<Time> newTime = new HashSet<Time>();
		
		//create a list of all schedules for the List Of Entities
		
		newTime.addAll(AOE.remove(0).timesFree());
		
		for (Entity e: AOE){
			newTime.retainAll(e.timesFree());
		}
		
		Iterator<Time> i = newTime.iterator();
		while(i.hasNext()){
			Time nextFree = i.next();
			newSched.addEvent("FreeTime", new Time(nextFree.getWeekday(), nextFree.getHour(), nextFree.getMinute()), 
					new Time(nextFree.getWeekday(), nextFree.getHour(), nextFree.getMinute()+Time.TIME_INTERVAL));
		}
		
		return newSched;
	}
	
	/**
	 * returns a new EntityGroup, which contains all the free members for a given time range.
	 * @param Time t
	 * @return Schedule
	 */
	public EntityGroup freeMembers(Time t){
		
		EntityGroup freeMembs = new EntityGroup();
		
		Iterator<Entity> i = AOE.iterator();
		while(i.hasNext()){
			Entity nextEnt = i.next();
			if(nextEnt.timesFree().contains(t)){
				freeMembs.addEntity(nextEnt);
			}
		}
		
		return freeMembs;
	}
		
}
