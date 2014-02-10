package org.swordess.toy.effectivejava.chapter4;

public class HierarchyPriorTaggedClass {
}

// Tagged class - vastly inferior to a class hierarchy
class Figure {
	
	enum Shape { RECTANGLE, CIRCLE };
	
	// Tag field - the shape of this figure
	final Shape shape;

	// These fields are used only if shape is RECTANGLE
	double length;
	double width;
	
	// This field is used only if shape is CIRCLE
	double radius;
	
	// Constructor for circle
	Figure(double radius) {
		shape = Shape.CIRCLE;
		this.radius = radius;
	}
	
	// Constructor for rectangle
	Figure(double length, double width) {
		shape = Shape.RECTANGLE;
		this.length = length;
		this.width = width;
	}
	
	double area() {
		switch (shape) {
		case RECTANGLE:
			return length * width;
		case CIRCLE:
			return Math.PI * (radius * radius);
		default:
			throw new AssertionError();
		}
	}
	
}

abstract class FixedFigure {
	abstract double area();
}

class Circle extends FixedFigure {
	final double radius;
	
	Circle(double radius) { this.radius = radius; }
	
	double area() { return Math.PI * (radius * radius); }
}

class Rectangle extends FixedFigure {
	final double length;
	final double width;
	
	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	
	double area() { return length * width; }
}

class Square extends Rectangle {
	Square(double side) {
		super(side, side);
	}
}

