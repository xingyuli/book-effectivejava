package org.swordess.effectivejava.chapter5.typesafe_heterogenous_container;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class Util {

	private Util() {
	}
	
	static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
		Class<?> annotationType = null; // Unbounded type token
		try {
			annotationType = Class.forName(annotationTypeName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return element.getAnnotation(annotationType.asSubclass(Annotation.class));
	}
	
}
