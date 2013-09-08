package entities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import data.ReadAndWrite;

public class EntityGroup {
	
	private ArrayList<Entity> entityList;;
	private String name;
	private String ID;

	
	public EntityGroup(){
		entityList = new ArrayList<Entity>();
		this.ID = String.valueOf(Integer.toHexString(this.hashCode()).toString()
				.substring(4, 8));
	}
	
	public EntityGroup(String name, String ID, ArrayList<Entity> entList) {
		this.name = name;
		this.ID = ID;
		this.entityList = entList;
	}
	
	public EntityGroup(String ID) throws IOException {
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
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getID() {
		return ID;
	}
	
}
