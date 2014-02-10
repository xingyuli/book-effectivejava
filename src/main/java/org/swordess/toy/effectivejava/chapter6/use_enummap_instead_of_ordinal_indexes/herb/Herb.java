package org.swordess.toy.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.herb;


public class Herb {

	public enum Type { ANNUAL, PERENNIAL, BIENNIAL }
	
	private final String name;
	private final Type type;
	
	Herb(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public Type type() {
		return type;
	}
	
	@Override public String toString() {
		return name;
	}
	
}
