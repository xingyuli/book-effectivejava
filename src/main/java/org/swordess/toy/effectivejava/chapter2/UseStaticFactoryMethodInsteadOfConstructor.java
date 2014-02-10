package org.swordess.toy.effectivejava.chapter2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseStaticFactoryMethodInsteadOfConstructor {

	public static void main(String[] args) {
		Map<String, List<String>> m1 = new HashMap<String, List<String>>();
		Map<String, List<String>> m2 = UseStaticFactoryMethodInsteadOfConstructor.newHashMap();
	}
	
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
}
