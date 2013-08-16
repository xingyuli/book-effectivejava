package org.swordess.effectivejava.chapter2;

import org.swordess.effectivejava.Utils;

public class AvoidToCreateUnnecessaryObjects {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// Long sum = 0L;
		long sum = 0L;
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		long end = System.currentTimeMillis();
		System.out.println(sum);
		System.out.println("time spent: " + (end - start) + " ms");
		
		Utils.exit(0);
	}
	
}
