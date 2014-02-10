package org.swordess.toy.effectivejava.chapter10.concurrent_prior_wait_notify;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo {

	private static final ConcurrentMap<String, String> map =
			new ConcurrentHashMap<String, String>();
	
	public static String intern(String s) {
		String result = map.putIfAbsent(s, s);
		if (result == null) {
			result = s;
		}
		return result;
	}
	
	public static String fastIntern(String s) {
		String result = map.get(s);
		if (result == null) {
			result = map.putIfAbsent(s, s);
			if (result == null) {
				result = s;
			}
		}
		return result;
	}
	
}
