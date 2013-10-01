package org.swordess.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.phase.impl1;

// Using ordinal() to index array of arrays - DON'T DO THIS!
public enum Phase {

	SOLID, LIQUID, GAS;
	
	public enum Transition {
		
		MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
		
		private static final Transition[][] TRANSITIONS = {
			{ null,    MELT,     SUBLIME },
			{ FREEZE,  null,     BOIL    },
			{ DEPOSIT, CONDENSE, null    }
		};
		
		// Returns the phase transition from one phase to another
		public static Transition from(Phase src, Phase dst) {
			return TRANSITIONS[src.ordinal()][dst.ordinal()];
		}
		
	}
	
}
