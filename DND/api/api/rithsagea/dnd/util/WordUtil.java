package api.rithsagea.dnd.util;

public class WordUtil {
	public static String formatId(String id) {
		return id.toLowerCase().replaceAll("[\\W_]", "-");
	}
}
