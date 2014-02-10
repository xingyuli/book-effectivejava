package org.swordess.toy.effectivejava.chapter2;

public class UsePrivateConstructorOrEnumToEnhanceTheSingleton {

	public static class Elvis {
		public static final Elvis INSTANCE = new Elvis();
		private Elvis() { /* ... */ }
	}
	
	public static class Elvis2 {
		private static final Elvis2 INSTANCE = new Elvis2();
		private Elvis2() { /* ... */ }
		public static Elvis2 getInstance() { return INSTANCE; }
	}
	
	public static enum Elvis3 {
		INSTANCE;
	}
	
}
