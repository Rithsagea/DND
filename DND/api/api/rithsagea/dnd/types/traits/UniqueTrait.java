package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.util.LanguageManager;

public abstract class UniqueTrait extends Trait {
	private DndRace race;
	
	public UniqueTrait(DndRace race) {
		this.race = race;
	}
	
	public DndRace getRace() {
		return race;
	}
	
	public abstract String getSubId();
	
	@Override
	public String getName() {
		return LanguageManager.getInstance().get("Trait." + getSubId() + ".Name");
	}
	
	@Override
	public String getId() {
		return getRace().getId() + "." + getSubId();
	}
}
