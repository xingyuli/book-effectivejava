package org.swordess.toy.effectivejava.chapter4.use_function_object_as_strategy;

// Strategy interface
public interface Comparator<T> {
	public int compare(T t1, T t2);
}
