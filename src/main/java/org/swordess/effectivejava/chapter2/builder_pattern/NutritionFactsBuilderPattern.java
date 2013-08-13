package org.swordess.effectivejava.chapter2.builder_pattern;


public class NutritionFactsBuilderPattern {

	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
	
	public static class Builder implements org.swordess.effectivejava.Builder<NutritionFactsBuilderPattern> {
		
		// Required parameters
		private final int servingSize;
		private final int servings;
		
		// Optional parameters - initialized to default values
		private int calories		= 0;
		private int fat				= 0;
		private int sodium			= 0;
		private int carbohydrate	= 0;
		
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		
		public Builder calories(int val)		{ calories = val; return this; }
		public Builder fat(int val)				{ fat = val; return this; }
		public Builder sodium(int val)			{ sodium = val; return this; }
		public Builder carbohydrate(int val)	{ carbohydrate = val; return this; }
		
		@Override
		public NutritionFactsBuilderPattern build() {
			return new NutritionFactsBuilderPattern(this);
		}
		
	}
	
	public NutritionFactsBuilderPattern(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
	
	public static void main(String[] args) {
		NutritionFactsBuilderPattern cocaCola = new NutritionFactsBuilderPattern.Builder(240, 8).
				calories(100).sodium(35).carbohydrate(27).build();
	}
	
}
