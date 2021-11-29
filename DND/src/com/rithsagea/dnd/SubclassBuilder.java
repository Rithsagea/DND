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
	
	private static void createLevel(DndSubclass subclass, int lvl, Collection<String> features) {
		DndSubclassLevel level = new DndSubclassLevel();
		
		level.id = _subclassId + "-" + lvl;
		level.subclassId = _subclassId;
		level.level = lvl;
		level.features = new ArrayList<>(features);
		
		subclass.levels.set(lvl - 1, level);
	}
	
	private static void createLevel(DndSubclass subclass, int lvl, String... features) {
		createLevel(subclass, lvl, Arrays.asList(features));
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
	
	private static void registerFeature(String id, String name, String description) {
		book.register(createFeature(id, name, StringUtil.convertDesc(description)));
	}
	
	private static DndSubclass createBerserker() {
		Dnd5eSubclass model = data5e.DndSubclass.get("berserker");
		DndSubclass subclass = createSubclass(model);
		
		_subclassId = subclass.id;
		_classId = subclass.classId;
		
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
		
		_classId = "barbarian";
		_subclassId = "totem-warrior";
		
		createLevel(subclass, 3, Arrays.asList("spirit-seeker", "totem-spirit"));
		book.register(createFeature("spirit-seeker", "Spirit Seeker",
				"Yours is a path that seeks attunement with the natural world, giving you a kinship with beasts. At 3rd level when you adopt this path, you gain the ability to cast the beast sense and speak with animals spells, but only as rituals."));
		book.register(createFeature("totem-spirit", "Totem Spirit",
				"At 3rd level, when you adopt this path, you choose a totem spirit and gain its feature. You must make or acquire a physical totem object-an amulet or similar adornment-that incorporates fur or feathers, claws, teeth, or bones of the totem animal. At your option, you also gain minor physical attributes that are reminiscent of your totem spirit. For example, if you have a bear totem spirit, you might be unusually hairy and thickskinned, or if your totem is the eagle, your eyes turn bright yellow.\n"
				+ "Your totem animal might be an animal related to those listed here but more appropriate to your homeland. For example, you could choose a hawk or vulture in place of an eagle.\n"
				+ "Bear. While raging, you have resistance to all damage except psychic damage. The spirit of the bear makes you tough enough to stand up to any punishment.\n"
				+ "Eagle. While you're raging and aren�t wearing heavy armor, other creatures have disadvantage on opportunity attack rolls against you, and you can use the Dash action as a bonus action on your turn. The spirit of the eagle makes you into a predator who can weave through the fray with ease.\n"
				+ "Wolf, While you're raging, your friends have advantage on melee attack rolls against any creature within 5 feet of you that is hostile to you. The spirit of the wolf makes you a leader of hunters."));
		
		createLevel(subclass, 6, Arrays.asList("aspect-of-the-beast"));
		book.register(createFeature("aspect-of-the-beast", "Aspect of the Beast",
				"At 6th level, you gain a magical benefit based on the totem animal of your choice. You can choose the same animal you selected at 3rd level or a different one.\n"
				+ "Bear. You gain the might of a bear. Your carrying capacity (including maximum load and maximum lift) is doubled, and you have advantage on Strength checks made to push, pull, lift, or break objects.\n"
				+ "Eagle. You gain the eyesight of an eagle. You can see up to 1 mile away with no difficulty, able to discern even fine details as though looking at something no more than 100 feet away from you. Additionally, dim light doesn't impose disadvantage on your Wisdom (Perception) checks.\n"
				+ "Wolf, You gain the hunting sensibilities of a wolf. You can track other creatures while traveling at a fast pace, and you can move stealthily while traveling at a normal pace."));
		
		createLevel(subclass, 10, Arrays.asList("spirit-walker"));
		book.register(createFeature("spirit-walker", "Spirit Walker",
				"At 10th level, you can cast the commune with nature spell, but only as a ritual. When you do so, a spiritual version of one of the animals you chose for Totem Spirit or Aspect of the Beast appears to you to convey the information you seek."));
		
		createLevel(subclass, 14, Arrays.asList("totemic-attunement"));
		book.register(createFeature("totemic-attunement", "Totemic Attunement",
				"At 14th level, you gain a magical benefit based on a totem animal of your choice. You can choose the same animal you selected previously or a different one.\n"
				+ "Bear. While you�re raging, any creature within 5 feet of you that�s hostile to you has disadvantage on attack rolls against targets other than you or another character with this feature. An enemy is immune to this effect if it can�t see or hear you or if it can�t be frightened.\n"
				+ "Eagle. While raging, you have a flying speed equal to your current walking speed. This benefit works only in short bursts; you fall if you end your turn in the air and nothing else is holding you aloft.\n"
				+ "Wolf. While you�re raging, you can use a bonus action on your turn to knock a Large or smaller creature prone when you hit it with melee weapon attack."));
		
		return subclass;
	}
	
	private static DndSubclass createLore() {
		Dnd5eSubclass model = data5e.DndSubclass.get("lore");
		DndSubclass subclass = createSubclass(model);
		
		_subclassId = subclass.id;
		_classId = subclass.classId;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null) {
				subclass.levels.set(x, createLevel(model.levels.get(x)));
			}
		}
		
		subclass.levels.get(2).features.set(0, "lore-bonus-proficiencies");
		
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
				"Bards of the College of Valor are daring skalds whose\r\n"
				+ "tales keep alive the memory of the great heroes of the\r\n"
				+ "past, and thereby inspire a new generation of heroes.\r\n"
				+ "These bards gather in mead halls or around great\r\n"
				+ "bonfires to sing the deeds of the mighty, both past\r\n"
				+ "and present. They travel the land to witness great\r\n"
				+ "events firsthand and to ensure that the memory of\r\n"
				+ "those events doesn�t pass from the world. With their\r\n"
				+ "songs, they inspire others to reach the same heights of\r\n"
				+ "accomplishment as the heroes of old.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 3, Arrays.asList("valor-bonus-proficiencies", "combat-inspiration"));
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
		
		createLevel(subclass, 6, Arrays.asList("valor-extra-attack"));
		book.register(createFeature("valor-extra-attack", "Extra Attack", StringUtil.convertDesc(
				"Starting at 6th level, you can attack twice, instead of\r\n"
				+ "once, whenever you take the Attack action on your turn.")));
		
		createLevel(subclass, 14, Arrays.asList("battle-magic"));
		book.register(createFeature("battle-magic", "Battle Magic", StringUtil.convertDesc(
				"At 14th level, you have mastered the art of weaving\r\n"
				+ "spellcasting and weapon use into a single harmonious\r\n"
				+ "act. When you use your action to cast a bard spell, you\r\n"
				+ "can make one weapon attack as a bonus action.")));
		
		return subclass;
	}
	
	//TODO add spells
	private static DndSubclass createLife() {
		Dnd5eSubclass model = data5e.DndSubclass.get("life");
		DndSubclass subclass = createSubclass(model);
		
		_subclassId = subclass.id;
		_classId = subclass.classId;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null) {
				subclass.levels.set(x, createLevel(model.levels.get(x)));
			}
		}
		
		subclass.levels.get(0).features.set(0, "life-bonus-proficiency");
		
		Feature feature = SourceRegistry.getItem("bonus-proficiency", Feature.class);
		if(feature != null) {
			feature.id = "life-bonus-proficiency";
			book.unregister("bonus-proficiency", Feature.class);
			book.register(feature);
		}
		
		subclass.levels.get(7).features.set(0, "life-divine-strike");
		feature = SourceRegistry.getItem("divine-strike", Feature.class);
		if(feature != null) {
			feature.id = "life-divine-strike";
			book.unregister("divine-strike", Feature.class);
			book.register(feature);
		}
		
		return subclass;
	}
	
	private static DndSubclass createKnowledge() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "knowledge";
		subclass.name = "Knowledge";
		subclass.flavor = "Divine Domain";
		subclass.classId = "cleric";
		subclass.description = StringUtil.convertDesc(
				"The gods of knowledge�including Oghma, Boccob,\r\n"
				+ "Gilean, Aureon, and Thoth�value learning and\r\n"
				+ "understanding above all. Some teach that knowledge is\r\n"
				+ "to be gathered and shared in libraries and universities,\r\n"
				+ "or promote the practical knowledge of craft and\r\n"
				+ "invention. Some deities hoard knowledge and keep its\r\n"
				+ "secrets to themselves. And some promise their followers\r\n"
				+ "that they will gain tremendous power if they unlock the\r\n"
				+ "secrets of the multiverse. Followers of these gods study\r\n"
				+ "esoteric lore, collect old tomes, delve into the secret\r\n"
				+ "places of the earth, and learn all they can. Some gods\r\n"
				+ "of knowledge promote the practical knowledge of craft\r\n"
				+ "and invention, including smith deities like Gond, Reorx,\r\n"
				+ "Onatar, Moradin, Hephaestus, and Goibhniu.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 1, Arrays.asList("blessings-of-knowledge"));
		book.register(createFeature("blessings-of-knowledge", "Blessings of Knowledge", StringUtil.convertDesc(
				"At 1st level, you learn two languages of your choice.\r\n"
				+ "You also become proficient in your choice of two of the\r\n"
				+ "following skills: Arcana, History, Nature, or Religion.\r\n"
				+ "\n"
				+ "Your proficiency bonus is doubled for any ability check\r\n"
				+ "you make that uses either of those skills.")));
		
		createLevel(subclass, 2, Arrays.asList("channel-divinity-knowledge-of-the-ages"));
		book.register(createFeature("channel-divinity-knowledge-of-the-ages", "Channel Divinity: Knowledge of the Ages", StringUtil.convertDesc(
				"Starting at 2nd level, you can use your Channel Divinity\r\n"
				+ "to tap into a divine well of knowledge. As an action,\r\n"
				+ "you choose one skill or tool. For 10 minutes, you have\r\n"
				+ "proficiency with the chosen skill or tool.")));
		
		createLevel(subclass, 6, Arrays.asList("channel-divinity-read-thoughts"));
		book.register(createFeature("channel-divinity-read-thoughts", "Channel Divinity: Read Thoughts", StringUtil.convertDesc(
				"At 6th level, you can use your Channel Divinity to read a\r\n"
				+ "creature�s thoughts. You can then use your access to the\r\n"
				+ "creature�s mind to command it.\r\n"
				+ "\n"
				+ "As an action, choose one creature that you can see\r\n"
				+ "within 60 feet of you. That creature must make a\r\n"
				+ "Wisdom saving throw. If the creature succeeds on the\r\n"
				+ "saving throw, you can�t use this feature on it again until\r\n"
				+ "you finish a long rest.\r\n"
				+ "\n"
				+ "If the creature fails its save, you can read its surface\r\n"
				+ "thoughts (those foremost in its mind, reflecting its\r\n"
				+ "current emotions and what it is actively thinking\r\n"
				+ "about) when it is within 60 feet of you. This effect lasts\r\n"
				+ "for 1 minute.\r\n"
				+ "\n"
				+ "During that time, you can use your action to end this\r\n"
				+ "effect and cast the suggestion spell on the creature\r\n"
				+ "without expending a spell slot. The target automatically\r\n"
				+ "fails its saving throw against the spell.")));
		
		createLevel(subclass, 8, Arrays.asList("potent-spellcasting"));
		book.register(createFeature("potent-spellcasting", "Potent Spellcasting", StringUtil.convertDesc(
				"Starting at 8th level, you add your Wisdom modifier to\r\n"
				+ "the damage you deal with any cleric cantrip.")));
		
		createLevel(subclass, 17, Arrays.asList("visions-of-the-past"));
		book.register(createFeature("visions-of-the-past", "Visions of the Past", StringUtil.convertDesc(
				"Starting at 17th level, you can call up visions of the past that relate to an object you hold or your immediate surroundings. You spend at least 1 minute in meditation and prayer, then receive dreamlike, shadowy glimpses of recent events. You can meditate in this way for a number of minutes equal to your Wisdom score and must maintain concentration during that time, as if you were casting a spell.\r\n"
				+ "\r\n"
				+ "Once you use this feature, you can't use it again until you finish a short or long rest.\r\n"
				+ "\r\n"
				+ "Object Reading. Holding an object as you meditate, you can see visions of the object's previous owner. After meditating for 1 minute, you learn how the owner acquired and lost the object, as well as the most recent significant event involving the object and that owner. If the object was owned by another creature in the recent past (within a number of days equal to your Wisdom score), you can spend 1 additional minute for each owner to learn the same information about that creature.\r\n"
				+ "\r\n"
				+ "Area Reading. As you meditate, you see visions of recent events in your immediate vicinity (a room, street, tunnel, clearing, or the like, up to a 50-foot cube), going back a number of days equal to your Wisdom score. For each minute you meditate, you learn about one significant event, beginning with the most recent. Significant events typically involve powerful emotions, such as battles and betrayals, marriages and murders, births and funerals. However, they might also include more mundane events that are nevertheless important in your current situation.")));
		
		return subclass;
	}
	
	private static DndSubclass createLight() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "light";
		subclass.name = "Light";
		subclass.flavor = "Divine Domain";
		subclass.classId = "cleric";
		subclass.description = StringUtil.convertDesc(
				"Gods of light � including Helm, Lathander, Pholtus, Branchala, the Silver Flame, Belenus, Apollo, and Re-Horakhty � promote the ideals of rebirth and renewal, truth, vigilance, and beauty, often using the symbol of the sun. Some of these gods are portrayed as the sun itself or as a charioteer who guides the sun across the sky. Others are tireless sentinels whose eyes pierce every shadow and see through every deception. Some are deities of beauty and artistry, who teach that art is a vehicle for the soul's improvement. Clerics of a god of light are enlightened souls infused with radiance and the power of their gods' discerning vision, charged with chasing away lies and burning away darkness.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 1, Arrays.asList("light-bonus-cantrip", "warding-flare"));
		book.register(createFeature("light-bonus-cantrip", "Bonus Cantrip", StringUtil.convertDesc(
				"When you choose this domain at 1st level, you gain the Light cantrip if you don't already know it.")));
		book.register(createFeature("warding-flare", "Warding Flare", StringUtil.convertDesc(
				"Also at 1st level, you can interpose divine light between yourself and an attacking enemy. When you are attacked by a creature within 30 feet of you that you can see, you can use your reaction to impose disadvantage on the attack roll, causing light to flare before the attacker before it hits or misses. An attacker that can't be blinded is immune to this feature.\r\n"
				+ "\r\n"
				+ "You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.")));
		
		createLevel(subclass, 2, Arrays.asList("channel-divinity-radiance-of-the-dawn"));
		book.register(createFeature("channel-divinity-radiance-of-the-dawn", "Channel Divinity: Radiance of the Dawn", StringUtil.convertDesc(
				"Starting at 2nd level, you can use your Channel Divinity to harness sunlight, banishing darkness and dealing radiant damage to your foes.\r\n"
				+ "\r\n"
				+ "As an action, you present your holy symbol, and any magical darkness within 30 feet of you is dispelled. Additionally, each hostile creature within 30 feet of you must make a Constitution saving throw. A creature takes radiant damage equal to 2d10 + your cleric level on a failed saving throw, and half as much damage on a successful one. A creature that has total cover from you is not affected.")));
		
		createLevel(subclass, 6, Arrays.asList("improved-flare"));
		book.register(createFeature("improved-flare", "Improved Flare", StringUtil.convertDesc(
				"Starting at 6th level, you can also use your Warding Flare feature when a creature that you can see within 30 feet of you attacks a creature other than you.")));
		
		createLevel(subclass, 8, "potent-spellcasting");
		book.register(createFeature("potent-spellcasting", "Potent Spellcasting", StringUtil.convertDesc(
				"Starting at 8th level, you add your Wisdom modifier to the damage you deal with any cleric cantrip.")));
		
		createLevel(subclass, 17, "corona-of-light");
		book.register(createFeature("corona-of-light", "Corona of Light", StringUtil.convertDesc(
				"Starting at 17th level, you can use your action to activate an aura of sunlight that lasts for 1 minute or until you dismiss it using another action. You emit bright light in a 60-foot radius and dim light 30 feet beyond that. Your enemies in the bright light have disadvantage on saving throws against any spell that deals fire or radiant damage.")));
		
		return subclass;
	}
	
	private static DndSubclass createNature() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "nature";
		subclass.name = "Nature";
		subclass.flavor = "Divine Domain";
		subclass.classId = "cleric";
		subclass.description = StringUtil.convertDesc("Gods of nature are as varied as the natural world itself; from inscrutable gods of the deep forests (such as Silvanus, Obad-Hai, Chislev, Balinor, and Pan) to friendly deities associated with particular springs and groves (such as Eldath). Druids revere nature as a whole and might serve one of these deities, practicing mysterious rites and reciting all-but-forgotten prayers in their own secret tongue. But many of these gods have clerics as well, champions who take a more active role in advancing the interests of a particular nature god. These clerics might hunt the evil monstrosities that despoil the woodlands, bless the harvest of the faithful, or wither the crops of those who anger their gods.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 1, "acolyte-of-nature", "nature-bonus-proficiency");
		registerFeature("acolyte-of-nature", "Acolyte of Nature",
				"At 1st level, you learn one cantrip of your choice from the druid spell list. You also gain proficiency in one of the following skills of your choice: Animal Handling, Nature, or Survival.");
		registerFeature("bonus-proficiency-nature", "Bonus Proficiency",
				"At 1st level, you gain proficiency with heavy armor.");
		
		createLevel(subclass, 2, "channel-divinity-charm-animals-and-plants");
		registerFeature("channel-divinity-charm-animals-and-plants", "Channel Divinity: Charm Animals and Plants",
				"Starting at 2nd level, you can use your Channel Divinity to charm animals and plants.\r\n"
				+ "\r\n"
				+ "As an action, you present your holy symbol and invoke the name of your deity. Each beast or plant creature that can see you within 30 feet of you must make a Wisdom saving throw. If the creature fails its saving throw, it is charmed by you for 1 minute or until it takes damage. While it is charmed by you, it is friendly to you and other creatures you designate.");
		
		createLevel(subclass, 6, "dampen-elements");
		registerFeature("dampen-elements", "Dampen Elements",
				"Starting at 6th level, when you or a creature within 30 feet of you takes acid, cold, fire, lightning, or thunder damage, you can use your reaction to grant resistance to the creature against that instance of the damage.");
		
		createLevel(subclass, 8, "nature-divine-strike");
		registerFeature("nature-divine-strike", "Divine Strike",
				"At 8th level, you gain the ability to infuse your weapon strikes with divine energy. Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra 1d8 cold, fire, or lightning damage (your choice) to the target. When you reach 14th level, the extra damage increases to 2d8.");
		
		createLevel(subclass, 17, "master-of-nature");
		registerFeature("master-of-nature", "Master of Nature",
				"At 17th level, you gain the ability to command animals and plant creatures. While creatures are charmed by your Charm Animals and Plants feature, you can take a bonus action on your turn to verbally command what each of those creatures will do on its next turn.");
		
		return subclass;
	}
	
	private static DndSubclass createTempest() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "tempest";
		subclass.name = "Tempest";
		subclass.flavor = "Divine Domain";
		subclass.classId = "cleric";
		subclass.description = StringUtil.convertDesc("Gods whose portfolios include the Tempest domain � including Talos, Umberlee, Kord, Zeboim, the Devourer, Zeus, and Thor � govern storms, sea, and, sky. They include gods of lightning and thunder, gods of earthquakes, some fire gods, and certain gods of violence, physical strength, and courage. In some pantheons, a god of this domain rules over other deities and is known for swift justice delivered by thunderbolts. In the pantheons of seafaring people, gods of this domain are ocean deities and the patrons of sailors. Tempest gods send their clerics to inspire fear in the common folk, either to keep those folk on the path of righteousness or to encourage them to offer sacrifices of propitiation to ward off divine wrath.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 1, "tempest-bonus-proficiencies", "wrath-of-the-storm");
		registerFeature("tempest-bonus-proficiencies", "Bonus Proficiencies",
				"At 1st level, you gain proficiency with martial weapons and heavy armor.");
		registerFeature("wrath-of-the-storm", "Wrath of the Storm",
				"Also at 1st level, you can thunderously rebuke attackers. When a creature within 5 feet of you that you can see hits you with an attack, you can use your reaction to cause the creature to make a Dexterity saving throw. The creature takes 2d8 lightning or thunder damage (your choice) on a failed saving throw, and half as much damage on a successful one.\r\n"
				+ "\r\n"
				+ "You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.");
		
		createLevel(subclass, 2, "channel-divinity-destructive-wrath");
		registerFeature("channel-divinity-destructive-wrath", "Channel Divinity: Destructive Wrath",
				"Starting at 2nd level, you can use your Channel Divinity to wield the power of the storm with unchecked ferocity.\r\n"
				+ "\r\n"
				+ "When you roll lightning or thunder damage, you can use your Channel Divinity to deal maximum damage, instead of rolling.");
		
		createLevel(subclass, 6, "thunderous-strike");
		registerFeature("thunderous-strike", "Thunderous Strike",
				"At 6th level, when you deal lightning damage to a Large or smaller creature, you can also push it up to 10 feet away from you.");
		
		createLevel(subclass, 8, "tempest-divine-strike");
		registerFeature("tempest-divine-strike", "Divine Strike",
				"At 8th level, you gain the ability to infuse your weapon strikes with divine energy. Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra 1d8 thunder damage to the target. When you reach 14th level, the extra damage increases to 2d8.");
		
		createLevel(subclass, 17, "stormborn");
		registerFeature("stormborn", "Stormborn",
				"At 17th level, you have a flying speed equal to your current walking speed whenever you are not underground or indoors.");
		
		return subclass;
	}
	
	private static DndSubclass createTrickery() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "trickery";
		subclass.name = "Trickery";
		subclass.flavor = "Divine Domain";
		subclass.classId = "cleric";
		subclass.description = StringUtil.convertDesc("Gods of trickery � such as Tymora, Beshaba, Olidammara, the Traveler, Garl Glittergold, and Loki � are mischief-makers and instigators who stand as a constant challenge to the accepted order among both gods and mortals. They're patrons of thieves, scoundrels, gamblers, rebels, and liberators. Their clerics are a disruptive force in the world, puncturing pride, mocking tyrants, stealing from the rich, freeing captives, and flouting hollow traditions. They prefer subterfuge, pranks, deception, and theft rather than direct confrontation.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 1, "blessing-of-the-trickster");
		registerFeature("blessing-of-the-trickster", "Blessing of the Trickster",
				"Starting when you choose this domain at 1st level, you can use your action to touch a willing creature other than yourself to give it advantage on Dexterity (Stealth) checks. This blessing lasts for 1 hour or until you use this feature again.");
		
		createLevel(subclass, 2, "channel-divinity-invoke-duplicity");
		registerFeature("channel-divinity-invoke-duplicity", "Channel Divinity: Invoke Duplicity",
				"Starting at 2nd level, you can use your Channel Divinity to create an illusory duplicate of yourself.\r\n"
				+ "\r\n"
				+ "As an action, you create a perfect illusion of yourself that lasts for 1 minute, or until you lose your concentration (as if you were concentrating on a spell). The illusion appears in an unoccupied space that you can see within 30 feet of you. As a bonus action on your turn, you can move the illusion up to 30 feet to a space you can see, but it must remain within 120 feet of you.\r\n"
				+ "\r\n"
				+ "For the duration, you can cast spells as though you were in the illusion's space, but you must use your own senses. Additionally, when both you and your illusion are within 5 feet of a creature that can see the illusion, you have advantage on attack rolls against that creature, given how distracting the illusion is to the target.");
		
		createLevel(subclass, 6, "channel-divinity-cloak-of-shadows");
		registerFeature("channel-divinity-cloak-of-shadows", "Channel Divinity: Cloak of Shadows",
				"Starting at 6th level, you can use your Channel Divinity to vanish.\r\n"
				+ "\r\n"
				+ "As an action, you become invisible until the end of your next turn. You become visible if you attack or cast a spell.");
		
		createLevel(subclass, 8, "trickery-divine-strike");
		registerFeature("trickery-divine-strike", "Divine Strike",
				"At 8th level, you gain the ability to infuse your weapon strikes with poison � a gift from your deity. Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra 1d8 poison damage to the target. When you reach 14th level, the extra damage increases to 2d8.");
		
		createLevel(subclass, 17, "improved-duplicity");
		registerFeature("improved-duplicity", "Improved Duplicity",
				"At 17th level, you can create up to four duplicates of yourself, instead of one, when you use Invoke Duplicity. As a bonus action on your turn, you can move any number of them up to 30 feet, to a maximum range of 120 feet.");
		
		return subclass;
	}
	
	private static DndSubclass createWar() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "war";
		subclass.name = "War";
		subclass.flavor = "Divine Domain";
		subclass.classId = "cleric";
		subclass.description = StringUtil.convertDesc("War has many manifestations. It can make heroes of ordinary people. It can be desperate and horrific, with acts of cruelty and cowardice eclipsing instances of excellence and courage. In either case, the gods of war watch over warriors and reward them for their great deeds. The clerics of such gods excel in battle, inspiring others to fight the good fight or offering acts of violence as prayers. Gods of war include champions of honor and chivalry (such as Torm, Heironeous, and Kiri-Jolith) as well as gods of destruction and pillage (such as Erythnul, the Fury, Gruumsh, and Ares) and gods of conquest and domination (such as Bane, Hextor, and Maglubiyet). Other war gods (such as Tempus, Nike, and Nuada) take a more neutral stance, promoting war in all its manifestations and supporting warriors in any circumstance.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 1, "war-bonus-proficiency", "war-priest");
		registerFeature("war-bonus-proficiency", "Bonus Proficiency",
				"At 1st level, you gain proficiency with martial weapons and heavy armor.");
		registerFeature("war-priest", "War Priest",
				"You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.");
		
		createLevel(subclass, 2, "channel-divinity-guided-strike");
		registerFeature("channel-divinity-guided-strike", "Channel Divinity: Guided Strike",
				"From 1st level, your god delivers bolts of inspiration to you while you are engaged in battle. When you use the Attack action, you can make one weapon attack as a bonus action.\r\n"
				+ "\r\n"
				+ "You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.");
		
		createLevel(subclass, 6, "channel-divinity-war-gods-blessing");
		registerFeature("channel-divinity-war-gods-blessing", "Channel Divinity: War God's Blessing",
				"At 6th level, when a creature within 30 feet of you makes an attack roll, you can use your reaction to grant that creature a +10 bonus to the roll, using your Channel Divinity. You make this choice after you see the roll, but before the DM says whether the attack hits or misses.");

		createLevel(subclass, 8, "war-divine-strike");
		registerFeature("war-divine-strike", "Divine Strike",
				"At 8th level, you gain the ability to infuse your weapon strikes with divine energy. Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra 1d8 damage of the same type dealt by the weapon to the target. When you reach 14th level, the extra damage increases to 2d8.");
		
		createLevel(subclass, 17, "avatar-of-battle");
		registerFeature("avatar-of-battle", "Avatar of Battle",
				"At 17th level, you gain resistance to bludgeoning, piercing, and slashing damage from nonmagical weapons.");
		
		return subclass;
	}
	
	private static DndSubclass createLand() {
		Dnd5eSubclass model = data5e.DndSubclass.get("land");
		DndSubclass subclass = createSubclass(model);
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null)
				subclass.levels.set(x, createLevel(model.levels.get(x)));
		}
		
		subclass.levels.get(1).features.set(0, "land-bonus-cantrip");
		Feature feature = SourceRegistry.getItem("bonus-cantrip", Feature.class);
		if(feature != null) {
			feature.id = "land-bonus-cantrip";
			book.unregister("bonus-cantrip", Feature.class);
			book.register(feature);
		}
		
		return subclass;
	}
	
	private static DndSubclass createMoon() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "moon";
		subclass.name = "Moon";
		subclass.flavor = "Druid Circle";
		subclass.classId = "druid";
		subclass.description = StringUtil.convertDesc(
				"Druids of the Circle of the Moon are fierce guardians of the wilds. Their order gathers under the full moon to share news and trade warnings. They haunt the deepest parts of the wilderness, where they might go for weeks on end before crossing paths with another humanoid creature, let alone another druid.\r\n"
				+ "\r\n"
				+ "Changeable as the moon, a druid of this circle might prowl as a great cat one night, soar over the treetops as an eagle the next day, and crash through the undergrowth in bear form to drive off a trespassing monster. The wild is in the druid's blood.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 2, "combat-wild-shape", "circle-forms");
		registerFeature("combat-wild-shape", "Combat Wild Shape",
				"When you choose this circle at 2nd level, you gain the ability to use Wild Shape on your turn as a bonus action, rather than as an action.\r\n"
				+ "\r\n"
				+ "Additionally, while you are transformed by Wild Shape, you can use a bonus action to expend one spell slot to regain 1d8 hit points per level of the spell slot expended.");
		
		registerFeature("circle-forms", "Circle Forms",
				"The rites of your circle grant you the ability to transform into more dangerous animal forms. Starting at 2nd level, you can use your Wild Shape to transform into a beast with a challenge rating as high as 1. You ignore the Max. CR column of the Beast Shapes table, but must abide by the other limitations there.\r\n"
				+ "\r\n"
				+ "Starting at 6th level, you can transform into a beast with a challenge rating as high as your druid level divided by 3, rounded down.");
		
		createLevel(subclass, 6, "primal-strike");
		registerFeature("primal-strike", "Primal Strike",
				"Starting at 6th level, your attacks in beast form count as magical for the purpose of overcoming resistance and immunity to nonmagical attacks and damage.");
		
		createLevel(subclass, 10, "elemental-wild-shape");
		registerFeature("elemental-wild-shape", "Elemental Wild Shape",
				"At 10th level, you can expend two uses of Wild Shape at the same time to transform into an air elemental, an earth elemental, a fire elemental, or a water elemental.");
		
		createLevel(subclass, 14, "thousand-forms");
		registerFeature("thousand-forms", "Thousand Forms",
				"By 14th level, you have learned to use magic to alter your physical form in more subtle ways. You can cast the Alter Self spell at will.");
		
		return subclass;
	}
	
	private static DndSubclass createChampion() {
		Dnd5eSubclass model = data5e.DndSubclass.get("champion");
		DndSubclass subclass = createSubclass(model);
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null)
				subclass.levels.set(x, createLevel(model.levels.get(x)));
		}
		
		return subclass;
	}
	
	private static DndSubclass createBattleMaster() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "battle-master";
		subclass.name = "Battle Master";
		subclass.flavor = "Martial Archetype";
		subclass.classId = "fighter";
		subclass.description = StringUtil.convertDesc(
				"Those who emulate the archetypal Battle Master employ martial techniques passed down through generations. To a Battle Master, combat is an academic field, sometimes including subjects beyond battle such as weaponsmithing and calligraphy. Not every fighter absorbs the lessons of history, theory, and artistry that are reflected in the Battle Master archetype, but those who do are well-rounded fighters of great skill and knowledge.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 3, "combat-superiority", "student-of-war"); //TODO detail maneuvers somewhere else
		registerFeature("combat-superiority", "Combat Superiority",
				"When you choose this archetype at 3rd level, you learn maneuvers that are fueled by special dice called superiority dice.\r\n"
				+ "\r\n"
				+ "Maneuvers. You learn three maneuvers of your choice. Many maneuvers enhance an attack in some way. You can use only one maneuver per attack. You learn two additional maneuvers of your choice at 7th, 10th, and 15th level. Each time you learn new maneuvers, you can also replace one maneuver you know with a different one.\r\n"
				+ "\r\n"
				+ "Superiority Dice. You have four superiority dice, which are d8s. A superiority die is expended when you use it. You regain all of your expended superiority dice when you finish a short or long rest. You gain another superiority die at 7th level and one more at 15th level.\r\n"
				+ "\r\n"
				+ "Saving Throws. Some of your maneuvers require your target to make a saving throw to resist the maneuver's effects. The saving throw DC is calculated as follows:\r\n"
				+ "\r\n"
				+ "Maneuver save DC = 8 + your proficiency bonus + your Strength or Dexterity modifier (your choice)");
		registerFeature("student-of-war", "Student of War",
				"At 3rd level, you gain proficiency with one type of artisan's tools of your choice.");
		
		createLevel(subclass, 7, "know-your-enemy");
		registerFeature("know-your-enemy", "Know Your Enemy",
				"Starting at 7th level, if you spend at least 1 minute observing or interacting with another creature outside combat, you can learn certain information about its capabilities compared to your own. The DM tells you if the creature is your equal, superior, or inferior in regard to two of the following characteristics of your choice:\r\n"
				+ "\r\n"
				+ "    Strength score\r\n"
				+ "\r\n"
				+ "    Dexterity score\r\n"
				+ "\r\n"
				+ "    Constitution score\r\n"
				+ "\r\n"
				+ "    Armor Class\r\n"
				+ "\r\n"
				+ "    Current hit points\r\n"
				+ "\r\n"
				+ "    Total class levels, if any\r\n"
				+ "\r\n"
				+ "    Fighter class levels, if any\r\n"
				+ "");
		
		createLevel(subclass, 10, "improved-combat-superiority");
		registerFeature("improved-combat-superiority", "Improved Combat Superiority",
				"At 10th level, your superiority dice turn into d10s. At 18th level, they turn into d12s.");
		
		createLevel(subclass, 15, "relentless");
		registerFeature("relentless", "Relentless",
				"Starting at 15th level, when you roll initiative and have no superiority dice remaining, you regain 1 superiority die.");
		return subclass;
	}
	
	private static DndSubclass createEldritchKnight() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "eldritch-knight";
		subclass.name = "Eldritch Knight";
		subclass.flavor = "Martial Archetype";
		subclass.classId = "fighter";
		subclass.description = StringUtil.convertDesc(
				"The archetypal Eldritch Knight combines the martial mastery common to all fighters with a careful study of magic. Eldritch Knights use magical techniques similar to those practiced by wizards. They focus their study on two of the eight schools of magic: abjuration and evocation. Abjuration spells grant an Eldritch Knight additional protection in battle, and evocation spells deal damage to many foes at once, extending the fighter's reach in combat. These knights learn a comparatively small number of spells, committing them to memory instead of keeping them in a spellbook.");
		//TODO add magic somehow eventually
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		createLevel(subclass, 3, "weapon-bond");
		registerFeature("weapon-bond", "Weapon Bond",
				"At 3rd level, you learn a ritual that creates a magical bond between yourself and one weapon. You perform the ritual over the course of 1 hour, which can be done during a short rest. The weapon must be within your reach throughout the ritual, at the conclusion of which you touch the weapon and forge the bond.\r\n"
				+ "\r\n"
				+ "Once you have bonded a weapon to yourself, you can't be disarmed of that weapon unless you are incapacitated. If it is on the same plane of existence, you can summon that weapon as a bonus action on your turn, causing it to teleport instantly to your hand.\r\n"
				+ "\r\n"
				+ "You can have up to two bonded weapons, but can summon only one at a time with your bonus action. If you attempt to bond with a third weapon, you must break the bond with one of the other two.");
		
		createLevel(subclass, 7, "war-magic");
		registerFeature("war-magic", "War Magic",
				"Beginning at 7th level, when you use your action to cast a cantrip, you can make one weapon attack as a bonus action.");
		
		createLevel(subclass, 10, "eldritch-strike");
		registerFeature("eldritch-strike", "Eldritch Strike",
				"At 10th level, you learn how to make your weapon strikes undercut a creature's resistance to your spells. When you hit a creature with a weapon attack, that creature has disadvantage on the next saving throw it makes against a spell you cast before the end of your next turn.");
		
		createLevel(subclass, 15, "arcane-charge");
		registerFeature("arcane-charge", "Arcane Charge",
				"At 15th level, you gain the ability to teleport up to 30 feet to an unoccupied space you can see when you use your Action Surge. You can teleport before or after the additional action.");
		
		createLevel(subclass, 18, "improved-war-magic");
		registerFeature("improved-war-magic", "Improved War Magic",
				"Starting at 18th level, when you use your action to cast a spell, you can make one weapon attack as a bonus action.");
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		return subclass;
	}
	
	private static DndSubclass createOpenHand() {
		Dnd5eSubclass model = data5e.DndSubclass.get("open-hand");
		DndSubclass subclass = createSubclass(model);
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null)
				subclass.levels.set(x, createLevel(model.levels.get(x)));
		}
		
		return subclass;
	}
	
	private static DndSubclass createShadow() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "shadow";
		subclass.name = "Shadow";
		subclass.flavor = "Monastic Tradition";
		subclass.classId = "monk";
		subclass.description = StringUtil.convertDesc(
				"Monks of the Way of Shadow follow a tradition that values stealth and subterfuge. These monks might be called ninjas or shadowdancers, and they serve as spies and assassins. Sometimes the members of a ninja monastery are family members, forming a clan sworn to secrecy about their arts and missions. Other monasteries are more like thieves' guilds, hiring out their services to nobles, rich merchants, or anyone else who can pay their fees. Regardless of their methods, the heads of these monasteries expect the unquestioning obedience of their students.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 3, "shadow-arts");
		registerFeature("shadow-arts", "Shadow Arts",
				"Starting when you choose this tradition at 3rd level, you can use your ki to duplicate the effects of certain spells. As an action, you can spend 2 ki points to cast Darkness, Darkvision, Pass without Trace, or Silence, without providing material components. Additionally, you gain the Minor Illusion cantrip if you don't already know it.");
		
		createLevel(subclass, 6, "shadow-step");
		registerFeature("shadow-step", "Shadow Step",
				"At 6th level, you gain the ability to step from one shadow into another. When you are in dim light or darkness, as a bonus action you can teleport up to 60 feet to an unoccupied space you can see that is also in dim light or darkness. You then have advantage on the first melee attack you make before the end of the turn.");
		
		createLevel(subclass, 11, "cloak-of-shadows");
		registerFeature("cloak-of-shadows", "Cloak of Shadows",
				"By 11th level, you have learned to become one with the shadows. When you are in an area of dim light or darkness, you can use your action to become invisible. You remain invisible until you make an attack, cast a spell, or are in an area of bright light.");
		
		createLevel(subclass, 17, "opportunist");
		registerFeature("opportunist", "Opportunist",
				"At 17th level, you can exploit a creature's momentary distraction when it is hit by an attack. Whenever a creature within 5 feet of you is hit by an attack made by a creature other than you, you can use your reaction to make a melee attack against that creature.");
		
		return subclass;
	}
	
	private static DndSubclass createFourElements() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "four-elements";
		subclass.name = "Four Elements";
		subclass.flavor = " Monastic Tradition";
		subclass.classId = "monk";
		subclass.description = StringUtil.convertDesc(
				"You follow a monastic tradition that teaches you to harness the elements. When you focus your ki, you can align yourself with the forces of creation and bend the four elements to your will, using them as an extension of your body. Some members of this tradition dedicate themselves to a single element, but others weave the elements together.\r\n"
				+ "\r\n"
				+ "Many monks of this tradition tattoo their bodies with representations of their ki powers, commonly imagined as coiling dragons, but also as phoenixes, fish, plants, mountains, and cresting waves.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 3, "disciple-of-the-elements");
		registerFeature("disciple-of-the-elements", "Disciple of the Elements",
				"When you choose this tradition at 3rd level, you learn magical disciplines that harness the power of the four elements. A discipline requires you to spend ki points each time you use it.\r\n"
				+ "\r\n"
				+ "You know the Elemental Attunement discipline and one other elemental discipline of your choice. You learn one additional elemental discipline of your choice at 6th, 11th, and 17th level.\r\n"
				+ "\r\n"
				+ "Whenever you learn a new elemental discipline, you can also replace one elemental discipline that you already know with a different discipline.");
		//TODO: there are disciplines. add them later and suffer
		return subclass;
	}
	
	//TODO paladins have tenets
	//probably include in desc or smth idk
	//also include divinity somehow
	private static DndSubclass createDevotion() {
		Dnd5eSubclass model = data5e.DndSubclass.get("devotion");
		DndSubclass subclass = createSubclass(model);
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null)
				subclass.levels.set(x, createLevel(model.levels.get(x)));
		}
		
		return subclass;
	}
	
	private static DndSubclass createAncients() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "ancients-";
		subclass.name = "Ancients";
		subclass.flavor = "Sacred Oath";
		subclass.classId = "paladin";
		subclass.description = StringUtil.convertDesc(
				"The Oath of the Ancients is as old as the race of elves and the rituals of the druids. Sometimes called fey knights, green knights, or horned knights, paladins who swear this oath cast their lot with the side of the light in the cosmic struggle against darkness because they love the beautiful and life-giving things of the world, not necessarily because they believe in principles of honor, courage, and justice. They adorn their armor and clothing with images of growing things-leaves, antlers, or flowers-to reflect their commitment to preserving life and light in the world.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 3, "channel-divinity");
		
		createLevel(subclass, 7, "aura-of-warding");
		registerFeature("aura-of-warding", "Aura of Warding",
				"Beginning at 7th level, ancient magic lies so heavily upon you that it forms an eldritch ward. You and friendly creatures within 10 feet of you have resistance to damage from spells.\n"
				+ "\n"
				+ "At 18th level, the range of this aura increases to 30 feet.");
		
		createLevel(subclass, 15, "undying-sentinel");
		registerFeature("undying-sentinel", "Undying Sentinel",
				"Starting at 15th level, when you are reduced to 0 hit points and are not killed outright, you can choose to drop to 1 hit point instead. Once you use this ability, you can't use it again until you finish a long rest.\n"
				+ "\n"
				+ "Additionally, you suffer none of the drawbacks of old age, and you can't be aged magically.");
		
		createLevel(subclass, 20, "elder-champion");
		registerFeature("elder-champion", "Elder Champion",
				"At 20th level, you can assume the form of an ancient force of nature, taking on an appearance you choose. For example, your skin might turn green or take on a bark-like texture, your hair might become leafy or moss-like, or you might sprout antlers or a lion-like mane.\n"
				+ "\n"
				+ "Using your action, you undergo a transformation. For 1 minute, you gain the following benefits:\n"
				+ "\n"
				+ "    At the start of each of your turns, you regain 10 hit points.\n"
				+ "\n"
				+ "    Whenever you cast a paladin spell that has a casting time of 1 action, you can cast it using a bonus action instead.\n"
				+ "\n"
				+ "    Enemy creatures within 10 feet of you have disadvantage on saving throws against your paladin spells and Channel Divinity options.\n"
				+ "\n"
				+ "Once you use this feature, you can't use it again until you finish a long rest.");
		
		return subclass;
	}
	
	private static DndSubclass createVengeance() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "vengeance";
		subclass.name = "Vengeance";
		subclass.flavor = "Sacred Oath";
		subclass.classId = "paladin";
		subclass.description = StringUtil.convertDesc(
				"The Oath of Vengeance is a solemn commitment to punish those who have committed a grievous sin. When evil forces slaughter helpless villagers, when an entire people turns against the will of the gods, when a thieves' guild grows too violent and powerful, when a dragon rampages through the countryside – at times like these, paladins arise and swear an Oath of Vengeance to set right that which has gone wrong. To these paladins – sometimes called avengers or dark knights – their own purity is not as important as delivering justice.");
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		createLevel(subclass, 3, "channel-divinity"); // TODO custom stuffs here
		
		createLevel(subclass, 7, "relentless-avenger");
		registerFeature("relentless-avenger", "Relentless Avenger",
				"By 7th level, your supernatural focus helps you close off a foe's retreat. When you hit a creature with an opportunity attack, you can move up to half your speed immediately after the attack and as part of the same reaction. This movement doesn't provoke opportunity attacks.");
		
		createLevel(subclass, 15, "soul-of-vengeance");
		registerFeature("soul-of-vengeance", "Soul of Vengeance",
				"Starting at 15th level, the authority with which you speak your Vow of Enmity gives you greater power over your foe. When a creature under the effect of your Vow of Enmity makes an attack, you can use your reaction to make a melee weapon attack against that creature if it is within range.");
		
		createLevel(subclass, 20, "avenging-angel");
		registerFeature("avenging-angel", "Avenging Angel",
				"At 20th level, you can assume the form of an angelic avenger. Using your action, you undergo a transformation. For 1 hour, you gain the following benefits:\n"
				+ "\n"
				+ "    Wings sprout from your back and grant you a flying speed of 60 feet.\n"
				+ "\n"
				+ "    You emanate an aura of menace in a 30-foot radius. The first time any enemy creature enters the aura or starts its turn there during a battle, the creature must succeed on a Wisdom saving throw or become frightened of you for 1 minute or until it takes any damage. Attack rolls against the frightened creature have advantage.\n"
				+ "\n"
				+ "Once you use this feature, you can't use it again until you finish a long rest.");
		
		return subclass;
	}
	
	/*
	template
	
	private static DndSubclass create() {
		DndSubclass subclass = new DndSubclass();
		subclass.id = "";
		subclass.name = "";
		subclass.flavor = "";
		subclass.classId = "";
		subclass.description = StringUtil.convertDesc();
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		
		_classId = subclass.classId;
		_subclassId = subclass.id;
		
		return subclass;
	}
	 */
	
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
		
		book.register(createLife());
		book.register(createKnowledge());
		book.register(createLight());
		book.register(createNature());
		book.register(createTempest());
		book.register(createTrickery());
		book.register(createWar());
		
		book.register(createLand());
		book.register(createMoon());
		
		book.register(createChampion());
		book.register(createBattleMaster());
		book.register(createEldritchKnight());
		
		book.register(createOpenHand());
		book.register(createShadow());
		book.register(createFourElements());
		
		book.register(createDevotion());
		book.register(createAncients());
		book.register(createVengeance());
		SourceRegistry.saveBooks();
	}
}
