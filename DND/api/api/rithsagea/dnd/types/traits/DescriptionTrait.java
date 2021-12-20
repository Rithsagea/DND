package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.types.DndRace;

public class DescriptionTrait extends UniqueTrait {

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
		
		@Override
		public String toString() {
			return key;
		}
	}
	
	private DescriptionType type;
	
	public DescriptionTrait(DndRace race, DescriptionType type) {
		super(race);
		this.type = type;
	}
	
	public DescriptionType getType() {
		return type;
	}
	
	@Override
	public String getSubId() {
		return type.getKey();
	}
}
