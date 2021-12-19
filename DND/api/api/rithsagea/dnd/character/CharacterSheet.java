package api.rithsagea.dnd.character;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.character.UpdateAbilityEvent.UpdateAbilityModifierEvent;
import api.rithsagea.dnd.character.UpdateAbilityEvent.UpdateAbilityScoreEvent;
import api.rithsagea.dnd.character.UpdateAbilityEvent.UpdateSavingThrowEvent;
import api.rithsagea.dnd.character.UpdateAbilityEvent.UpdateSkillModifierEvent;
import api.rithsagea.dnd.character.UpdateFieldEvent.UpdateInitiativeEvent;
import api.rithsagea.dnd.character.UpdateFieldEvent.UpdatePassiveWisdomEvent;
import api.rithsagea.dnd.character.UpdateFieldEvent.UpdateSpeedEvent;
import api.rithsagea.dnd.character.UpdateProficiencyEvent.UpdateSavingProficiencyEvent;
import api.rithsagea.dnd.character.UpdateProficiencyEvent.UpdateEquipmentProficiencyEvent;
import api.rithsagea.dnd.character.UpdateProficiencyEvent.UpdateSkillProficiencyEvent;
import api.rithsagea.dnd.event.EventBus;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.event.EventPriority;
import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.EquipmentProficiency;
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
		equipmentProficiencies = new TreeSet<>();
		
		eventBus = new EventBus();
		eventBus.registerListener(this);
	}
	
	public void refreshSheet() {
		calculateLevel();
		calculateProficiencies();
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
	
	// -=-=- Leveling -=-=-
	
	private static final int[] EXPERIENCE_TABLE = new int[] {
			-1, 0, 300, 900, 2700, 6500,
			14000, 23000, 34000, 48000, 64000,
			85000, 100000, 120000, 140000, 165000,
			195000, 225000, 265000, 305000, 355000
	};
	
	private int experience;
	private int level;
	private int proficiencyBonus;
	
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
	
	// -=-=- Proficiencies -=-=-
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	private Set<EquipmentProficiency> equipmentProficiencies;	

	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateProficiency(UpdateProficiencyEvent<?> e) {
		if(e.getProficiencies().isEmpty()) return;
		Enum<?> first = e.getProficiencies().iterator().next();
		
		if(first instanceof Ability) {
			((Set<Ability>) e.getProficiencies()).forEach(this::addProficiency);
		}
		
		if(first instanceof Skill) {
			((Set<Skill>) e.getProficiencies()).forEach(this::addProficiency);
		}
		
		if(first instanceof EquipmentProficiency) {
			((Set<EquipmentProficiency>) e.getProficiencies()).forEach(this::addProficiency);
		}
	}
	
	private void calculateProficiencies() {
		skillProficiencies.clear();
		savingProficiencies.clear();
		equipmentProficiencies.clear();
		
		eventBus.submitEvent(new UpdateSkillProficiencyEvent(this));
		eventBus.submitEvent(new UpdateSavingProficiencyEvent(this));
		eventBus.submitEvent(new UpdateEquipmentProficiencyEvent(this));
	}
	
	public boolean hasProficiency(Skill skill) {
		return skillProficiencies.contains(skill);
	}
	
	public boolean hasProficiency(Ability ability) {
		return savingProficiencies.contains(ability);
	}
	
	public boolean hasProficiency(EquipmentProficiency equipment) {
		return equipmentProficiencies.contains(equipment);
	}
	
	public void addProficiency(Skill skill) {
		skillProficiencies.add(skill);
	}
	
	public void addProficiency(Ability ability) {
		savingProficiencies.add(ability);
	}
	
	public void addProficiency(EquipmentProficiency equipment) {
		equipmentProficiencies.add(equipment);
	}
	
	// -=-=- Ability Scores -=-=-
	
	private Map<Ability, Integer> baseAbilityScores;
	
	private Map<Ability, Integer> abilityScores;
	private Map<Ability, Integer> abilityModifiers;
	private Map<Ability, Integer> savingThrows;
	private Map<Skill, Integer> skillModifiers;
	
	private int passiveWisdom;
	private int initiative;
	private int speed;
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateAbility(UpdateAbilityEvent e) {
		if(e instanceof UpdateAbilityScoreEvent) {
			abilityScores.put(e.getAbility(), e.getValue());
		}
		
		if(e instanceof UpdateAbilityModifierEvent) {
			abilityModifiers.put(e.getAbility(), e.getValue());
		}
		
		if(e instanceof UpdateSavingThrowEvent) {
			savingThrows.put(e.getAbility(), e.getValue()
					+ (hasProficiency(e.getAbility()) ? proficiencyBonus : 0));
		}
		
		if(e instanceof UpdateSkillModifierEvent) {
			Skill s = ((UpdateSkillModifierEvent) e).getSkill();
			skillModifiers.put(s, e.getValue()
					+ (hasProficiency(s) ? proficiencyBonus : 0));
		}
	}
	
	@EventHandler(priority = EventPriority.ROOT)
	public void onUpdateField(UpdateFieldEvent e) {
		if(e instanceof UpdatePassiveWisdomEvent) {
			passiveWisdom = e.getValue();
		}
		
		if(e instanceof UpdateInitiativeEvent) {
			initiative = e.getValue();
		}
		
		if(e instanceof UpdateSpeedEvent) {
			speed = e.getValue();
		}
	}
	
	private void calculateAbilities() {		
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
		eventBus.submitEvent(new UpdateSpeedEvent(this, 30));
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
	
	public int getProficiencyBonus() {
		return proficiencyBonus;
	}
	
	public int getPassiveWisdom() {
		return passiveWisdom;
	}
	
	public int getInitiative() {
		return initiative;
	}

	public int getSpeed() {
		return speed;
	}
	
	// -=-=- Misc -=-=-
	
	
	// -=-=- Races -=-=-
	
	private DndRace race;
	
	public void setRace(DndRace race) {
		if(this.race != null) {
			this.race.getTraits().forEach(this::removeTrait);
			eventBus.unregisterListener(this.race);
		}
			
		this.race = race;
		eventBus.registerListener(race);
		race.getTraits().forEach(this::addTrait);
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
	
	private void removeTrait(Trait trait) {
		traits.remove(trait);
		eventBus.unregisterListener(trait);
	}
	
	public Set<Trait> getTraits() {
		return Collections.unmodifiableSet(traits);
	}
}
