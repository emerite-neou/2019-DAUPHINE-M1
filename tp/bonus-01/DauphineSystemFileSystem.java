package fr.dauphine.ds.filesystem;

public class DauphineSystemFileSystem {

		
	public DauphineSystemFileSystem() {
	}

	/**
	 * return whether the file exists
	 * 
	 * @param path path of the file
	 * @return whether the file exists
	 */
	public boolean exist(String path) {
		throw new RuntimeException("Dauphine System is not installed");
	}
	
	
	/**
	 * return when the file had been created
	 * the format of the date is yyyy-MM-dd
	 * empty String if it doesnt exist
	 * 
	 * @param path path of the file
	 * @return the date
	 */
	public String getCreationDate(String path) {
		throw new RuntimeException("Dauphine System is not installed");
	}
	
}
