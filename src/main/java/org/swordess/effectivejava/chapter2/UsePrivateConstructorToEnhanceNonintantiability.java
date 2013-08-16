package org.swordess.effectivejava.chapter2;

public class UsePrivateConstructorToEnhanceNonintantiability {

	// Noninstantiable utility class
	public class UtilityClass {
	
		// Suppress default constructor for noninstantiablity
		private UtilityClass() {
			throw new AssertionError();
		}
		
	}
	
}
