package api.rithsagea.dnd.character;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.Dice;
import api.rithsagea.dnd.util.Dice.Die;

public class CharacterSheet {
	private String name;
	
	private Alignment alignment;
	
	private Set<Skill> skillProfs;
	
	private Map<Ability, Integer> abilityScores;
	private Map<Ability, Integer> abilityModifiers;
	private Map<Ability, Integer> savingThrows;
	private Map<Skill, Integer> skillModifiers;
	
	private int passiveWisdom;
	private int initiative;
	private boolean inspiration;
	
//	private Dice hitDice;
	private int hitPoints;
	private int maxHitPoints;
	
	public CharacterSheet() {
		abilityScores = new TreeMap<>();
		abilityModifiers = new TreeMap<>();
		savingThrows = new TreeMap<>();
		skillModifiers = new TreeMap<>();
		
		skillProfs = new TreeSet<>();
	}
	
	private void calcAbilityValues() {
		for(Ability ability : Ability.values()) {
			abilityModifiers.put(ability, (abilityScores.get(ability) - 10) / 2);
			savingThrows.put(ability, abilityModifiers.get(ability));
		}
		
		for(Skill skill : Skill.values()) {
			skillModifiers.put(skill, abilityModifiers.get(skill.getAbility()));
		}
		
		passiveWisdom = 10 + skillModifiers.get(Skill.PERCEPTION) + abilityModifiers.get(Ability.WISDOM);
		initiative = abilityModifiers.get(Ability.DEXTERITY);
	}
	
	/// ACCESSORS
	
	public String getName() {
		return name;
	}

	public Alignment getAlignment() {
		return alignment;
	}
	
	public boolean hasInspiration() {
		return inspiration;
	}
	
	public int getAbilityScore(Ability ability) {
		return abilityScores.get(ability);
	}
	
	public int getAbilityModifier(Ability ability) {
		return abilityModifiers.get(ability);
	}
	
	public int getSavingThrow(Ability ability) {
		return savingThrows.get(ability);
	}
	
	public int getSkillModifier(Skill skill) {
		return skillModifiers.get(skill);
	}
	
	public Set<Skill> getSkillProficiencies() {
		return Collections.unmodifiableSet(skillProfs);
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}
	
	/// MUTATORS
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	
	public void setInspiration(boolean inspiration) {
		this.inspiration = inspiration;
	}
	
	public void setAbilityScore(Ability ability, int value) {
		abilityScores.put(ability, value);
		
		calcAbilityValues();
	}

	public void setAbilityScores(int strVal, int dexVal, int conVal, int intVal, int wisVal, int chaVal) {
		abilityScores.put(Ability.STRENGTH, strVal);
		abilityScores.put(Ability.DEXTERITY, dexVal);
		abilityScores.put(Ability.CONSTITUTION, conVal);
		abilityScores.put(Ability.INTELLIGENCE, intVal);
		abilityScores.put(Ability.WISDOM, wisVal);
		abilityScores.put(Ability.CHARISMA, chaVal);
		
		calcAbilityValues();
	}
	
	public int getPassiveWisdom() {
		return passiveWisdom;
	}
	
	public int getInitiative() {
		return initiative;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = Math.min(maxHitPoints, hitPoints);
	}
	
	public void addSkillProficiency(Skill skill) {
		skillProfs.add(skill);
	}
	
	public void removeSkillProficiency(Skill skill) {
		skillProfs.remove(skill);
	}
}
