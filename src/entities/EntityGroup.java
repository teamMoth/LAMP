package entities;

import java.io.IOException;
import java.util.ArrayList;

import data.ReadAndWrite;

/**
 * A named collection of Entities
 * 
 * @author gac
 *
 */
public class EntityGroup {
	
	// List of entities in the group 
	private ArrayList<Entity> entityList;
	// Name of the group of entities
	private String name;
	//Group's unique ID, included for future extension, not currently used. (09/08/2013)
	private String ID;

	
/*	public EntityGroup(){
		entityList = new ArrayList<Entity>();
		this.ID = String.valueOf(Integer.toHexString(this.hashCode()).toString()
				.substring(4, 8));
	}
*/
	/**
	 * Constructor for EntityGroup that sets its name, ID, and membership list
	 * @param name name of the EntityGroup
	 * @param ID ID of the EntityGroup
	 * @param entList List of the entities in the EnetityGroup
	 */
	public EntityGroup(String name, String ID, ArrayList<Entity> entList) {
		this.name = name;
		this.ID = name;
		this.entityList = entList;
	}
	
	/**
	 * Constructs an EntityGroup from the file titled with the String ID
	 * @param ID title of the file to read the EntityGroup from. 
	 * @throws IOException throws if encounters reading errors
	 */
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
	
	/**
	 * set the name of the EntityGroup
	 * @param name string to set as the EntityGroup
	 */
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
	
	/**
	 * returns name of the EntityGroup
	 * @return name of the EntityGroup
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns ID of the EntityGroup
	 * as of 09/08/2013 ID of EntityGroup is not used. 
	 * @return ID of the EntityGroup
	 */
	public String getID() {
		return ID;
	}
	
}
