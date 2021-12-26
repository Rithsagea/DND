package api.rithsagea.dnd.features;

import api.rithsagea.dnd.types.DndClass;

public class ProficiencyFeature extends UniqueFeature {

	public ProficiencyFeature(DndClass parent) {
		super(parent);
	}

	@Override
	public String getSubId() {
		return "Proficiency";
	}
}
