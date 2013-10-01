package org.swordess.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.herb;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.swordess.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.herb.Herb.Type;

public class Demo2 {

	public static void main(String[] args) {
		Herb[] garden = new Herb[] {
		    new Herb("Aaa", Type.ANNUAL),
		    new Herb("Bbb", Type.PERENNIAL),
		    new Herb("Ccc", Type.BIENNIAL),
		    new Herb("Ddd", Type.ANNUAL),
		    new Herb("Eee", Type.PERENNIAL),
		    new Herb("Fff", Type.BIENNIAL),
		    new Herb("Ggg", Type.ANNUAL),
		    new Herb("Hhh", Type.PERENNIAL),
		    new Herb("Iii", Type.BIENNIAL),
		};
		
		// Using an EnumMap to associate data with an enum
		Map<Herb.Type, Set<Herb>> herbsByType =
				new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
		for (Herb.Type t : Herb.Type.values()) {
			herbsByType.put(t, new HashSet<Herb>());
		}
		for (Herb h : garden) {
			herbsByType.get(h.type()).add(h);
		}
		System.out.println(herbsByType);
	}
	
}
