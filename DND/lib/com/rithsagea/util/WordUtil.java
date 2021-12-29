package api.rithsagea.dnd.util;

public class WordUtil {
	
	/**
	 * Capitalizes the word, only setting the first character to upper case
	 * @param word the word to capitalize
	 * @return the capitalized word
	 */
	public static String capitalize(String word) {
		return Character.toUpperCase(word.charAt(0)) + word.substring(1);
	}
	
	/**
	 * Capitalizes the entire word, making the first character upper case
	 * and any later characters lower case
	 * @param word the word to capitalize
	 * @return the fully capitalized word
	 */
	public static String capitalizeFully(String word) {
		return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	}
	
	public static String formatId(String id) {
		StringBuilder builder = new StringBuilder();
		for(String token : id.toLowerCase().split("[\\W_]"))
			builder.append(capitalizeFully(token));
		return builder.toString();
	}
}
