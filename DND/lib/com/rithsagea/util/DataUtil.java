package com.rithsagea.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;

public class DataUtil {
	/**
	 * Generate a map with all possible values of an enum with a set
	 * default value
	 * @param <K> the key type, must be an Enum
	 * @param <V> the default value type
	 * @param clazz the class of the key
	 * @param defaultVal the default value
	 * @return the map
	 */
	public static <K extends Enum<?>, V> Map<K, V> generateDefaultMap(Class<K> clazz, V defaultVal) {
		Map<K, V> map = new TreeMap<>();
		for(K k : clazz.getEnumConstants()) 
			map.put(k, defaultVal);
		return map;
	}
	
	/**
	 * Generate a map with all possible values of an enum, and a function
	 * to provide default values
	 * @param <K> the key type, must be an Enum
	 * @param <V> the default value type
	 * @param clazz the class of the key
	 * @param func the provider for default values
	 * @return the map
	 */
	public static <K extends Enum<?>, V> Map<K, V> generateDefaultMap(Class<K> clazz, Function<K, V> func) {
		Map<K, V> map = new TreeMap<>();
		for(K k : clazz.getEnumConstants())
			map.put(k, func.apply(k));
		return map;
	}
	
	/**
	 * Creates a new array from a collection of type T.
	 * WILL THROW EXCEPTION IF T IS A PRIMITIVE
	 * @param <T> the type of array to make
	 * @param col the collection to convert
	 * @return the array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<T> col) {
		return col.toArray((T[]) new Object[col.size()]);
	}
	
	/**
	 * Creates a new unmodifiable set from the provided elements
	 * @param <T> the type of set to make
	 * @param elements the elements to include
	 * @return the set
	 */
	@SafeVarargs
	public static <T> Set<T> set(T... elements) {
		return Collections.unmodifiableSet(new LinkedHashSet<T>(Arrays.asList(elements)));
	}
	
	/**
	 * Creates a new unmodifiable list from the provided elements
	 * @param <T> the type of list to make
	 * @param elements the elements to include
	 * @return the list
	 */
	@SafeVarargs
	public static <T> List<T> list(T... elements) {
		return Collections.unmodifiableList(Arrays.asList(elements));
	}
}
