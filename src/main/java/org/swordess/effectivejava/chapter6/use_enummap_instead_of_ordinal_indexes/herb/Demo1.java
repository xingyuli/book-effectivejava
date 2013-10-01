package org.swordess.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.herb;

import java.util.HashSet;
import java.util.Set;

import org.swordess.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.herb.Herb.Type;

public class Demo1 {

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
		
		// Using an ordinal() to index an array - DON'T DO THIS!
		@SuppressWarnings("unchecked")
		Set<Herb>[] herbsByType = // Indexed by Herb.Type.ordinal() 
				(Set<Herb>[])new Set[Herb.Type.values().length];
		for (int i = 0; i < herbsByType.length; i++) {
			herbsByType[i] = new HashSet<Herb>();
		}
		for (Herb h : garden) {
			herbsByType[h.type().ordinal()].add(h);
		}
		for (int i = 0; i < herbsByType.length; i++) {
			System.out.printf("%s: %s%n",
					Herb.Type.values()[i], herbsByType[i]);
		}
	}
	
}
