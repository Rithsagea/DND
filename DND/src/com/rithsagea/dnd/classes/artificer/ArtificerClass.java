package com.rithsagea.dnd.classes.artificer;

import com.rithsagea.util.dice.Dice;

import api.rithsagea.dnd.types.classes.DndClass;
import api.rithsagea.dnd.types.classes.features.HitPointFeature;
import api.rithsagea.dnd.types.classes.features.ProficiencyFeature;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.choice.OptionItem;

public class ArtificerClass extends DndClass {

	public ArtificerClass(boolean multiclass) {
		super(multiclass);
	}
	
	public ArtificerClass() {
		super(false);
	}

	@Override
	public String getId() {
		return "Artificer";
	}

	@Override
	public void onLevelUp(int level) {
		switch(level) {
		
		case 1:
			addFeature(new HitPointFeature(this, new Dice(1, 8)));
			addFeature(new ProficiencyFeature(this, 
					Equipment.LIGHT_ARMOR, Equipment.MEDIUM_ARMOR, Equipment.SHIELDS,
					Equipment.SIMPLE_WEAPONS, Equipment.FIREARMS, // technically optional
					Equipment.THIEVES_TOOLS, Equipment.TINKERS_TOOLS, new OptionItem(1, Equipment.ARTISANS_TOOLS),
					Ability.CONSTITUTION, Ability.INTELLIGENCE,
					new OptionItem(2, Skill.ARCANA, Skill.HISTORY, Skill.INVESTIGATION,
							Skill.MEDICINE, Skill.NATURE, Skill.PERCEPTION, Skill.SLEIGHT_OF_HAND)
					));
			addFeature(new MagicalTinkeringFeature());
			
			//TODO add default inventory / gold
			break;
		
		}
	}

}
