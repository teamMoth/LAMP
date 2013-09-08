package comparison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import data.Time;

import entities.Entity;
import entities.EntityGroup;
import entities.Schedule;

public class scheduleComparison {

	EntityGroup LOE; //List Of Entities
	ArrayList<Entity> AOE;
	
	public scheduleComparison(EntityGroup LOE){
		this.LOE = LOE;
		this.AOE = LOE.getEntities();
	}
	
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
}
