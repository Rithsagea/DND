package com.rithsagea.dnd.classes.artificer;

import com.rithsagea.util.dice.Dice;

import api.rithsagea.dnd.types.DndClass;

public class ArtificerClass extends DndClass {

	public ArtificerClass() {
		super("Artificer", new Dice(1, 8));
	}
}
