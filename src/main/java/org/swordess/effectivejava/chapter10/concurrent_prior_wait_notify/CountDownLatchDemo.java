package org.swordess.effectivejava.chapter10.concurrent_prior_wait_notify;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		new Driver().main();
	}
	
	public static long time(Executor executor, int concurrency,
			final Runnable action) throws InterruptedException {
		final CountDownLatch ready = new CountDownLatch(concurrency);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(concurrency);
		
		for (int i = 0; i < concurrency; i++) {
			executor.execute(new Runnable() {
				public void run() {
					ready.countDown();	// Tell timer we're ready
					try {
						start.await();	// Wait till peers are ready
						action.run();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					} finally {
						done.countDown();	// Tell timer we're done
					}
				}
			});
		}
		
		ready.await();		// Wait for all workers to be ready
		long startNanos = System.nanoTime();
		start.countDown();	// And they're off!
		done.await();		// Wait for all workers to finish
		return System.nanoTime() - startNanos;
	}
	
}

class Driver {
	
	private static final int N = 5;
	
	void main() throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);
		
		for (int i = 0; i < N; i++) {
			new Thread(new Worker(startSignal, doneSignal)).start();
		}
		
		doSomethingElse();			// don't let run yet
		startSignal.countDown();	// let all threads proceed
		doSomethingElse();
		doneSignal.await();			// wait for all to finish
	}

	private void doSomethingElse() {
		System.out.println("do something");
	}
	
}

class Worker implements Runnable {
	
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException e) {}
	}

	void doWork() {
		System.out.println("I'm hard working...");
	}
	
}