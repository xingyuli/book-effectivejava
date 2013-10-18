package org.swordess.effectivejava.chapter10.tip2;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ObservableSetTest {

	@Test
	public void willWork() {
		ObservableSet<Integer> set =
				new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
	@Test(expected = ConcurrentModificationException.class)
	public void willNotWorkBecauseOfConcurrentModification() {
		ObservableSet<Integer> set =
				new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
				if (element == 23) {
					// will throw ConcurrentModificationException as this
					// running added() method is called from
					// ObservableSet.notifyElementAdded() which is iterating the
					// the set.observers, but the following set.removeObserver
					// request to modify that observers List
					set.removeObserver(this);
				}
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
	// Use 5 seconds to indicate deadlock.
	@Test(timeout = 5000)
	public void willNotWorkBecauseOfDeadlock() {
		ObservableSet<Integer> set =
				new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			public void added(final ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
				if (element == 23) {
					ExecutorService executor =
							Executors.newSingleThreadExecutor();
					final SetObserver<Integer> observer = this;
					try {
						executor.submit(new Runnable() {
							public void run() {
								set.removeObserver(observer);
							}
						}).get();
					} catch (ExecutionException e) {
						throw new AssertionError(e.getCause());
					} catch (InterruptedException e) {
						throw new AssertionError(e.getCause());
					}
				}
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
}
