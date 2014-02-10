package org.swordess.toy.effectivejava.chapter7.with_caution_when_overload;

public class Overriding {

	public static void main(String[] args) {
		Wine[] wines = {
			new Wine(), new SparkingWine(), new Champagne()
		};
		for (Wine wine : wines) {
			System.out.println(wine.name());
		}
	}
	
}

class Wine {
	String name() { return "wine"; }
}

class SparkingWine extends Wine {
	@Override String name() { return "sparking wine"; }
}

class Champagne extends SparkingWine {
	@Override String name() { return "champagne"; }
}
