package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.DndSubclass;
import com.rithsagea.dnd.api.types.DndSubclassLevel;
import com.rithsagea.dnd.api.types.Feature;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eSubclass;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eSubclassLevel;

public class SubclassBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	private static SourceBook book;
	
	private static DndSubclass createSubclass(Dnd5eSubclass model) {
		DndSubclass subclass = new DndSubclass();
		
		subclass.id = model.id;
		subclass.name = model.name;
		subclass.flavor = model.flavor;
		subclass.description = model.description;
		subclass.classId = model.parentClass;
		
		subclass.levels = new ArrayList<>();
		subclass.levels.addAll(Collections.nCopies(20, null));
		
		return subclass;
	}
	
	private static DndSubclassLevel createLevel(Dnd5eSubclassLevel model) {
		DndSubclassLevel level = new DndSubclassLevel();
		level.id = model.id;
		level.features = model.features;
		level.level = model.level;
		level.subclassId = _subclassId;
		return level;
	}
	
	private static String _classId;
	private static String _subclassId;
	
	private static DndSubclassLevel createLevel(int lvl, Collection<String> features) {
		DndSubclassLevel level = new DndSubclassLevel();
		
		level.id = _subclassId + "-" + lvl;
		level.subclassId = _subclassId;
		level.level = lvl;
		level.features = new ArrayList<>(features);
		
		return level;
	}
	
	private static Feature createFeature(String id, String name, String description) {
		Feature f = new Feature();
		
		f.classId = _classId;
		f.subclassId = _subclassId;
		
		f.id = id;
		f.name = name;
		f.description = description;
		
		return f;
	}
	
	private static DndSubclass createBerserker() {
		Dnd5eSubclass model = data5e.DndSubclass.get("berserker");
		DndSubclass subclass = createSubclass(model);
		
		_subclassId = subclass.id;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null) {
				subclass.levels.set(x, createLevel(model.levels.get(x)));
			}
		}
		
		return subclass;
	}
	
	private static DndSubclass createTotemWarrior() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "totem-warrior";
		subclass.name = "Totem Warrior";
		subclass.flavor = "Primal Path";
		subclass.description = "The Path  of the Totem Warrior is a spiritual journey, as the barbarian accepts a spirit animal  as guide, protector, and inspiration. In battle, your totem spirit fills you with supernatural might, adding magical fuel to your barbarian rage. Most barbarian tribes consider a totem animal to be kin to a particular clan. In such cases, it is unusual for an individual to have more than one totem animal spirit, though exceptions exist.";
		subclass.classId = "barbarian";
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		DndSubclassLevel level;
		
		_classId = "barbarian";
		_subclassId = "totem-warrior";
		
		level = createLevel(3, Arrays.asList("spirit-seeker", "totem-spirit"));
		subclass.levels.set(2, level);
		
		book.register(createFeature("spirit-seeker", "Spirit Seeker",
				"Yours is a path that seeks attunement with the natural world, giving you a kinship with beasts. At 3rd level when you adopt this path, you gain the ability to cast the beast sense and speak with animals spells, but only as rituals."));
		book.register(createFeature("totem-spirit", "Totem Spirit",
				"At 3rd level, when you adopt this path, you choose a totem spirit and gain its feature. You must make or acquire a physical totem object-an amulet or similar adornment-that incorporates fur or feathers, claws, teeth, or bones of the totem animal. At your option, you also gain minor physical attributes that are reminiscent of your totem spirit. For example, if you have a bear totem spirit, you might be unusually hairy and thickskinned, or if your totem is the eagle, your eyes turn bright yellow.\n"
				+ "Your totem animal might be an animal related to those listed here but more appropriate to your homeland. For example, you could choose a hawk or vulture in place of an eagle.\n"
				+ "Bear. While raging, you have resistance to all damage except psychic damage. The spirit of the bear makes you tough enough to stand up to any punishment.\n"
				+ "Eagle. While you're raging and aren’t wearing heavy armor, other creatures have disadvantage on opportunity attack rolls against you, and you can use the Dash action as a bonus action on your turn. The spirit of the eagle makes you into a predator who can weave through the fray with ease.\n"
				+ "Wolf, While you're raging, your friends have advantage on melee attack rolls against any creature within 5 feet of you that is hostile to you. The spirit of the wolf makes you a leader of hunters."));
		
		level = createLevel(6, Arrays.asList("aspect-of-the-beast"));
		subclass.levels.set(5, level);
		
		book.register(createFeature("aspect-of-the-beast", "Aspect of the Beast",
				"At 6th level, you gain a magical benefit based on the totem animal of your choice. You can choose the same animal you selected at 3rd level or a different one.\n"
				+ "Bear. You gain the might of a bear. Your carrying capacity (including maximum load and maximum lift) is doubled, and you have advantage on Strength checks made to push, pull, lift, or break objects.\n"
				+ "Eagle. You gain the eyesight of an eagle. You can see up to 1 mile away with no difficulty, able to discern even fine details as though looking at something no more than 100 feet away from you. Additionally, dim light doesn't impose disadvantage on your Wisdom (Perception) checks.\n"
				+ "Wolf, You gain the hunting sensibilities of a wolf. You can track other creatures while traveling at a fast pace, and you can move stealthily while traveling at a normal pace."));
		
		level = createLevel(10, Arrays.asList("spirit-walker"));
		subclass.levels.set(9, level);
		
		book.register(createFeature("spirit-walker", "Spirit Walker",
				"At 10th level, you can cast the commune with nature spell, but only as a ritual. When you do so, a spiritual version of one of the animals you chose for Totem Spirit or Aspect of the Beast appears to you to convey the information you seek."));
		
		level = createLevel(14, Arrays.asList("totemic-attunement"));
		subclass.levels.set(13, level);
		
		book.register(createFeature("totemic-attunement", "Totemic Attunement",
				"At 14th level, you gain a magical benefit based on a totem animal of your choice. You can choose the same animal you selected previously or a different one.\n"
				+ "Bear. While you’re raging, any creature within 5 feet of you that’s hostile to you has disadvantage on attack rolls against targets other than you or another character with this feature. An enemy is immune to this effect if it can’t see or hear you or if it can’t be frightened.\n"
				+ "Eagle. While raging, you have a flying speed equal to your current walking speed. This benefit works only in short bursts; you fall if you end your turn in the air and nothing else is holding you aloft.\n"
				+ "Wolf. While you’re raging, you can use a bonus action on your turn to knock a Large or smaller creature prone when you hit it with melee weapon attack."));
		
		return subclass;
	}
	
	private static DndSubclass createLore() {
		Dnd5eSubclass model = data5e.DndSubclass.get("lore");
		DndSubclass subclass = createSubclass(model);
		
		_subclassId = subclass.id;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null) {
				subclass.levels.set(x, createLevel(model.levels.get(x)));
			}
		}
		
		Feature feature = SourceRegistry.getItem("bonus-proficiencies", Feature.class);
		if(feature != null) {
			feature.id = "lore-bonus-proficiencies";
			book.unregister("bonus-proficiencies", Feature.class);
			book.register(feature);
		}
		return subclass;
	}
	
	private static DndSubclass createValor() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "valor";
		subclass.name = "Valor";
		subclass.flavor = "Bard College";
		subclass.classId = "bard";
		subclass.description = StringUtil.convertDesc(
				"Bards o f the College of Valor are daring skalds whose\r\n"
				+ "tales keep alive the memory of the great heroes of the\r\n"
				+ "past, and thereby inspire a new generation of heroes.\r\n"
				+ "These bards gather in mead halls or around great\r\n"
				+ "bonfires to sing the deeds of the mighty, both past\r\n"
				+ "and present. They travel the land to witness great\r\n"
				+ "events firsthand and to ensure that the memory of\r\n"
				+ "those events doesn’t pass from the world. With their\r\n"
				+ "songs, they inspire others to reach the same heights of\r\n"
				+ "accomplishment as the heroes of old.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		DndSubclassLevel level;
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		level = createLevel(3, Arrays.asList("valor-bonus-proficiencies", "combat-inspiration"));
		subclass.levels.set(2, level);
		book.register(createFeature("valor-bonus-proficiencies", "Bonus Proficiencies", StringUtil.convertDesc(
				"When you join the College of Valor at 3rd level, you\r\n"
				+ "gain proficiency with medium armor, shields, and\r\n"
				+ "martial weapons.")));
		
		book.register(createFeature("combat-inspiration", "Combat Inspiration", StringUtil.convertDesc(
				"Also at 3rd level, you learn to inspire others in battle.\r\n"
				+ "A creature that has a Bardic Inspiration die from you\r\n"
				+ "can roll that die and add the number rolled to a weapon\r\n"
				+ "damage roll it just made. Alternatively, when an attack\r\n"
				+ "roll is made against the creature, it can use its reaction\r\n"
				+ "to roll the Bardic Inspiration die and add the number\r\n"
				+ "rolled to its AC against that attack, after seeing the roll\r\n"
				+ "but before knowing whether it hits or misses.")));
		
		level = createLevel(6, Arrays.asList("valor-extra-attack"));
		subclass.levels.set(5, level);
		book.register(createFeature("valor-extra-attack", "Extra Attack", StringUtil.convertDesc(
				"Starting at 6th level, you can attack twice, instead of\r\n"
				+ "once, whenever you take the Attack action on your turn.")));
		
		level = createLevel(14, Arrays.asList("battle-magic"));
		subclass.levels.set(13, level);
		book.register(createFeature("battle-magic", "Battle Magic", StringUtil.convertDesc(
				"At 14th level, you have mastered the art of weaving\r\n"
				+ "spellcasting and weapon use into a single harmonious\r\n"
				+ "act. When you use your action to cast a bard spell, you\r\n"
				+ "can make one weapon attack as a bonus action.")));
		
		return subclass;
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		book = SourceRegistry.getBooks().get("5e");
		System.out.println(data5e.DndSubclass.keySet().stream().sorted().collect(Collectors.toList()));
		
		book.register(createBerserker());
		book.register(createTotemWarrior());
		book.register(createLore());
		book.register(createValor());
		SourceRegistry.saveBooks();
	}
}
