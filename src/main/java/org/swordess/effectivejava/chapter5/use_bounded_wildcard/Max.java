package org.swordess.effectivejava.chapter5.use_bounded_wildcard;

import java.util.Iterator;
import java.util.List;

public class Max {

	private Max() {
	}
	
	public static <T extends Comparable<T>> T max(List<T> list) {
		Iterator<T> i = list.iterator();
		T result = i.next();
		while (i.hasNext()) {
			T t = i.next();
			if (t.compareTo(result) > 0) {
				result = t;
			}
		}
		return result;
	}
	
	public static <T extends Comparable<? super T>> T maxWithBoundedWildcard(List<? extends T> list) {
		Iterator<? extends T> i = list.iterator();
		T result = i.next();
		while (i.hasNext()) {
			T t = i.next();
			if (t.compareTo(result) > 0) {
				result = t;
			}
		}
		return result;
	}
	
}
