package com.rithsagea.dnd.api;

import com.google.gson.annotations.SerializedName;
import com.rithsagea.dnd.api.data.AbilityScore;
import com.rithsagea.dnd.api.data.Alignment;
import com.rithsagea.dnd.api.data.Language;
import com.rithsagea.dnd.api.data.Proficiency;
import com.rithsagea.dnd.api.data.Skill;

public class ExpansionRegistry {
	
	@SerializedName("ability_scores")
	public Registry<AbilityScore> AbilityScore;
	
	@SerializedName("skills")
	public Registry<Skill> Skill;
	
	@SerializedName("proficiencies")
	public Registry<Proficiency> Proficiency;
	
	@SerializedName("languages")
	public Registry<Language> Language;
	
	@SerializedName("alignments")
	public Registry<Alignment> Alignment;
	
	public ExpansionRegistry() {
		
	}
}
