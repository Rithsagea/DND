package api.rithsagea.dnd.types.aspects.features;

import api.rithsagea.dnd.types.AbstractClass;
import api.rithsagea.dnd.types.aspects.UniqueFeature;

public class ProficiencyFeature extends UniqueFeature {

	public ProficiencyFeature(AbstractClass parentClass) {
		super(parentClass);
	}

	@Override
	public String getSubId() {
		return "Proficiency";
	}

}
