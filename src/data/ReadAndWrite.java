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
			fos = new FileOutputStream("Entites" + File.separator
					+ ent.getName());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ent);
			oos.close();
		} catch (IOException e) {
			System.out.println("File Save Failed!");
			e.printStackTrace();
			throw new FileNotFoundException();
		}
	}

	public static Entity readEntityFromFile(String ID)
			throws FileNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Entity ent = null;

		try {
			fis = new FileInputStream("Entites" + File.separator + ID);
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

	public static void writeEntityGroupToFile(EntityGroup entgrp)
			throws IOException {
		String name = entgrp.getName();
		String ID = entgrp.getID();
		ArrayList<Entity> entlist = entgrp.getEntities();

		File fin = new File(ID);
		FileWriter fw = new FileWriter(fin);
		fw.write(name + "\n");
		fw.write(ID + "\n");
		for (Entity ent : entlist) {
			fw.write(ent.getID() + "\n");
		}
		fw.flush();
		fw.close();
	}

	public static EntityGroup readEntityGroupFromFile(String ID)
			throws IOException {

		File fin = new File(ID);
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
