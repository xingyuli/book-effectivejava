package org.swordess.effectivejava.chapter6.use_enumset_instead_of_bit_field.impl1;

// Bit field enumeration constants - OBSOLETE!
public class Text {
	public static final int STYLE_BOLD          = 1 << 0;	// 1
	public static final int STYLE_ITALIC        = 1 << 1;	// 2
	public static final int STYLE_UNDERLINE     = 1 << 2;	// 4
	public static final int STYLE_STRIKETHROUGH = 1 << 3;	// 8
	
	public void applyStyles(int styles) { /* ... */ }
	
	public static void main(String[] args) {
		Text text = new Text();
		text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
	}
}
