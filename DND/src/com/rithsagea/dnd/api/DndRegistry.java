package com.rithsagea.dnd.api;

import java.io.File;
import java.util.List;

import com.rithsagea.dnd.api.misc.AbilityScore;
import com.rithsagea.dnd.api.misc.Alignment;
import com.rithsagea.dnd.api.misc.CharacterSize;
import com.rithsagea.dnd.api.misc.Language;

public class DndRegistry {
	
	public static final Registry<AbilityScore> AbilityScore = new Registry<>();
	public static final Registry<Alignment> Alignment = new Registry<>();
	public static final Registry<CharacterSize> CharacterSize = new Registry<>();
	public static final Registry<Language> Language = new Registry<>();
	
	public static void registerAbilityScores(List<AbilityScore> abilityScores) {
		for(AbilityScore abilityScore : abilityScores) {
			AbilityScore.register(abilityScore.id, abilityScore);
		}
	}
	
	public static void loadRegistry(File file) {
		
	}
	
	public static void saveRegistry(File file) {
		
	}
}
