package entities;

import java.util.ArrayList;

public class EntityGroup {
	
	private ArrayList<Entity> entityList= new ArrayList<Entity>();
	
	public void addEntity(Entity ent){
		if (!entityList.contains(ent))
			entityList.add(ent);
	}
	
	public void delEntity(Entity ent){
		entityList.remove(ent);
	}
	
	
	
	
}
