package midend;

import comparison.scheduleComparison;

import data.Time;
import entities.EntityGroup;

public class Database {
	
	EntityGroup grouplist;
	
	public String freeNow(){
		Time now = MidEndFormatting.systemTime();
		return freeDuring(now);
	}
	
	public String freeDuring(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.freePeople(free, t);
		return output;
	}
	
	public String busyDuring(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.busyPeople(free, t);
		return output;
	}
	
	public String openDuring(Time t){
		scheduleComparison sC = new scheduleComparison(grouplist);
		EntityGroup free = sC.freeMembers(t);
		String output = MidEndFormatting.openBuildings(free, t);
		return output;
	}
}
