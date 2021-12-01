package com.rithsagea.dnd.api.game;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.extras.Sense;

public interface GameCharacter {
	public String getName();
	
	public int getArmorClass();
	
	public int getHitPoints();
	public String getHitDie();
	
	public Map<String, Integer> getModifiers();
	public Map<String, Integer> getSavingThrows();
	public Map<String, Integer> getSkills();
	
	public Sense getSenses();
	
	public List<Action> getActions();
}
