package midend;

import entities.EntityGroup;

public class Database {
	
	String name;
	EntityGroup grouplist;
	
	public Database(String name){
		this.name = name;
		this.grouplist = new EntityGroup();
	}
	
}
