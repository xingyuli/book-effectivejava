package org.swordess.effectivejava.chapter10.tip2.impl2;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ObservableSetTest {

	@Test
	public void shouldWorkNow() {
		ObservableSet<Integer> set =
				new ObservableSet<Integer>(new HashSet<Integer>());
		
		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
				if (element == 23) {
					set.removeObserver(this);
				}
			}
		});
		
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
	}
	
	@Test
	public void shouldWorkNow2() {
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
