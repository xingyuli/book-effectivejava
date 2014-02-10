package org.swordess.toy.effectivejava.chapter10.caution_when_lazy_initialization;

import static junit.framework.Assert.assertNotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.swordess.toy.effectivejava.chapter10.concurrent_prior_wait_notify.CountDownLatchDemo;

public class Demonstration {

	// Normal initialization of an instance field
	private final FieldType field1 = computeFieldValue();

	// Lazy initialization of instance field - synchronized accessor
	private FieldType field2;
	synchronized FieldType getField2() {
		if (null == field2) {
			field2 = computeFieldValue();
		}
		return field2;
	}
	
	// Lazy initialization holder class idiom for static fields
	private static class FieldHolder {
		static final FieldType field = computeFieldValue();
	}
	
	// Double-check idiom for lazy initialization of instance fields
	private volatile FieldType field;
	FieldType getField() {
		FieldType result = field;
		if (result == null) {	// First check (no locking)
			synchronized (this) {
				result = field;
				if (result == null) {	// Second check (with locking)
					result = field = computeFieldValue();
				}
			}
		}
		return result;
	}
	
	private volatile FieldType fieldWithoutUsingLocalVariable;
	FieldType getFieldWithoutUsingLocalVariable() {
		if (fieldWithoutUsingLocalVariable == null) {
			synchronized (this) {
				if (fieldWithoutUsingLocalVariable == null) {
					fieldWithoutUsingLocalVariable = computeFieldValue();
				}
			}
		}
		return fieldWithoutUsingLocalVariable;
	}
	
	@Test
	public void normalInitialization() {
		assertNotNull(field1);
	}
	
	@Test
	public void lazyInitializationUsingSynchronizedAccessor() {
		assertNotNull(getField2());
	}
	
	@Test
	public void lazyInitializationHolderClassIdiom() {
		assertNotNull(FieldHolder.field);
	}
	
	@Test
	public void lazyInitializationDoucleCheckIdiom() {
		assertNotNull(getField());
	}
	
	/**
	 * According to the testing result on my machine, I found the performance of these
	 * two approaches are almost the same.
	 */
	@Test
	public void testPerformanceOfDoubleCheckIdiomWithAndWithoutUsingLocalVariables() {
		Runnable accessField = new Runnable() {
			public void run() {
				getField();
			}
		};
		Runnable accessFieldWithoutUsingLocalVariable = new Runnable() {
			public void run() {
				getFieldWithoutUsingLocalVariable();
			}
		};
		
		long nanoTimeWhenAccessField = 0L;
		long nanoTimeWhenAccessFieldWithoutUsingLocalVariable = 0L;

		int concurrency = 200;
		int times = 200;
		Executor executor = Executors.newFixedThreadPool(concurrency);
		try {
			for (int i = 0; i < times; i++) {
				nanoTimeWhenAccessField +=
						CountDownLatchDemo.time(executor, concurrency, accessField);
			}
			for (int i = 0; i < times; i++) {
				nanoTimeWhenAccessFieldWithoutUsingLocalVariable +=
						CountDownLatchDemo.time(executor, concurrency, accessFieldWithoutUsingLocalVariable);
			}
		} catch (InterruptedException e) {
		}

		System.out.println("accessField: " + nanoTimeWhenAccessField);
		System.out.println("accessFieldWithoutUsingLocalVariable: " +
				nanoTimeWhenAccessFieldWithoutUsingLocalVariable);
		
		System.out.println((nanoTimeWhenAccessFieldWithoutUsingLocalVariable - nanoTimeWhenAccessField + 0.0f) / nanoTimeWhenAccessField);
	}

	private static FieldType computeFieldValue() {
		return new FieldType("java.lang.String");
	}
	
}

class FieldType {
	String type;
	FieldType(String type) { this.type = type; }
	@Override public String toString() { return type; }
}
