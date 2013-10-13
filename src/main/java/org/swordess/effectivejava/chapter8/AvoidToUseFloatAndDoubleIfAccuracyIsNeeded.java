package org.swordess.effectivejava.chapter8;

import java.math.BigDecimal;

import org.junit.Test;

public class AvoidToUseFloatAndDoubleIfAccuracyIsNeeded {
	
	@Test
	public void unexpectedResult1() {
		System.out.println(1.03 - 0.42);
	}

	@Test
	public void unexpectedResult2() {
		System.out.println(1.00 - 0.9);
	}
	
	@Test
	public void currencyUseDouble() {
		double funds = 1.00;
		int itemsBought = 0;
		for (double price = .10; funds >= price; price += .10) {
			funds -= price;
			itemsBought++;
		}
		System.out.println(itemsBought + " items bought");
		System.out.println("Change: $" + funds);
	}

	@Test
	public void currencyUseBigDecimal() {
		final BigDecimal TEN_CENTS = new BigDecimal(".10");
		
		BigDecimal funds = new BigDecimal("1.00");
		int itemsBought = 0;
		for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
			funds = funds.subtract(price);
			itemsBought++;
		}
		System.out.println(itemsBought + " items bought");
		System.out.println("Change: $" + funds);
	}
	
	@Test
	public void currencyUseInt() {
		int funds = 100;
		int itemsBought = 0;
		for (int price = 10; funds >= price; price += 10) {
			funds -= price;
			itemsBought++;
		}
		System.out.println(itemsBought + " items bought");
		System.out.println("Change: $" + funds);
	}
	
}
