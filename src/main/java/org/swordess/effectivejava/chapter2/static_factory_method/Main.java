package org.swordess.effectivejava.chapter2.static_factory_method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<String, List<String>> m1 = new HashMap<String, List<String>>();
		Map<String, List<String>> m2 = Main.newHashMap();
	}
	
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
}
