package midend;

import data.Time;
import entities.EntityGroup;

public class Database {
	
	EntityGroup grouplist;
	
	public Database(String ID){
		EntityGroup grouplist = new EntityGroup(ID);
	}
	
	
	
	public String freeNow(){
		Time now = MidEndFormatting.systemTime();
		return null;
		// TODO
	}
	
}
