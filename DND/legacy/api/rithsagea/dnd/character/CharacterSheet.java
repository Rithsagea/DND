package api.rithsagea.dnd.character;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.rithsagea.util.DataUtil;
import com.rithsagea.util.dice.Roll;
import com.rithsagea.util.event.EventBus;
import com.rithsagea.util.event.EventHandler;
import com.rithsagea.util.event.EventPriority;
import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.character.events.update.UpdateAbilityModifierEvent;
import api.rithsagea.dnd.character.events.update.UpdateAbilityScoreEvent;
import api.rithsagea.dnd.character.events.update.UpdateArmorClassEvent;
import api.rithsagea.dnd.character.events.update.UpdateEquipmentProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateInitiativeEvent;
import api.rithsagea.dnd.character.events.update.UpdateMaxHitPointEvent;
import api.rithsagea.dnd.character.events.update.UpdatePassiveWisdomEvent;
import api.rithsagea.dnd.character.events.update.UpdateSavingProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateSavingThrowEvent;
import api.rithsagea.dnd.character.events.update.UpdateSkillModifierEvent;
import api.rithsagea.dnd.character.events.update.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateSpeedEvent;
import api.rithsagea.dnd.types.classes.AbstractClass;
import api.rithsagea.dnd.types.classes.Feature;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Background;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Size;
import api.rithsagea.dnd.types.enums.Skill;

public class CharacterSheet implements Listener {

	private EventBus eventBus;
	
	private String characterName;
	private String playerName;
	
	private Background background;
	private Alignment alignment;
	private boolean inspiration;
	
	private int age;
	private int height;
	private int weight;
	private Size size;
	
	private static final int[] EXPERIENCE_TABLE = new int[] {
			-1, 0, 300, 900, 2700, 6500,
			14000, 23000, 34000, 48000, 64000,
			85000, 100000, 120000, 140000, 165000,
			195000, 225000, 265000, 305000, 355000
	};
	
	private int experiencePoints;
	private int level;
	private int proficiencyBonus;
	
	private AbstractClass characterClass;
	private Set<Feature> features;
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	private Set<Equipment> equipmentProficiencies;
	
	private Map<Ability, Integer> baseAbilityScores;
	private Map<Ability, Integer> abilityScores;
	private Map<Ability, Integer> abilityModifiers;
	private Map<Ability, Integer> savingThrows;
	private Map<Skill, Integer> skillModifiers;
	
	private int hitPoints;
	private int maxHitPoints;
	private Roll hitDice;
	
	private int passiveWisdom;
	private int initiative;
	private int armorClass;
	private int speed;
	
	public CharacterSheet() {
		features = new HashSet<>();
		
		equipmentProficiencies = new TreeSet<>();
		skillProficiencies = new TreeSet<>();
		savingProficiencies = new TreeSet<>();
		
		baseAbilityScores = DataUtil.generateDefaultMap(Ability.class, 0);
		abilityScores = DataUtil.generateDefaultMap(Ability.class, 0);
		abilityModifiers = DataUtil.generateDefaultMap(Ability.class, 0);
		savingThrows = DataUtil.generateDefaultMap(Ability.class, 0);
		skillModifiers = DataUtil.generateDefaultMap(Skill.class, 0);
		
		eventBus = new EventBus();
		eventBus.registerListener(this);
	}
	
	public void refresh() {
		calculateLevel();
		calculateProfile();
		calculateProficiencies();
		calculateAbilityScores();
		calculateHitPoints();
	}
	
	private void calculateLevel() {
		for(level = 1; level < 20 && experiencePoints > EXPERIENCE_TABLE[level]; level++);
		
		proficiencyBonus = (int) (Math.ceil(level / 4d + 1));
	}
	
	/////////////////////////
	/// CLASS & RACE CALC ///
	/////////////////////////
	
	private void calculateProfile() {
		characterClass.getFeatures().forEach(this::addFeature);
		//TODO MULTICLASSING
	}
	
