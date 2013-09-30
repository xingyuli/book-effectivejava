package org.swordess.effectivejava.chapter7.with_caution_when_overload;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionClassifier {

	public static String classify(Set<?> s) {
		return "Set";
	}
	
	public static String classify(List<?> list) {
		return "List";
	}
	
	public static String classify(Collection<?> c) {
		return "Unknown Collection";
	}
	
	public static void main(String[] args) {
		Collection<?>[] collections = {
			new HashSet<String>(),
			new ArrayList<BigInteger>(),
			new HashMap<String, String>().values()
		};
		
		// choosing overloaded method is static, decided when compilation
		for (Collection<?> c : collections) {
			System.out.println(classify(c));
			if (c instanceof Set) {
				System.out.println(classify((Set<?>)c));
			} else if (c instanceof List) {
				System.out.println(classify((List<?>)c));
			}
		}
	}
	
}
