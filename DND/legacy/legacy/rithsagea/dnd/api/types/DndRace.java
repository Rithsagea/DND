package legacy.rithsagea.dnd.api.types;

import java.util.List;
import java.util.Map;

import legacy.rithsagea.dnd.api.types.extras.Options;

public class DndRace extends IndexedItem {
	public String name;
	public String age;
	public String alignment;
	public String size;
	public String sizeDescription;
	
	public int speed;
	
	public Map<String, Integer> abilityBonuses;
	
	public List<String> proficiencies;
	public Options proficiencyOptions;
	
	public List<String> languages;
	public String languageDesc;
	
	public List<String> traits;
	
	public List<String> subraces;
}
