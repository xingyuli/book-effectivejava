package org.swordess.effectivejava.chapter7;

class WithCautionWhenVarargs {

	static int sum(int... args) {
		int sum = 0;
		for (int arg : args) {
			sum += arg;
		}
		return sum;
	}
	
	// The WRONG way to use varargs to pass one or more arguments!
	@Deprecated
	static int min(int... args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("Too few arguments");
		}
		int min = args[0];
		for (int i = 1; i < args.length; i++) {
			if (args[i] < min) {
				min = args[i];
			}
		}
		return min;
	}
	
	static int min(int firstArg, int... remainingArgs) {
		int min = firstArg;
		for (int arg : remainingArgs) {
			if (arg < min) {
				min = arg;
			}
		}
		return min;
	}
	
	void foo() { }
	void foo(int a1) { }
	void foo(int a1, int a2) { }
	void foo(int a1, int a2, int a3) { }
	void foo(int a1, int a2, int a3, int... rest) { }
	
}