	////////////////////////
	/// PROFICIENCY CALC ///
	////////////////////////
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateEquipmentProficiency(UpdateEquipmentProficiencyEvent e) {
		equipmentProficiencies.clear();
		equipmentProficiencies.addAll(e.getProficiencies());
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateSavingProficiency(UpdateSavingProficiencyEvent e) {
		savingProficiencies.clear();
		savingProficiencies.addAll(e.getProficiencies());
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateSkillProficiency(UpdateSkillProficiencyEvent e) {
		skillProficiencies.clear();
		skillProficiencies.addAll(e.getProficiencies());
	}
	
	private void calculateProficiencies() {
		eventBus.submitEvent(new UpdateEquipmentProficiencyEvent(this));
		eventBus.submitEvent(new UpdateSavingProficiencyEvent(this));
		eventBus.submitEvent(new UpdateSkillProficiencyEvent(this));
	}
	
	//////////////////////////
	/// ABILITY SCORE CALC ///
	//////////////////////////
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateAbilityScore(UpdateAbilityScoreEvent e) {
		abilityScores.put(e.getAbility(), e.getValue());
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateAbilityModifier(UpdateAbilityModifierEvent e) {
		abilityModifiers.put(e.getAbility(), e.getValue());
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateSavingThrow(UpdateSavingThrowEvent e) {
		savingThrows.put(e.getAbility(), e.getValue());
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateSkillModifier(UpdateSkillModifierEvent e) {
		skillModifiers.put(e.getSkill(), e.getValue());
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdatePassiveWisdom(UpdatePassiveWisdomEvent e) {
		passiveWisdom = e.getValue();
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateInitiative(UpdateInitiativeEvent e) {
		initiative = e.getValue();
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateArmorClass(UpdateArmorClassEvent e) {
		armorClass = e.getValue();
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateSpeed(UpdateSpeedEvent e) {
		speed = e.getValue();
	}
	
	private void calculateAbilityScores() {
		for(Ability ability : Ability.values()) {
			eventBus.submitEvent(new UpdateAbilityScoreEvent(this, ability, 
					baseAbilityScores.get(ability)));
		}
		
		for(Ability ability : Ability.values()) {
			eventBus.submitEvent(new UpdateAbilityModifierEvent(this, ability,
					(abilityScores.get(ability) - 10) / 2));
		}
		
		for(Ability ability : Ability.values()) {
			eventBus.submitEvent(new UpdateSavingThrowEvent(this, ability,
					abilityModifiers.get(ability) + (hasSavingProficiency(ability) ? proficiencyBonus : 0)));
		}
		
		for(Skill skill : Skill.values()) {
			eventBus.submitEvent(new UpdateSkillModifierEvent(this, skill,
					abilityModifiers.get(skill.getAbility()) + (hasSkillProficiency(skill) ? proficiencyBonus : 0)));
		}
		
		eventBus.submitEvent(new UpdatePassiveWisdomEvent(this, 10 + skillModifiers.get(Skill.PERCEPTION)));
		eventBus.submitEvent(new UpdateInitiativeEvent(this, abilityModifiers.get(Ability.DEXTERITY)));
		eventBus.submitEvent(new UpdateArmorClassEvent(this, 10 + abilityModifiers.get(Ability.DEXTERITY)));
	}
	
	///////////////////////
	/// HIT POINTS CALC ///
	///////////////////////
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateMaxHitPoint(UpdateMaxHitPointEvent e) {
		hitDice = e.getHitDice();
		maxHitPoints = e.getValue();
		setHitPoints(hitPoints); // refresh hp cap
	}
	
	private void calculateHitPoints() {
		eventBus.submitEvent(new UpdateMaxHitPointEvent(this, 0));
	}
	
	//////////////////////////////
	/// MUTATORS AND ACCESSORS ///
	//////////////////////////////
	
	//OVERVIEW
	
	public String getCharacterName() {
		return characterName;
	}
	
	public void setName(String name) {
		this.characterName = name;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Background getBackground() {
		return background;
	}
	
	public void setBackground(Background background) {
		this.background = background;
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
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	//LEVEL
	
	public void setExperience(int experience) {
		this.experiencePoints = experience;
	}
	
	public int getExperience() {
		return experiencePoints;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getProficiencyBonus() {
		return proficiencyBonus;
	}

	//PROFILE (CLASSES & RACES)
	
	public void setCharacterClass(AbstractClass c) {
		removeClass(characterClass);
		characterClass = c;
		
		addClass(characterClass);
	}
	
	private void addClass(AbstractClass c) {
		eventBus.registerListener(c);
		c.getFeatures().forEach(this::addFeature);
	}
	
	private void removeClass(AbstractClass c) {
		if(c != null) {
			eventBus.unregisterListener(c);
			c.getFeatures().forEach(this::removeFeature);
		}
	}
	
	private void addFeature(Feature f) {
		eventBus.registerListener(f);
		features.add(f);
	}
	
	private void removeFeature(Feature f) {
		if(f != null) {
			eventBus.unregisterListener(f);
		}
	}
	
	public AbstractClass getCharacterClass() {
		return characterClass;
	}
	
	public Set<Feature> getFeatures() {
		return Collections.unmodifiableSet(features);
	}
	
	//PROFICIENCIES
	
	public boolean hasEquipmentProficiency(Equipment equipment) {
		return equipmentProficiencies.contains(equipment);
	}
	
	public Set<Equipment> getEquipmentProficiencies() {
		return Collections.unmodifiableSet(equipmentProficiencies);
	}
	
	public boolean hasSkillProficiency(Skill skill) {
		return skillProficiencies.contains(skill);
	}
	
	public boolean hasSavingProficiency(Ability ability) {
		return savingProficiencies.contains(ability);
	}
	
	//ABILITY SCORES
	
	public void setBaseAbilityScore(Ability ability, int val) {
		baseAbilityScores.put(ability, val);
	}
	
	public void setBaseAbilityScores(int strVal, int dexVal, int conVal,
									int intVal, int wisVal, int chaVal) {
		setBaseAbilityScore(Ability.STRENGTH, strVal);
		setBaseAbilityScore(Ability.DEXTERITY, dexVal);
		setBaseAbilityScore(Ability.CONSTITUTION, conVal);
		setBaseAbilityScore(Ability.INTELLIGENCE, intVal);
		setBaseAbilityScore(Ability.WISDOM, wisVal);
		setBaseAbilityScore(Ability.CHARISMA, chaVal);
	}
	
	public int getBaseAbilityScore(Ability ability) {
		return baseAbilityScores.get(ability);
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

	//HIT POINTS
	
	public int getHitPoints() {
		return hitPoints;
	}
	
	public void setHitPoints(int hitPoints) {
		this.hitPoints = Math.min(maxHitPoints, hitPoints);
	}
	
	public Roll getHitDice() {
		return hitDice.clone();
	}
	
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
	
	//MISC VALUES
	
	public int getPassiveWisdom() {
		return passiveWisdom;
	}
	
	public int getInitiative() {
		return initiative;
	}
	
	public int getArmorClass() {
		return armorClass;
	}
	
	public int getSpeed() {
		return speed;
	}

	//BACKEND
	
	public EventBus getEventBus() {
		return eventBus;
	}
}
