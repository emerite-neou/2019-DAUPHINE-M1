package fr.dauphine.concu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Runner {
	public static Runnable getProducerWithAnonymeClass(BlockingQueue<String> buffer, String message, int waitTime) {
		return new Runnable() {
			@Override
			public void run() {
				while (true) {
					//System.out.println(message);
					try {
						buffer.put(message);
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};
	}

	public static Runnable getProducerWithLambda(BlockingQueue<String> buffer, String message, int waitTime) {
		return () -> {
			while (true) {
				//System.out.println(message);
				try {
					buffer.put(message);
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	public static Runnable getConsumer(BlockingQueue<String> buffer, String message, int waitTime) {
		return new Runnable() {
			@Override
			public void run() {
				while (true) {
					//System.out.println(message);
					try {
						String s = buffer.take();
						System.out.println(message+" affiche "+s);
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};
	}
	
	public static Runnable getConsumer2(BlockingQueue<String> buffer, String message, int waitTime) {
		return () -> {
			while (true) {
				//System.out.println(message);
				try {
					String s = buffer.take();
					System.out.println(message+" affiche "+s);
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}	
	
	
	public static void main(String[] args) {
		BlockingQueue<String> buffer = new ArrayBlockingQueue<>(10);
		
		/* CREATE THE PRODUCERS */
		new Thread(getProducerWithAnonymeClass(buffer, "p1", 1000)).start();
		new Thread(getProducerWithLambda(buffer, "p2", 2000)).start();
		new Thread(new Producer(buffer, "p3", 1500)).start();
		
		/* CREATE THE CONSOMMERS */
		new Thread(getConsumer(buffer, "c1", 1000)).start();
		new Thread(getConsumer2(buffer, "c2", 1000)).start();
		new Thread(new Consummer(buffer, "c3", 1500)).start();
	}
}
