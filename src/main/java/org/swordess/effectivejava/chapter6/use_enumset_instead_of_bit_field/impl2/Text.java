package org.swordess.effectivejava.chapter6.use_enumset_instead_of_bit_field.impl2;

import java.util.EnumSet;
import java.util.Set;

// EnumSet - a modern replacement for bit fields
public class Text {
	public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }
	
	public void applyStyles(Set<Style> styles) { /* ... */ }
	
	public static void main(String[] args) {
		Text text = new Text();
		text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
	}
}
