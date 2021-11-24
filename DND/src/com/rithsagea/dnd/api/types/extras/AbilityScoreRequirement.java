package com.rithsagea.dnd.api.types.extras;

public class AbilityScoreRequirement {
	
	public AbilityScoreRequirement(String abilityScore, int value) { this.abilityScore = abilityScore; this.value = value; }
	
	public String abilityScore;
	public int value;
	
	public String toString() { return abilityScore + ">" + value; }
}
