package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.util.WordUtil;

public class DescriptionTrait extends Trait {

	private String name;
	private String desc;
	
	public DescriptionTrait(String name, Object... desc) {
		this.name = name;
		this.desc = WordUtil.createHyperlinkedText(desc);
	}
	
	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getName() {
		return name;
	}

}
