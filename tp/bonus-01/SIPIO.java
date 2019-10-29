package fr.dauphine.sip.io;

import fr.dauphine.ds.filesystem.DauphineSystemFileSystem;

/**
 * A class to write and read files from dfs
 * DSIO: Dauphine System IO
 * 
 */
public class SIPIO {

	private final DauphineSystemFileSystem fileSystem;

	public SIPIO() {
		this.fileSystem = new DauphineSystemFileSystem();
	}
	
	/**
	 * @param path path of the file
	 * @param date the minimum date
	 * @return whether the file exist and is more recent than the given date
	 */
	public boolean exist(String path, String date) {
		return fileSystem.exist(path) && fileSystem.getCreationDate(path).compareTo(date) > 0;
	}	
}
