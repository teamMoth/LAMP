package midend;

import java.util.ArrayList;

import entities.Entity;
import entities.TimeEvent;

public class MidEndFormatting {

	static String freePeople(){
		String toReturn = "";



		return toReturn;
	}

	static String busyPeople(){
		String toReturn = "";



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