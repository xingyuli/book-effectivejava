package org.swordess.effectivejava.chapter6.use_annotation_instead_of_naming_pattern;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;
		Class<?> testClass = Class.forName(args[0]);
		for (Method m : testClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					m.invoke(null);
					passed++;
				} catch (InvocationTargetException e) {
					Throwable exc = e.getCause();
					System.out.println(m + " failed: " + exc);
				} catch (Exception e) {
					System.out.println("INVALID @Test: " + m);
				}

			} else if (m.isAnnotationPresent(ExceptionTest.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("Test %s failed: no exception%n", m);
				} catch (InvocationTargetException e) {
					Throwable exc = e.getCause();
					Class<? extends Exception>[] excTypes =
							m.getAnnotation(ExceptionTest.class).value();
					int oldPassed = passed;
					for (Class<? extends Exception> excType : excTypes) {
						if (excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
					if (passed == oldPassed) {
						System.out.printf("Test %s failed: %s%n", m, exc);
					}
				} catch (Exception e) {
					System.out.println("INVALID @Test: " + m);
				}
			}
		}
		System.out.printf("Passed: %d, Failed: %d%n",
				passed, tests - passed);
	}
	
}
