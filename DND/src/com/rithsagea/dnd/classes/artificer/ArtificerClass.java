package com.rithsagea.dnd.classes.artificer;

import com.rithsagea.util.dice.Dice;

import api.rithsagea.dnd.types.classes.DndClass;
import api.rithsagea.dnd.types.classes.HitPointFeature;

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
//			addFeature(); //proficiencies
			break;
		
		}
	}

}
