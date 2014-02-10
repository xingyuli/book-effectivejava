package org.swordess.toy.effectivejava.chapter4.composition_prior_inheritance;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// Wrapper class - use composition in place of inheritance
public class InstrumentedSet<E> extends ForwardingSet<E> {

	private int addCount = 0;
	
	public InstrumentedSet(Set<E> s) {
		super(s);
	}

	@Override public boolean add(E e) {
		addCount++;
		return super.add(e);
	}
	
	@Override public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}
	
	public int getAddCount() {
		return addCount;
	}

	public static void main(String[] args) {
		Set<Date> s = new InstrumentedSet<Date>(new TreeSet<Date>());
		Set<String> s2 = new InstrumentedSet<String>(new HashSet<String>());
	}
	
}
