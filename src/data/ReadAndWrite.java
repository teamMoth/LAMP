package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entities.Entity;
import entities.EntityGroup;

public class ReadAndWrite {

	public static void writeEntityToFile(Entity ent)
			throws FileNotFoundException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			File fo = new File("Entities" + File.separator
					+ ent.getID());
			fo.setLastModified(0); 
			fos = new FileOutputStream(fo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ent);
			oos.close();
		} catch (IOException e) {
			System.out.println("File Save Failed! Here");
            e.printStackTrace();
		}
	}

	public static Entity readEntityFromFile(String ID)
			throws FileNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Entity ent = null;

		try {
			fis = new FileInputStream("Entities" + File.separator + ID);
			ois = new ObjectInputStream(fis);
			ent = (Entity) ois.readObject();
			ois.close();
		} catch (IOException e) {
			throw new FileNotFoundException();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ent;
	}

	/**
	 * writes the information of the EntityGroup and the IDs of the Entities it contains 
	 * @param entgrp the EntityGroup to be written
	 * @throws IOException throws if error writing
	 */
	public static void writeEntityGroupToFile(EntityGroup entgrp)
			throws IOException {
		String name = entgrp.getName();
		String ID = entgrp.getID();
		ArrayList<Entity> entlist = entgrp.getEntities();

		File fin = new File("EntityGroup" + File.separator + ID);
		fin.setLastModified(0);
		FileWriter fw = new FileWriter(fin);
		fw.write(name + "\n");
		fw.write(ID + "\n");
		for (Entity ent : entlist) {
			fw.write(ent.getID() + "\n");
		}
		fw.flush();
		fw.close();
	}

	/**
	 * reads the information about an EntityGroup from a File and then loading in
	 * the entities it contains separately 
	 * @param ID name of the file it reads from
	 * @return EntityGroup read from the file 
	 * @throws IOException if error reading or file not found
	 */
	public static EntityGroup readEntityGroupFromFile(String ID)
			throws IOException {

		File fin = new File("EntityGroup" + File.separator + ID);
		BufferedReader br = new BufferedReader(new FileReader(fin));
		
		String egName = br.readLine();
		String egID = br.readLine();
		String entID;
		ArrayList<Entity> entList = new ArrayList<Entity>();
		while ((entID = br.readLine()) != null) {
			entList.add(readEntityFromFile(entID));
		}
		br.close();
		return new EntityGroup(egName, egID, entList);
	}
}
