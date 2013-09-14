package org.swordess.effectivejava.chapter6.use_enum_instead_of_int.operation.impl3;

public class Main {

	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		for (Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f%n",
					x, op, y, op.apply(x, y));
		}
	}
	
}
