package org.swordess.toy.effectivejava.chapter8;

import java.lang.ref.WeakReference;

public class CautionThePerformanceOfStringConcatenation {

	private static final String DUMMY_LINE =
			"0000000000" + 
			"1111111111" + 
			"2222222222" +
			"3333333333" +
			"4444444444" +
			"5555555555" +
			"6666666666" +
			"7777777777";
	
	private static final int LINE_WIDTH = DUMMY_LINE.length();

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		statement();
		long consume = System.currentTimeMillis() - start;
		
		start = System.currentTimeMillis();
		statementUseWeakReference();
		long weakReferenceConsume = System.currentTimeMillis() - start;
		
		start = System.currentTimeMillis();
		fastStatement();
		long fastConsume = System.currentTimeMillis() - start;
		
		System.out.printf("consume:%dms, weak reference consume: %dms, fast consume: %dms",
				consume, weakReferenceConsume, fastConsume);
	}
	
	private static String statement() {
		String result = "";
		for (int i = 0; i < numItems(); i++) {
			result += lineForItem(i);
		}
		return result;
	}

	private static String statementUseWeakReference() {
		WeakReference<String> result = new WeakReference<String>("");
		for (int i = 0; i < numItems(); i++) {
			result = new WeakReference<String>(result.get() + lineForItem(i));
		}
		return result.get();
	}
	
	private static String fastStatement() {
		StringBuilder result = new StringBuilder(numItems() * LINE_WIDTH);
		for (int i = 0; i < numItems(); i++) {
			result.append(lineForItem(i));
		}
		return result.toString();
	}
	
	private static int numItems() {
		return 2000;
	}
	
	private static String lineForItem(int i) {
		return DUMMY_LINE;
	}

}
