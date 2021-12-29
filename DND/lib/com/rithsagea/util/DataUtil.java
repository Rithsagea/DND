package com.rithsagea.util;

import java.util.Map;
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
}
