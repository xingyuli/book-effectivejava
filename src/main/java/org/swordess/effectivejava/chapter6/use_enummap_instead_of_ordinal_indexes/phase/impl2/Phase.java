package org.swordess.effectivejava.chapter6.use_enummap_instead_of_ordinal_indexes.phase.impl2;

import java.util.EnumMap;
import java.util.Map;

// Using a nested EnumMap to associate data with enum pairs
public enum Phase {

	SOLID, LIQUID, GAS, PLASMA;
	
	public enum Transition {
		
		MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
		BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
		SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
		IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);
		
		private final Phase src;
		private final Phase dst;
		
		private Transition(Phase src, Phase dst) {
			this.src = src;
			this.dst = dst;
		}
		
		// Initialize the phase transition map
		private static final Map<Phase, Map<Phase, Transition>> m =
				new EnumMap<Phase, Map<Phase,Transition>>(Phase.class);
		static {
			for (Phase p : Phase.values()) {
				m.put(p, new EnumMap<Phase, Phase.Transition>(Phase.class));
			}
			for (Transition trans : Transition.values()) {
				m.get(trans.src).put(trans.dst, trans);
			}
		}
		
		public static Transition from(Phase src, Phase dst) {
			return m.get(src).get(dst);
		}
		
	}
	
}
