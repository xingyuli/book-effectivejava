package org.swordess.effectivejava.chapter8;

import java.util.Comparator;

import org.junit.Test;

import static junit.framework.Assert.*;

public class PrimitivePriorBoxedTypes {

	@Test
	public void boxedTypesHaveDifferentIdentification() {
		Comparator<Integer> naturalOrder = new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				return first < second ? -1 : (first == second ? 0 : 1);
			}
		};

		Comparator<Integer> fixedNaturalOrder = new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				int f = first;	// Auto-unboxing
				int s = second;	// Auto-unboxing
				return f < s ? -1 : (f == s ? 0: 1);
			}
		};
		
		assertTrue(naturalOrder.compare(new Integer(42), new Integer(42)) != 0);
		assertTrue(fixedNaturalOrder.compare(new Integer(42), new Integer(42)) == 0);
	}	
	
	// Integer could be null, while int can only be a valid number.
	Integer i;
	
	@Test(expected = NullPointerException.class)
	public void unbelievable() {
		if (i == 42) {
			System.out.println("Unbelievable");
		}
	}

}
