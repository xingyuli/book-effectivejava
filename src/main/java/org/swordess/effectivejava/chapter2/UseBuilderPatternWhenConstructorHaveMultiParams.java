package org.swordess.effectivejava.chapter2;

public class UseBuilderPatternWhenConstructorHaveMultiParams {

	public static class NutritionFacts {
	
		private final int servingSize;	// (mL)				required
		private final int servings;		// (per container)	required
		private final int calories;		// 					optional
		private final int fat;			// (g)				optional
		private final int sodium;		// (mg)				optional
		private final int carbohydrate; // (g)				optional
		
		public NutritionFacts(int servingSize, int servings) {
			this(servingSize, servings, 0);
		}
		
		public NutritionFacts(int servingSize, int servings,
				int calories) {
			this(servingSize, servings, calories, 0);
		}
		
		public NutritionFacts(int servingSize, int servings,
				int calories, int fat) {
			this(servingSize, servings, calories, fat, 0);
		}
		
		public NutritionFacts(int servingSize, int servings,
				int calories, int fat, int sodium) {
			this(servingSize, servings, calories, fat, sodium, 0);
		}
		
		public NutritionFacts(int servingSize, int servings,
				int calories, int fat, int sodium, int carbohydrate) {
			this.servingSize = servingSize;
			this.servings = servings;
			this.calories = calories;
			this.fat = fat;
			this.sodium = sodium;
			this.carbohydrate = carbohydrate;
		}
		
		public static void main(String[] args) {
			NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
		}
		
	}
	
	public static class NutritionFactsJavaBean {

		// Parameters initialized to default values (if any)
		private int servingSize = -1;	// Required; no default value
		private int servings = -1;		// Required; no default value
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbohydrate = 0;

		public NutritionFactsJavaBean() { }
		
		// Setters
		public void setServingSize(int val)		{ this.servingSize = val; }
		public void setServings(int val) 		{ this.servings = val; }
		public void setCalories(int val) 		{ this.calories = val; }
		public void setFat(int val) 			{ this.fat = val; }
		public void setSodium(int val) 			{ this.sodium = val; }
		public void setCarbohydrate(int val) 	{ this.carbohydrate = val; }

		public static void main(String[] args) {
			NutritionFactsJavaBean cocaCola = new NutritionFactsJavaBean();
			cocaCola.setServingSize(240);
			cocaCola.setServings(8);
			cocaCola.setCalories(100);
			cocaCola.setSodium(35);
			cocaCola.setCarbohydrate(27);
		}

	}
	
	public static class NutritionFactsBuilderPattern {

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
	
}
