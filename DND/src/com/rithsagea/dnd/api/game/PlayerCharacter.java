package com.rithsagea.dnd.api.game;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.CharacterSheetTemplate;
import com.rithsagea.dnd.api.types.extras.Sense;

public class PlayerCharacter implements GameCharacter {

	private PlayerCharacterSheet sheet;
	
	public PlayerCharacter(PlayerCharacterSheet sheet) {
		this.sheet = sheet;
	}
	
	public PlayerCharacterSheet getCharacterSheet() {
		return sheet;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getArmorClass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHitPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHitDie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getModifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getSavingThrows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getSkills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sense getSenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> getActions() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
