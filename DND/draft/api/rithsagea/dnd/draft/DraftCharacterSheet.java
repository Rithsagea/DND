package api.rithsagea.dnd.draft;

import java.util.Map;
import java.util.Set;

import com.rithsagea.util.event.EventBus;

import api.rithsagea.dnd.types.DndClass;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Background;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;

public class DraftCharacterSheet {

	private EventBus eventBus;
	
	private String name;
	private String playerName;
	
	private Background background;
	private Alignment alignment;
	private boolean inspiration;
	
	private int experiencePoints;
	private int level;
	private int proficiencyBonus;
	
	private DndRace characterRace;
	private DndClass characterClass;
	
	private Set<Skill> skillProficiencies;
	private Set<Ability> savingProficiencies;
	private Set<Equipment> equipmentProficiencies;	

	private Set<Trait> traits;
	
	private Map<Ability, Integer> baseAbilityScores;
	private Map<Ability, Integer> abilityScores;
	private Map<Ability, Integer> abilityModifiers;
	private Map<Ability, Integer> savingThrows;
	private Map<Skill, Integer> skillModifiers;
	
	private int passiveWisdom;
	private int initiative;
	private int speed;
}
