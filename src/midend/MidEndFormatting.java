package midend;

import java.util.ArrayList;
import comparison.scheduleComparison;
import data.Time;
import entities.Entity;
import entities.EntityGroup;
import entities.TimeEvent;

public class MidEndFormatting {

	/**
	 * returns a formatted String, which contains all the free people for a given time.
	 * @param Entity Group eg
	 * @param Time t
	 * @return Schedule
	 */
	static String freePeople(EntityGroup eg, Time t){
		String toReturn = "";
		
		scheduleComparison sc = new scheduleComparison(eg);
		EntityGroup eg2 = sc.freeMembers(t);
		
		for (Entity e:eg2.getEntities()){
			toReturn += e.getName() + " is free right now\n";
		}
		
		return toReturn;
	}
	
	/**
	 * returns a formatted String, which contains all the free people for a given time.
	 * @param Entity Group eg
	 * @param Time t
	 * @return Schedule
	 */
	static String busyPeople(EntityGroup eg, Time t){
		String toReturn = "";
		
		scheduleComparison sc = new scheduleComparison(eg);
		EntityGroup eg2 = sc.freeMembers(t);
		EntityGroup eg3 = new EntityGroup();
		
		for (Entity e : eg.getEntities()){
			if(!eg2.getEntities().contains(e)){
				eg3.addEntity(e);
			}
		}
		
		for (Entity e : eg3.getEntities()){
			toReturn += e.getName() + " is busy doing " + e.eventAtTime(t) + " and will be free at " + e.nextFree(t) + "\n";
		}
		
		return toReturn;
	}

	static String openBuildings(){
		String toReturn = "";



		return toReturn;
	}


	static void scheduleMaker(String[][] strSched, String ID) {
		int startwk, startintrvl, endwk, endintrvl;
		String schedStr, prevSchedStr;
		ArrayList<Entity> entityList = Database.getEntityList();
		for (int wkday = 0; wkday < strSched.length; wkday++){
			for (int intrvl = 0; intrvl < strSched[wkday].length; intrvl++) {
				prevSchedStr = schedStr;
				schedStr = strSched[wkday][intrvl];

				if (!schedStr.equalsIgnoreCase(prevSchedStr)) {

				}

				String eventName = schedStr.split(";")[0];
			}
		}
	}

}