package testing;

import midend.Database;

public class ReadingSampleFromFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Database db = new Database();
		db.changeEntityGroup("default");
		
		System.out.println(db.freeNow());
		System.out.println(db.getOpenSchedule());

	}

}
