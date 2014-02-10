package org.swordess.toy.effectivejava.chapter10.tip2.impl2;



public interface SetObserver<E> {

	// Invoked when an element is added to the observable set
	public void added(ObservableSet<E> set, E element);
	
}
