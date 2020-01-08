package fr.fauphine;


public class Connection {

	private static Connection CON = null;
	private static Object o = new Object();
	
	private Connection() {System.out.println("AZE");}
	
	/**
	 * Lock complet sur tous les appels
	 * @return
	 */
	public static synchronized Connection getConnection() {
		if (CON == null) {
			CON = new Connection();
		}
		return CON;
	}
	
	public static synchronized Connection getConnection2() {
		synchronized (Connection.class) {
			if (CON == null) {
				CON = new Connection();
			}
			return CON;
		}
	}	

	/**
	 * Lock du test de CON à chaque appel 
	 * @return
	 */
	public static Connection getConnection3() {
		synchronized (o) {
			if (CON == null) {
				CON = new Connection();
			}	
		}
		return CON;
	}	
	
	/**
	 * Lock 
	 * @return
	 */
	public static Connection getConnection4() {
		if (CON == null) {
			synchronized (o) {
				if (CON == null) CON = new Connection();
			}	
		}
		return CON;
	}		
	
	public static void main(String[] args) {
		new Thread(() -> {getConnection();}).start(); 
		new Thread(() -> {getConnection();}).start(); 
		new Thread(() -> {getConnection();}).start(); 
		
		/*
		getConnection(); // init CON
		getConnection(); // CON est pas null pas d init
		getConnection(); // CON est pas null pas d init
		*/
		
	}
	
	
}
