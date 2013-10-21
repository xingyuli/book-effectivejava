package org.swordess.effectivejava.chapter10.tip2.impl2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.swordess.effectivejava.chapter4.composition_prior_inheritance.ForwardingSet;

public class ObservableSet<E> extends ForwardingSet<E> {

	private final List<SetObserver<E>> observers =
			new ArrayList<SetObserver<E>>();
	
	public ObservableSet(Set<E> s) {
		super(s);
	}

	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}
	
	public void removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.remove(observer);
		}
	}
	
	// Alien method moved outside of synchronized block - open calls
	private void notifyElementAdded(E element) {
		List<SetObserver<E>> snapshot = null;
		synchronized (observers) {
			snapshot = new ArrayList<SetObserver<E>>(observers);
		}
		for (SetObserver<E> observer : snapshot) {
			observer.added(this, element);
		}
	}
	
	@Override public boolean add(E element) {
		boolean added = super.add(element);
		if (added) {
			notifyElementAdded(element);
		}
		return added;
	}
	
	@Override public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c) {
			result |= add(element);	// calls notifyElementAdded
		}
		return result;
	}
	
}
