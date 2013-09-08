package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entities.Entity;
import entities.EntityGroup;

public class ReadAndWrite {

	public static void writeEntityToFile(Entity ent) throws FileNotFoundException{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("Entites/"+ent.getName());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ent);
			oos.close();
		} catch (IOException e) {
			System.out.println("File Save Failed!");
			e.printStackTrace();
			throw new FileNotFoundException();
		}
	}
	
	public static Entity readEntityFromFile(String ID) throws FileNotFoundException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Entity ent = null;
		
		try {
			fis = new FileInputStream("Entites/"+ID);
			ois = new ObjectInputStream(fis);
			ent = (Entity) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileNotFoundException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return ent;
	}
	
	public static void writeEntityGroupToFile(EntityGroup entgrp) throws FileNotFoundException{
		String name = entgrp.getName();
		String ID = entgrp.getID();
		ArrayList<Entity> entlist = entgrp.getEntities();
		
		File fin = new File(ID);
		try {
			FileWriter fw = new FileWriter(fin);
			fw.write(name + "\n");
			fw.write(ID + "\n");
			for (Entity ent: entlist) {
				fw.write(ent.getID() + "\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static EntityGroup readEntityGroupFromFile(String ID) throws FileNotFoundException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		EntityGroup entgrp = null;
		
		try {
			fis = new FileInputStream("EntityGroup/"+ID);
			ois = new ObjectInputStream(fis);
			entgrp = (EntityGroup) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileNotFoundException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException();
		}
		
		return entgrp;
	}
}
