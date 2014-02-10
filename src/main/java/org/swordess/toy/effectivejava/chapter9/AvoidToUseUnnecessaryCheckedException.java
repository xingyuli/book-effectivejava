package org.swordess.toy.effectivejava.chapter9;

import org.junit.Test;

public class AvoidToUseUnnecessaryCheckedException {

	@Test
	public void demo1() {
		try {
			method();
		} catch (TheCheckedExcpetion e) {
			throw new AssertionError(); // Can't happen!
		}
	}
	
	@Test
	public void demo2() {
		try {
			method();
		} catch (TheCheckedExcpetion e) {
			e.printStackTrace();		// Oh well, we lose.
			System.exit(1);
		}
	}
	
	@Test
	public void demo3() {
		Object[] args = null;
		MyClass obj = new MyClass();
		try {
			obj.action(args);
		} catch (TheCheckedExcpetion e) {
			// Handle exceptional condition
		}
	}
	
	@Test
	public void refactoredDemo3() {
		Object[] args = null;
		MyRefactoredClass obj = new MyRefactoredClass();
		if (obj.actionPermitted(args)) {
			obj.action(args);
		} else {
			// Handle exceptional condition
		}
	}
	
	@Test(expected = TheUncheckedExcpetion.class)
	public void ignoreExceptionOfRefactoredDemo3() {
		Object[] args = null;
		MyRefactoredClass obj = new MyRefactoredClass();
		obj.action(args);
	}
	
	private void method() throws TheCheckedExcpetion { }
	
}

class MyClass {

	void action(Object[] args) throws TheCheckedExcpetion {
		if (null == args) {
			throw new TheCheckedExcpetion("args should not be null");
		}
		System.out.println(args);
	}

}

class MyRefactoredClass {

	boolean actionPermitted(Object[] args) {
		return null != args;
	}

	void action(Object[] args) throws TheUncheckedExcpetion {
		if (null == args) {
			throw new TheUncheckedExcpetion("args should not be null");
		}
		System.out.println(args);
	}

}

@SuppressWarnings("serial")
class TheCheckedExcpetion extends Exception {
	TheCheckedExcpetion(String message) { super(message); }
}

@SuppressWarnings("serial")
class TheUncheckedExcpetion extends RuntimeException {
	TheUncheckedExcpetion(String message) { super(message); }
}
