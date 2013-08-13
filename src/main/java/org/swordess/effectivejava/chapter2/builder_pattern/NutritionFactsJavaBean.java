package org.swordess.effectivejava.chapter2.builder_pattern;

public class NutritionFactsJavaBean {

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
