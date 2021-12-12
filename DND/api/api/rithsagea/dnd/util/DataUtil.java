package api.rithsagea.dnd.util;

import java.util.Map;
import java.util.TreeMap;

public class DataUtil {
	public static <K extends Enum<?>, V> Map<K, V> generateDefaultMap(Class<K> clazz, V defaultVal) {
		Map<K, V> map = new TreeMap<>();
		for(K k : clazz.getEnumConstants()) 
			map.put(k, defaultVal);
		return map;
	}
}
