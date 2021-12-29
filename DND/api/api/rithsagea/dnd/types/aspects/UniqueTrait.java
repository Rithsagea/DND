package api.rithsagea.dnd.types.aspects;

import api.rithsagea.dnd.types.AbstractRace;
import api.rithsagea.dnd.types.LanguageManager;

public abstract class UniqueTrait extends Trait {

	private AbstractRace parentRace;
	
	public UniqueTrait(AbstractRace parentRace) {
		this.parentRace = parentRace;
	}
	
	public AbstractRace getParentRace() {
		return parentRace;
	}
	
	public abstract String getSubId();
	
	@Override
	public String getId() {
		return String.format("Trait.%s.%s", parentRace.getId(), getSubId());
	}
	
	@Override
	public String getName() {
		return LanguageManager.getInstance().get(String.format("Trait.Unique.%s.Name", getSubId()));
	}
}
