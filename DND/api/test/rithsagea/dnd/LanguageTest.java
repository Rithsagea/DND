package test.rithsagea.dnd;

import java.util.Arrays;

import api.rithsagea.dnd.types.enums.Condition;
import api.rithsagea.dnd.util.LanguageManager;

public class LanguageTest {
	public static void main(String[] args) {
		LanguageManager manager = LanguageManager.getInstance();
		Arrays.asList(Condition.values()).forEach((x) -> System.out.println(x.getName()));
		System.out.println("-=-=- Missing -=-=-");
		manager.missing().forEach(System.out::println);
	}
}
