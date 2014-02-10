package org.swordess.toy.effectivejava.chapter5.typesafe_heterogenous_container;

import java.util.HashMap;
import java.util.Map;

// Typesafe heterogeneous container pattern - API
public class Favourites {

	private Map<Class<?>, Object> favourites =
			new HashMap<Class<?>, Object>();
	
	public <T> void putFavourite(Class<T> type, T instance) {
		if (type == null) {
			throw new NullPointerException("Type is null");
		}
		// achieving runtime type safety with a dynamic cast
		favourites.put(type, type.cast(instance));
	}
	
	public <T> T getFavourite(Class<T> type) {
		return type.cast(favourites.get(type));
	}
	
	// Typesafe heterogeneous container pattern - client 
	public static void main(String[] args) {
		Favourites f = new Favourites();
		f.putFavourite(String.class, "Java");
		f.putFavourite(Integer.class, 0xcafebabe);
		f.putFavourite(Class.class, Favourites.class);
		
		String favouriteString = f.getFavourite(String.class);
		int favouriteInteger = f.getFavourite(Integer.class);
		Class<?> favouriteClass = f.getFavourite(Class.class);
		System.out.printf("%s %x %s%n", favouriteString,
				favouriteInteger, favouriteClass.getName());
	}
	
}
