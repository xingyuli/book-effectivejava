package org.swordess.toy.effectivejava.chapter10.tip1;

import java.util.concurrent.TimeUnit;

// Properly synchronized cooperative thread termination
public class StopThread2 {
	
	private static boolean stopRequested;
	
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested()) {
					i++;
				}
				System.out.println("current i: " + i);
			}
		});
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		requestStop();
	}
	
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	
	private static synchronized boolean stopRequested() {
		return stopRequested;
	}

}
