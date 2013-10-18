package org.swordess.effectivejava.chapter10.tip1;

import java.util.concurrent.TimeUnit;

// Cooperative thread termination with a volatile field
public class StopThread3 {
	
	private static volatile boolean stopRequested;
	
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested) {
					i++;
				}
				System.out.println("current i: " + i);
			}
		});
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}
	
}
