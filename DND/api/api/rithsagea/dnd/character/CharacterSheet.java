package api.rithsagea.dnd.character;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.rithsagea.util.event.EventBus;

import api.rithsagea.dnd.meta.Metadata;
import api.rithsagea.dnd.types.DndClass;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Background;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Size;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;

public class CharacterSheet {

	private EventBus eventBus;
	private Metadata metadata;
	
	private String name;
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
	private Set<DndClass> characterClasses; //multiclassing go here
	
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
	
	public CharacterSheet() {
		eventBus = new EventBus();
	}
	
	public void refresh() {
		calculateLevel();
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	private void calculateLevel() {
		for(level = 1; level < 20 && experiencePoints > EXPERIENCE_TABLE[level]; level++);
		
		proficiencyBonus = (int) (Math.ceil(level / 4d + 1));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public DndRace getRace() {
		return characterRace;
	}
	
	public void setRace(DndRace race) {
		
	}
	
	public Set<DndClass> getClasses() {
		return Collections.unmodifiableSet(characterClasses);
	}
	
	public void addClass(DndClass c) {
		characterClasses.add(c);
	}
	
	/**
	 * For convenience only
	 * @param c the class to remove
	 */
	public void removeClass(DndClass c) {
		characterClasses.remove(c);
	}
}
