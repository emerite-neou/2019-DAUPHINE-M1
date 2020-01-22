package fr.dauphine.concu;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	
	private final BlockingQueue<String> buffer;
	private final String id;
	private final int waitTime;
	
	public Producer(BlockingQueue<String> buffer, String id, int waitTime) {
		this.buffer = buffer;
		this.id = id;
		this.waitTime = waitTime;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				buffer.put(id);
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}	
	}
}
