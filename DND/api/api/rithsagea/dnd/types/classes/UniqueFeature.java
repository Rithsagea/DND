package api.rithsagea.dnd.types.classes;

import api.rithsagea.dnd.types.LanguageManager;

public abstract class UniqueFeature extends BaseFeature {
	public abstract String getSubId();
	
	private AbstractClass parent;
	
	public UniqueFeature(AbstractClass parent) {
		this.parent = parent;
	}
	
	public AbstractClass getParent() {
		return parent;
	}
	
	@Override
	public String getId() {
		return parent.getId() + "." + getSubId();
	}
	
	@Override
	public String getName() {
		return LanguageManager.getInstance().get("Feature." + getSubId() + ".Name");
	}
}
