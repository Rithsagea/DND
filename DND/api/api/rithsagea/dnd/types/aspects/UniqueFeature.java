package api.rithsagea.dnd.types.aspects;

import api.rithsagea.dnd.types.AbstractClass;
import api.rithsagea.dnd.types.LanguageManager;

public abstract class UniqueFeature extends Feature {
	
	private AbstractClass parentClass;
	
	public UniqueFeature(AbstractClass parentClass) {
		this.parentClass = parentClass;
	}
	
	public AbstractClass getParentClass() {
		return parentClass;
	}
	
	public abstract String getSubId();
	
	@Override
	public String getId() {
		return String.format("Feature.%s.%s", parentClass.getId(), getSubId());
	}
	
	@Override
	public String getName() {
		return LanguageManager.getInstance().get(String.format("Feature.Unique.%s.Name", getSubId()));
	}
}
