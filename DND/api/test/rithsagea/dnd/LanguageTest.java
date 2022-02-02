package test.rithsagea.dnd;

import java.util.Arrays;

import api.rithsagea.dnd.types.LanguageManager;
import api.rithsagea.dnd.types.enums.Currency;

public class LanguageTest {
	public static void main(String[] args) {
		LanguageManager manager = LanguageManager.getInstance();
		Arrays.asList(Currency.values())
				.stream()
//				.sorted(new Comparator<Equipment>() {
//					@Override
//					public int compare(Equipment arg0, Equipment arg1) {
//						return arg0.getId().compareTo(arg1.getId());
//					}
//				})
				.forEach((x) -> {
					System.out.println(x);
					System.out.println(x.getUnitLabel());
				});
		System.out.println("-=-=- Missing -=-=-");
		manager.missing().forEach(System.out::println);
	}
}
