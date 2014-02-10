package org.swordess.toy.effectivejava.chapter6.use_enum_instead_of_int.operation.impl3;

public class Util {

	private Util() {
	}
	
	public static Operation inverse(Operation op) {
		switch(op) {
		case PLUS:   return Operation.MINUS;
		case MINUS:  return Operation.PLUS;
		case TIMES:  return Operation.DIVIDE;
		case DIVIDE: return Operation.TIMES;
		default: throw new AssertionError("Unknown op: " + op);
		}
	}
	
}
