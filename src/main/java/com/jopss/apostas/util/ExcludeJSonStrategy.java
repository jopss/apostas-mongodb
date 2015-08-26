package com.jopss.apostas.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Classe de suporte ao GSON para indicando alguma estrategia de transformacao dos atributos.
 * No caso, exclui os atributos que contenha a anotacao @ExcludeJSon.
 */
public class ExcludeJSonStrategy implements ExclusionStrategy {
	private final Class<?> typeToSkip;

	public ExcludeJSonStrategy() {
		this.typeToSkip = null;
	}

	public ExcludeJSonStrategy(Class<?> typeToSkip) {
		this.typeToSkip = typeToSkip;
	}

        @Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return (typeToSkip != null && clazz == typeToSkip);
	}

        @Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(ExcludeJSon.class) != null;
	}
}
