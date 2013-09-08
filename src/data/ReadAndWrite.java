package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import entities.Entity;

public class ReadAndWrite {

	public void writeToFile(Entity ent){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(ent.getName()+ent.getID());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ent);
			oos.close();
		} catch (IOException e) {
			System.out.println("File Save Failed!");
			e.printStackTrace();
		}
	}
	
	public Entity readFromFile(Entity entity){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Entity ent = null;
		
		try {
			fis = new FileInputStream(entity.getName()+entity.getID());
			ois = new ObjectInputStream(fis);
			ent = (Entity) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return ent;
	}
}
