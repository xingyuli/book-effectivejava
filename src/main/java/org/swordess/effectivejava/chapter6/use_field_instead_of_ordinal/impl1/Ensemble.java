package org.swordess.effectivejava.chapter6.use_field_instead_of_ordinal.impl1;

// Abuse of ordinal to derive an associated value - DON'T DO THIS
public enum Ensemble {

	SOLO, DUET, TRIO, QUARTET, QUINTET,
	SEXTET, SEPTET, OCTET, NONET, DECTET;
	
	public int numberOfMusicians() { return ordinal() + 1; }
	
}
