package legacy.rithsagea.dnd.api.types;

import java.util.List;
import java.util.Map;

public class Spell extends IndexedItem {
	public String name;
	public String description;
	public String higherDescription;
	public String range;
	public List<String> components;
	public String materials;
	
	public boolean ritual;
	public String duration;
	public boolean concentration;
	public String castingTime;
	public int level;
	public String attackType;
	
	public String damageType;
	public Map<String, String> damageValues;
	public String school;
}
