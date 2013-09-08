package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import data.ReadAndWrite;

public class EntityGroup {
	
	private ArrayList<Entity> entityList;;
	private String name;
	
	public EntityGroup(){
		entityList = new ArrayList<Entity>();
	}
	
	public EntityGroup(String ID) throws FileNotFoundException {
		EntityGroup newGrp = ReadAndWrite.readEntityGroupFromFile(ID);
		entityList = newGrp.entityList;
		name = newGrp.name;
	}

	/**
	 * add an entity to the group
	 * @param ent entity to be added to the group
	 */
	public void addEntity(Entity ent){
		if (!entityList.contains(ent))
			entityList.add(ent);
	}
	
	/**
	 * deletes the entity passed to it from the group
	 * @param ent entity to be deleted from the group
	 */
	public void delEntity(Entity ent){
		entityList.remove(ent);
	}
	
	/**
	 * returns the list of entities in the group
	 * @return list of entities in the group
	 */
	public ArrayList<Entity> getEntities(){
		return entityList;
	}
	
	public String getName() {
		return name;
	}
	
	
	
}
