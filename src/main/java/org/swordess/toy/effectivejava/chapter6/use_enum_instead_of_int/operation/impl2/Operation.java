package org.swordess.toy.effectivejava.chapter6.use_enum_instead_of_int.operation.impl2;


// Enum type with constant-specific method implementation
public enum Operation {
	PLUS   { double apply(double x, double y) { return x + y; } },
	MINUS  { double apply(double x, double y) { return x - y; } },
	TIMES  { double apply(double x, double y) { return x * y; } },
	DIVIDE { double apply(double x, double y) { return x / y; } };
	
	abstract double apply(double x, double y);
}
