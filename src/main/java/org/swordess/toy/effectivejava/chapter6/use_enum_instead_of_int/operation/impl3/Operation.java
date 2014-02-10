package org.swordess.toy.effectivejava.chapter6.use_enum_instead_of_int.operation.impl3;

import java.util.HashMap;
import java.util.Map;

// Enum type with constant-specific class bodies and data
public enum Operation {
	
	PLUS("+")   { double apply(double x, double y) { return x + y; } },
	MINUS("-")  { double apply(double x, double y) { return x - y; } },
	TIMES("*")  { double apply(double x, double y) { return x * y; } },
	DIVIDE("/") { double apply(double x, double y) { return x / y; } };
	
	// Implementing a fromString method on an enum type
	private static final Map<String, Operation> stringToEnum
		= new HashMap<String, Operation>();
	
	static { // Initialize map from constant name to enum constant
		for (Operation op : values()) {
			stringToEnum.put(op.toString(), op);
		}
	}
	
	private final String symbol;
	Operation(String symbol) { this.symbol = symbol; }
	@Override public String toString() { return symbol; }
	
	abstract double apply(double x, double y);
	
	// Returns Operation for string, or null if string is invalid
	public static Operation fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
	
}
