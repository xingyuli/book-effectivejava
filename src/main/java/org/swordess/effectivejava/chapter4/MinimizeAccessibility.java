package org.swordess.effectivejava.chapter4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimizeAccessibility {

	public static class Thing {
	}
	
	private static final Thing[] PRIVATE_VALUES = { new Thing(), new Thing() };
	
	// solution 1
	public static final List<Thing> VALUES =
			Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
	
	// solution 2
	public static final Thing[] values() {
		return PRIVATE_VALUES.clone();
	}
	
}
