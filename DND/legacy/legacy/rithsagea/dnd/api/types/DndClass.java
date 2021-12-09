package legacy.rithsagea.dnd.api.types;

import java.util.List;

import legacy.rithsagea.dnd.api.types.extras.ItemStack;
import legacy.rithsagea.dnd.api.types.extras.MulticlassingInfo;
import legacy.rithsagea.dnd.api.types.extras.Options;
import legacy.rithsagea.dnd.api.types.extras.SpellcastingInfo;

public class DndClass extends IndexedItem {
	public String name;
	public int hitDie;
	public List<Options> proficiencyOptions;
	public List<String> proficiencies;
	public List<String> savingThrows;
	
	public List<ItemStack> equipment;
	public List<Options> equipmentOptions;
	
	public SpellcastingInfo spellcasting;
	public List<String> spells;
	
	public List<DndClassLevel> levels;
	
	public List<String> subclasses;
	
	public MulticlassingInfo multiclassing;
}
