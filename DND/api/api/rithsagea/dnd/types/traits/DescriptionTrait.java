package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.LanguageManager;

public class DescriptionTrait implements Trait {

	public static enum DescriptionType {
		AGE("Age"),
		ALIGNMENT("Alignment"),
		SIZE("Size");
		
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
	
	private String raceId;
	private DescriptionType type;
	
	public DescriptionTrait(String raceId, DescriptionType type) {
		this.raceId = raceId;
		this.type = type;
	}
	
	public String getRaceId() {
		return raceId;
	}
	
	public DescriptionType getType() {
		return type;
	}
	
	@Override
	public String getId() {
		return raceId + "." + type.getKey();
	}
	
	@Override
	public String getName() {
		return type.getKey();
	}

	@Override
	public String getDesc() {
		return LanguageManager.getInstance().get(this, KeyConstants.DESCRIPTION);
	}
	
}
