package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.LanguageManager;
import api.rithsagea.dnd.util.WordUtil;

public enum DamageType implements IndexedItem {
	ACID,
	BLUDGEONING,
	COLD,
	FIRE,
	FORCE,
	LIGHTNING,
	NECROTIC,
	PIERCING,
	POISON,
	PSYCHIC,
	RADIANT,
	SLASHING,
	THUNDER;

	public String getDescription() {
		return LanguageManager.getInstance().get(this, KeyConstants.DESCRIPTION);
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
	
	@Override
	public String toString() {
		return getName() + ". " + getDescription();
	}
}
