package api.rithsagea.dnd.character;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.rithsagea.util.DataUtil;
import com.rithsagea.util.event.EventBus;
import com.rithsagea.util.event.EventHandler;
import com.rithsagea.util.event.EventPriority;
import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.character.events.update.UpdateAbilityModifierEvent;
import api.rithsagea.dnd.character.events.update.UpdateAbilityScoreEvent;
import api.rithsagea.dnd.character.events.update.UpdateArmorClassEvent;
import api.rithsagea.dnd.character.events.update.UpdateInitiativeEvent;
import api.rithsagea.dnd.character.events.update.UpdatePassiveWisdomEvent;
import api.rithsagea.dnd.character.events.update.UpdateSavingProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateSavingThrowEvent;
import api.rithsagea.dnd.character.events.update.UpdateSkillModifierEvent;
import api.rithsagea.dnd.character.events.update.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.character.events.update.UpdateSpeedEvent;
import api.rithsagea.dnd.types.AbstractClass;
import api.rithsagea.dnd.types.AbstractRace;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Background;
import api.rithsagea.dnd.types.enums.Size;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.options.CharacterChoice;

public class CharacterSheet implements Listener {

	private EventBus eventBus;
	private Map<String, CharacterChoice> choices;
	
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
	
	private DndRace characterRace;
	private Set<CharacterClass> characterClasses; //multiclassing go here
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	
	private Map<Ability, Integer> baseAbilityScores;
	private Map<Ability, Integer> abilityScores;
	private Map<Ability, Integer> abilityModifiers;
	private Map<Ability, Integer> savingThrows;
	private Map<Skill, Integer> skillModifiers;
	
	private int passiveWisdom;
	private int initiative;
	private int armorClass;
	private int speed;
	
	public CharacterSheet() {
		characterClasses = new HashSet<>();
		
		skillProficiencies = new TreeSet<>();
		savingProficiencies = new TreeSet<>();
		
		baseAbilityScores = DataUtil.generateDefaultMap(Ability.class, 0);
		abilityScores = DataUtil.generateDefaultMap(Ability.class, 0);
		abilityModifiers = DataUtil.generateDefaultMap(Ability.class, 0);
		savingThrows = DataUtil.generateDefaultMap(Ability.class, 0);
		skillModifiers = DataUtil.generateDefaultMap(Skill.class, 0);
		
		choices = new TreeMap<>();
		
		eventBus = new EventBus();
		eventBus.registerListener(this);
		
		speed = 30; //TODO set this when configuring race instead
	}
	
	public void refresh() {
		calculateLevel();
		calculateProficiencies();
		calculateAbilityScores();
	}
	
	private void calculateLevel() {
		for(level = 1; level < 20 && experiencePoints > EXPERIENCE_TABLE[level]; level++);
		
		proficiencyBonus = (int) (Math.ceil(level / 4d + 1));
	}
	
	////////////////////////
	/// PROFICIENCY CALC ///
	////////////////////////
	
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
					abilityModifiers.get(ability)));
		}
		
		for(Skill skill : Skill.values()) {
			eventBus.submitEvent(new UpdateSkillModifierEvent(this, skill,
					abilityModifiers.get(skill.getAbility())));
		}
		
		eventBus.submitEvent(new UpdatePassiveWisdomEvent(this, 10 + skillModifiers.get(Skill.PERCEPTION)));
		eventBus.submitEvent(new UpdateInitiativeEvent(this, abilityModifiers.get(Ability.DEXTERITY)));
		eventBus.submitEvent(new UpdateArmorClassEvent(this, 10 + abilityModifiers.get(Ability.DEXTERITY)));
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

	//PROFILE
	
	public AbstractRace getRace() {
		return characterRace;
	}
	
	public void setRace(AbstractRace race) {
		
	}
	
	public Set<CharacterClass> getClasses() {
		return Collections.unmodifiableSet(characterClasses);
	}
	
	public CharacterClass getClassData(AbstractClass clazz) {
		for(CharacterClass c : characterClasses)
			if(c.getClassType().equals(clazz))
				return c;
		
		return null;
	}
	
	public void addClass(AbstractClass c) {
		characterClasses.add(new CharacterClass(c));
	}
	
	/**
	 * For convenience only
	 * @param c the class to remove
	 */
	public void removeClass(CharacterClass c) {
		characterClasses.remove(c);
	}

	//PROFICIENCIES
	
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

	//CHOICES (FOR BACKEND)
	public CharacterChoice getChoice(String key) {
		return choices.get(key);
	}
}
