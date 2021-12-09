package api.rithsagea.dnd.types.traits;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.util.WordUtil;

public class AbilityScoreTrait extends Trait {
	
	private Map<Ability, Integer> bonusMap;
	private String desc;
	
	public AbilityScoreTrait(Map<Ability, Integer> bonuses) {
		bonusMap = new TreeMap<>(bonuses);
		
		//building the description
		//fluid language go brrrrrr
		Map<Integer, Set<Ability>> temp = new TreeMap<>(Comparator.reverseOrder());
		for(Entry<Ability, Integer> entry : bonusMap.entrySet()) {
			if(!temp.containsKey(entry.getValue())) temp.put(entry.getValue(), new TreeSet<>());
			temp.get(entry.getValue()).add(entry.getKey());
		}
		
		List<String> list1 = new ArrayList<>();
		String prefix = "Your ";
		for(int bonus : temp.keySet()) {
			StringBuilder builder = new StringBuilder();
			builder.append(prefix);
			prefix = "your ";
			
			Set<Ability> abilities = temp.get(bonus);
			List<String> list2 = new ArrayList<>();
			
			for(Ability ability : abilities) {
				list2.add(WordUtil.createHyperlink(ability));
			}
			
			builder.append(WordUtil.commaList(list2));
			builder.append(abilities.size() > 1 ? " scores each" : " score");
			builder.append(" increase by ");
			builder.append(bonus);
			
			list1.add(builder.toString());
		}
		
		desc = WordUtil.commaList(list1) + ".";
	}
	
	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getName() {
		return "Ability Score Increase";
	}
}
