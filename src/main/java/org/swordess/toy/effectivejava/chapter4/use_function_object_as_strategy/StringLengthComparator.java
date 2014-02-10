package org.swordess.toy.effectivejava.chapter4.use_function_object_as_strategy;

import java.io.Serializable;

class StringLengthComparator implements Comparator<String> {

	public static final StringLengthComparator INSTANCE = new StringLengthComparator();
	
	private StringLengthComparator() {
	}
	
	@Override
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
	}
	
}

class Host {
	
	private static class StrLenCmp implements Comparator<String>, Serializable {
		private static final long serialVersionUID = 1261890412799803184L;

		@Override
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}
	}
	
	// Returned comparator is serializable
	public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StrLenCmp();
	
}