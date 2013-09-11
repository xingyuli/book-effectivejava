package org.swordess.effectivejava.chapter5.use_bounded_wildcard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataStructureUtils {

	private DataStructureUtils() {
	}
	
	public static boolean isEmpty(Collection<?> c) {
		return null == c || c.isEmpty();
	}
	
	public static boolean isEmpty(Map<?, ?> m) {
		return null == m || m.isEmpty();
	}
	
	public static boolean isNotEmpty(Collection<?> c) {
		return null != c && !c.isEmpty();
	}
	
	public static boolean isNotEmpty(Map<?, ?> m) {
		return null != m && !m.isEmpty();
	}
	
	public static <T> Set<T> intersection(Set<? extends T> s1, Set<? extends T> s2) {
		Set<T> intersection = new HashSet<T>();
		if (isNotEmpty(s1) && isNotEmpty(s2)) {
			intersection.addAll(s1);
			intersection.retainAll(s2);
		}
		return intersection;
	}
	
	public static <T> List<T> intersection(List<? extends T> l1, List<? extends T> l2) {
		Set<T> intersection = new LinkedHashSet<T>();
		if (isNotEmpty(l1) && isNotEmpty(l2)) {
			intersection.addAll(l1);
			intersection.retainAll(l2);
		}
		return new ArrayList<T>(intersection);
	}
	
	public static <K, V> Map<K, V> intersection(Map<? extends K, V> m1, Map<? extends K, V> m2, Prefer prefer) {
		Set<K> commonKeys = new LinkedHashSet<K>();
		if (isNotEmpty(m1) && isNotEmpty(m2)) {
			commonKeys.addAll(m1.keySet());
			commonKeys.retainAll(m2.keySet());
		}
		
		Map<? extends K, V> preferredMap = null;
		switch (prefer) {
		default:
		case FORMER: preferredMap = m1; break;
		case LATER: preferredMap = m2; break; 
		}
		
		Map<K, V> intersection = new HashMap<K, V>();
		for (K commonKey : commonKeys) {
			V preferredValue = preferredMap.get(commonKey);
			intersection.put(commonKey, preferredValue);
		}
		return intersection;
	}
	
	public static <T> Set<T> union(Set<? extends T> s1, Set<? extends T> s2) {
		Set<T> union = new HashSet<T>();
		if (isNotEmpty(s1)) {
			union.addAll(s1);
		}
		if (isNotEmpty(s2)) {
			union.addAll(s2);
		}
		return union;
	}
	
	public static <T> List<T> union(List<? extends T> l1, List<? extends T> l2) {
		Set<T> union = new LinkedHashSet<T>();
		if (isNotEmpty(l1)) {
			union.addAll(l1);
		}
		if (isNotEmpty(l2)) {
			union.addAll(l2);
		}
		return new ArrayList<T>(union);
	}
	
	public static <K, V> Map<K, V> union(Map<? extends K, V> m1, Map<? extends K, V> m2, Prefer prefer) {
		Map<K, V> union = new HashMap<K, V>();

		if (isNotEmpty(m1) && isNotEmpty(m2)) {
			Map<? extends K, V> preferredMap = null;
			Map<? extends K, V> candidateMap = null;
			switch (prefer) {
			default:
			case FORMER: preferredMap = m1; candidateMap = m2; break;
			case LATER: preferredMap = m2; candidateMap = m1; break;
			}
			
			/*
			 * Put candidateMap first, then preferredMap. Thus the keys in preferredMap
			 * will overwrite if candidateMap map also has those keys.
			 */
			union.putAll(candidateMap);
			union.putAll(preferredMap);
			
		} else if (isNotEmpty(m1)) {
			union.putAll(m1);
		} else if (isNotEmpty(m2)) {
			union.putAll(m2);
		}
		
		return union;
	}
	
	public static enum Prefer {
		FORMER, LATER;
	}
	
}
