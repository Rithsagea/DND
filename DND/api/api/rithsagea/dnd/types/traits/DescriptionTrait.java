package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.types.DndRace;

public class DescriptionTrait extends Trait {

	public static enum DescriptionType {
		AGE("Age"),
		ALIGNMENT("Alignment"),
		SIZE("Size"),
		LANGUAGE("Language");
		
		private final String key;
		
		private DescriptionType(String key) {
			this.key = key;
		}
		
		public String getKey() {
			return key;
		}
		
		public String toString() {
			return key;
		}
	}
	
	private DndRace race;
	private DescriptionType type;
	
	public DescriptionTrait(DndRace race, DescriptionType type) {
		this.race = race;
		this.type = type;
	}
	
	public DndRace getRace() {
		return race;
	}
	
	public DescriptionType getType() {
		return type;
	}
	
	@Override
	public String getId() {
		return race.getId() + "." + type.getKey();
	}
	
	@Override
	public String getName() {
		return type.getKey();
	}
}
