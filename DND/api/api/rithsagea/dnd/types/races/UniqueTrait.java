package api.rithsagea.dnd.types.races;

import api.rithsagea.dnd.types.LanguageManager;

public abstract class UniqueTrait extends BaseTrait {
	public abstract String getSubId();
	
	private AbstractRace parent;
	
	public UniqueTrait(AbstractRace parent) {
		this.parent = parent;
	}
	
	public AbstractRace getParent() {
		return parent;
	}
	
	@Override
	public String getId() {
		return parent.getId() + "." + getSubId();
	}
	
	@Override
	public String getName() {
		return LanguageManager.getInstance().get("Trait." + getSubId() + ".Name");
	}
}
