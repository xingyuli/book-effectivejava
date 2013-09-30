package org.swordess.effectivejava.chapter7;

import static junit.framework.Assert.*;
import static org.swordess.effectivejava.chapter7.WithCautionWhenVarargs.*;

import org.junit.Test;

public class WithCautionWhenVarargsTest {

	@Test
	public void sumWithNoArgs() {
		assertEquals(0, sum());
	}
	
	@Test(expected = NullPointerException.class)
	public void sumWithNull() {
		sum(null);
	}
	
}
