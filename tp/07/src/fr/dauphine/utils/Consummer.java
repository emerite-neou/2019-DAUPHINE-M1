package fr.dauphine.utils;

import java.util.concurrent.BlockingQueue;

public class Consummer implements Runnable {
	
	private final BlockingQueue<String> buffer;
	private final String id;
	private final int waitTime;
	
	public Consummer(BlockingQueue<String> buffer, String id, int waitTime) {
		this.buffer = buffer;
		this.id = id;
		this.waitTime = waitTime;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String s = buffer.take();
				System.out.println(id+" affiche "+s);
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

}
