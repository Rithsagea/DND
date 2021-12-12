package api.rithsagea.dnd.character;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.event.EventBus;
import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;
import api.rithsagea.dnd.util.DataUtil;

public class CharacterSheet implements Listener {
	
	private EventBus eventBus;
	
	public CharacterSheet() {
		baseAbilityScores = DataUtil.generateDefaultMap(Ability.class, 0);
		
		abilityScores = DataUtil.generateDefaultMap(Ability.class, 0);
		abilityModifiers = DataUtil.generateDefaultMap(Ability.class, 0);
		savingThrows = DataUtil.generateDefaultMap(Ability.class, 0);
		skillModifiers = DataUtil.generateDefaultMap(Skill.class, 0);
		
		skillProficiencies = new TreeSet<>();
		savingProficiencies = new TreeSet<>();
		
		eventBus = new EventBus();
		eventBus.registerListener(this);
	}
	
	public void refreshSheet() {
		eventBus.clearListeners();
		
		calculateLevel();
		calculateRace();
		
		calculateAbilities();
	}
	
	// -=-=- Lore -=-=-
	
	private String name;
	private Alignment alignment;
	private boolean inspiration;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Alignment getAlignment() {
		return alignment;
	}
	
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	
	public boolean getInspiration() {
		return inspiration;
	}
	
	public void setInspiration(boolean inspiration) {
		this.inspiration = inspiration;
	}
	
	// -=-=- Misc -=-=-
	
	private static final int[] EXPERIENCE_TABLE = new int[] {
			-1, 0, 300, 900, 2700, 6500,
			14000, 23000, 34000, 48000, 64000,
			85000, 100000, 120000, 140000, 165000,
			195000, 225000, 265000, 305000, 355000
	};
	
	private int experience;
	private int level;
	
	private void calculateLevel() {
		for(level = 1; level < 20 && experience > EXPERIENCE_TABLE[level]; level++);
		
		proficiencyBonus = (int) (Math.ceil(level / 4d + 1));
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public int getLevel() {
		return level;
	}
	
	// -=-=- Ability Scores -=-=-
	
	private Map<Ability, Integer> baseAbilityScores;
	
	private Map<Ability, Integer> abilityScores;
	private Map<Ability, Integer> abilityModifiers;
	private Map<Ability, Integer> savingThrows;
	private Map<Skill, Integer> skillModifiers;
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	
	private int proficiencyBonus;
	private int passiveWisdom;
	private int initiative;
	
	private void calculateAbilities() {		
		skillProficiencies.clear();
		savingProficiencies.clear();
		
		for(Entry<Ability, Integer> entry : baseAbilityScores.entrySet()) {
			eventBus.submitEvent(new UpdateAbilityScoreEvent(this, entry.getKey(), entry.getValue()));
		}
		
		for(Ability ability : Ability.values()) {
			eventBus.submitEvent(new UpdateAbilityModifierEvent(this, ability, (abilityScores.get(ability) - 10) / 2));
		}
		
		for(Ability ability : Ability.values()) {
			eventBus.submitEvent(new UpdateSavingThrowEvent(this, ability, abilityModifiers.get(ability)));
		}
		
		for(Skill skill : Skill.values()) {
			eventBus.submitEvent(new UpdateSkillModifierEvent(this, skill, abilityModifiers.get(skill.getAbility())));
		}
		
		eventBus.submitEvent(new UpdatePassiveWisdomEvent(this, 10 + skillModifiers.get(Skill.PERCEPTION)));
		eventBus.submitEvent(new UpdateInitiativeEvent(this, abilityModifiers.get(Ability.DEXTERITY)));
	}
	
	protected void setAbilityScore(Ability ability, int value) {
		abilityScores.put(ability, value);
	}
	
	protected void setAbilityModifier(Ability ability, int value) {
		abilityModifiers.put(ability, value);
	}
	
	protected void setSavingThrow(Ability ability, int value) {
		savingThrows.put(ability, value);
	}
	
	protected void setSkillModifier(Skill skill, int value) {
		skillModifiers.put(skill, value);
	}
	
	protected void setPassiveWisdom(int value) {
		passiveWisdom = value;
	}
	
	protected void setInitiative(int value) {
		initiative = value;
	}
	
	public int getBaseAbilityScore(Ability ability) {
		return baseAbilityScores.get(ability);
	}
	
	public void setBaseAbilityScore(Ability ability, int value) {
		baseAbilityScores.put(ability, value);
	}
	
	public void setBaseAbilityScores(int strVal, int dexVal, int conVal, int intVal, int wisVal, int chaVal) {
		baseAbilityScores.put(Ability.STRENGTH, strVal);
		baseAbilityScores.put(Ability.DEXTERITY, dexVal);
		baseAbilityScores.put(Ability.CONSTITUTION, conVal);
		baseAbilityScores.put(Ability.INTELLIGENCE, intVal);
		baseAbilityScores.put(Ability.WISDOM, wisVal);
		baseAbilityScores.put(Ability.CHARISMA, chaVal);
	}
	
	public boolean hasProficiency(Skill skill) {
		return skillProficiencies.contains(skill);
	}
	
	public boolean hasProficiency(Ability ability) {
		return savingProficiencies.contains(ability);
	}
	
	public void addProficiency(Skill skill) {
		skillProficiencies.add(skill);
	}
	
	public void addProficiency(Ability ability) {
		savingProficiencies.add(ability);
	}
	
	public int getProficiencyBonus() {
		return proficiencyBonus;
	}
	
	public int getPassiveWisdom() {
		return passiveWisdom;
	}
	
	public int getInitiative() {
		return initiative;
	}

	// -=-=- Races -=-=-
	
	private DndRace race;
	
	private void calculateRace() {
		race.getTraits().forEach(this::addTrait);
	}
	
	public void setRace(DndRace race) {
		this.race = race;
	}
	
	public DndRace getRace() {
		return race;
	}
	
	// -=-=- Traits -=-=-
	
	private Set<Trait> traits = new TreeSet<Trait>();
	
	private void addTrait(Trait trait) {
		traits.add(trait);
		eventBus.registerListener(trait);
	}
	
	public Set<Trait> getTraits() {
		return Collections.unmodifiableSet(traits);
	}
}
