package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.DndSubrace;
import com.rithsagea.dnd.api.types.Trait;
import com.rithsagea.dnd.api5e.Datapack;

public class SubraceBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	private static SourceBook book;
	
	private static DndSubrace createHillDwarf() {
		DndSubrace subrace = new DndSubrace();
		subrace.id = "hill-dwarf";
		subrace.name = "Hill Dwarf";
		subrace.bonuses = Map.of("wis", 1);
		subrace.description = StringUtil.convertDesc(
				"As a hill dwarf, you have keen senses, deep intuition, and remarkable resilience. The gold dwarves of Faerûn in their mighty southern kingdom are hill dwarves, as are the exiled Neidar and the debased Klar of Krynn in the Dragonlance setting.");
		subrace.traits = new ArrayList<>(Arrays.asList("dwarven-toughness"));
		
		return subrace;
	}
	
	private static DndSubrace createMountainDwarf() {
		DndSubrace subrace = new DndSubrace();
		
		subrace.id = "mountain-dwarf";
		subrace.name = "Mountain Dwarf";
		subrace.bonuses = Map.of("str", 2);
		subrace.description = StringUtil.convertDesc(
				"As a mountain dwarf, you’re strong and hardy, accustomed to a difficult life in rugged terrain. You’re probably on the tall side (for a dwarf), and tend toward lighter coloration. The shield dwarves of northern Faerûn, as well as the ruling Hylar clan and the noble Daewar clan of Dragonlance, are mountain dwarves.");
		subrace.traits = List.of("dwarven-armor-training");
		Trait trait = new Trait();
		trait.id = "dwarven-armor-training";
		trait.name = "Dwarven Armor Training";
		trait.description = "You have proficiency with light and medium armor.";
		book.register(trait);
		
		return subrace;
	}
	
	private static DndSubrace createHighElf() {
		DndSubrace subrace = new DndSubrace();
		
		subrace.id = "high-elf";
		subrace.name = "High Elf";
		subrace.bonuses = Map.of(
				"int", 1);
		subrace.traits = List.of("elf-weapon-training", "high-elf-cantrip", "extra-language");
		subrace.description = StringUtil.convertDesc(
				"As a high elf, you have a keen mind and a mastery of at least the basics of magic. In many of the worlds of D&D, there are two kinds of high elves. One type (which includes the gray elves and valley elves of Greyhawk, the Silvanesti of Dragonlance, and the sun elves of the Forgotten Realms) is haughty and reclusive, believing themselves to be superior to non-elves and even other elves. The other type (including the high elves of Greyhawk, the Qualinesti of Dragonlance, and the moon elves of the Forgotten Realms) are more common and more friendly, and often encountered among humans and other races.\n"
				+ "\n"
				+ "The sun elves of Faerûn (also called gold elves or sunrise elves) have bronze skin and hair of copper, black, or golden blond. Their eyes are golden, silver, or black. Moon elves (also called silver elves or gray elves) are much paler, with alabaster skin sometimes tinged with blue. They often have hair of silver-white, black, or blue, but various shades of blond, brown, and red are not uncommon. Their eyes are blue or green and flecked with gold.");
		
		return subrace;
	}
	
	private static DndSubrace createWoodElf() {
		DndSubrace subrace = new DndSubrace();
		
		subrace.id = "wood-elf";
		subrace.name = "Wood Elf";
		subrace.bonuses = Map.of("wis", 1);
		subrace.description = StringUtil.convertDesc("As a wood elf, you have keen senses and intuition, and your fleet feet carry you quickly and stealthily through your native forests. This category includes the wild elves (grugach) of Greyhawk and the Kagonesti of Dragonlance, as well as the races called wood elves in Greyhawk and the Forgotten Realms. In Faerûn, wood elves (also called wild elves, green elves, or forest elves) are reclusive and distrusting of non-elves.\n"
				+ "\n"
				+ "Wood elves’ skin tends to be copperish in hue, sometimes with traces of green. Their hair tends toward browns and blacks, but it is occasionally blond or copper-colored. Their eyes are green, brown, or hazel.");
		subrace.traits = List.of("elf-weapon-training", "fleet-of-foot", "mask-of-the-wild");
		
		Trait trait = new Trait();
		trait.id = "fleet-of-foot";
		trait.name = "Fleet of Foot";
		trait.description = "Your base walking speed increases to 35 feet.";
		book.register(trait);
		
		trait = new Trait();
		trait.id = "mask-of-wild";
		trait.name = "Mask of Wild";
		trait.description = "You can attempt to hide even when you are only lightly obscured by foliage, heavy rain, falling snow, mist, and other natural phenomena.";
		book.register(trait);
		
		return subrace;
	}
	
	private static DndSubrace createDarkElf() {
		DndSubrace subrace = new DndSubrace();
		subrace.id = "dark-elf";
		subrace.name = "Dark Elf";
		subrace.bonuses = Map.of("cha", 1);
		subrace.description = StringUtil.convertDesc(
				"Descended from an earlier subrace of dark-skinned\n"
				+ "elves, the drow were banished from the surface world\n"
				+ "for following the goddess Lolth down the path to\n"
				+ "evil and corruption. Now they have built their own\n"
				+ "civilization in the depths of the Underdark, patterned\n"
				+ "after the Way of Lolth. Also called dark elves, the drow\n"
				+ "have black skin that resembles polished obsidian and\n"
				+ "stark white or pale yellow hair. They commonly have\n"
				+ "very pale eyes (so pale as to be mistaken for white) in\n"
				+ "shades o f lilac, silver, pink, red, and blue. They tend to\n"
				+ "be smaller and thinner than most elves.");
		
		subrace.traits = List.of("superior-darkvision", "sunlight-sensitivity", "drow-magic", "drow-weapon-training");
		
		Trait trait = new Trait();
		trait.id = "superior-darkvision";
		trait.name = "Superior Darkvisoin";
		trait.description = "Your darkvision has a radius of 120 feet.";
		book.register(trait);
		
		trait = new Trait();
		trait.id = "sunlight-sensitivity";
		trait.name = "Sunlight Sensitivity";
		trait.description = "You have disadvantage on attack rolls and Wisdom (Perception) checks that rely on sight when you, the target of the attack, or whatever you are trying to perceive is in direct sunlight.";
		book.register(trait);
		
		trait = new Trait();
		trait.id = "drow-magic";
		trait.name = "Drow Magic";
		trait.description = "You know the Dancing Lights cantrip. When you reach 3rd level, you can cast the Faerie Fire spell once with this trait and regain the ability to do so when you finish a long rest. When you reach 5th level, you can cast the Darkness spell onceand regain the ability to do so when you finish a long rest. Charisma is your spellcasting ability for these spells.";
		book.register(trait);
		
		trait = new Trait();
		trait.id = "drow-weapon-training";
		trait.name = "Drow Weapon Training";
		trait.description = "You have proficiency with rapiers, shortswords, and hand crossbows.";
		book.register(trait);
		
		return subrace;
	}
	
	private static DndSubrace createLightfootHalfling() {
		DndSubrace subrace = new DndSubrace();
		subrace.id = "lightfoot-halfling";
		subrace.name = "Lightfoot Halfling";
		subrace.description = StringUtil.convertDesc(
				"As a lightfoot halfling, you can easily hide from notice,\n"
				+ "even using other people as cover. You’re inclined to be\n"
				+ "affable and get along well with others. In the Forgotten\n"
				+ "Realms, lightfoot halflings have spread the farthest and\n"
				+ "thus are the most common variety.\n"
				+ "\n"
				+ "Lightfoots are more prone to wanderlust than other\n"
				+ "halflings, and often dwell alongside other races or take\n"
				+ "up a nomadic life. In the world of Greyhawk, these\n"
				+ "halflings are called hairfeet or tallfellows.");
		subrace.bonuses = Map.of("cha", 1);
		subrace.traits = List.of("naturally-stealthy");
		
		return subrace;
	}
	
	private static DndSubrace createStoutHalfling() {
		DndSubrace subrace = new DndSubrace();
		subrace.id = "stout-halfling";
		subrace.name = "Stout Halfling";
		subrace.description = StringUtil.convertDesc(
				"As a stout halfling, you’re hardier than average and have\n"
				+ "some resistance to poison. Some say that stouts have\n"
				+ "dwarven blood. In the Forgotten Realms, these halflings\n"
				+ "are called stronghearts, and they’re most common\n"
				+ "in the south.");
		subrace.bonuses = Map.of("con", 1);
		subrace.traits = List.of("stout-resilience");
		
		Trait trait = new Trait();
		trait.id = "stout-resilience";
		trait.name = "Stout Resilience";
		trait.description = "You have advantage on saving throws against poison, and you have resistance against poison damage.";
		
		return subrace;
	}
	
	private static DndSubrace createForestGnome() {
		DndSubrace subrace = new DndSubrace();
		subrace.id = "forest-gnome";
		subrace.name = "Forest Gnome";
		subrace.description = StringUtil.convertDesc(
				"As a forest gnome, you have a natural knack for illusion\n"
				+ "and inherent quickness and stealth. In the worlds of\n"
				+ "D&D, forest gnomes are rare and secretive. They gather\n"
				+ "in hidden communities in sylvan forests, using illusions\n"
				+ "and trickery to conceal themselves from threats or\n"
				+ "to mask their escape should they be detected. Forest\n"
				+ "gnomes tend to be friendly with other good-spirited\n"
				+ "woodland folk, and they regard elves and good fey as\n"
				+ "their most important allies. These gnomes also befriend\n"
				+ "small forest animals and rely on them for information\n"
				+ "about threats that might prowl their lands.");
		
		subrace.bonuses = Map.of("dex", 1);
		subrace.traits = List.of("natural-illusionist", "speak-with-small-beasts");
		
		Trait trait = new Trait();
		trait.id = "natural-illusionist";
		trait.name = "Natural Illusionist";
		trait.description = "You know the Minor Illusion cantrip. Intelligence is your spellcasting modifier for it.";
		book.register(trait);
		
		trait = new Trait();
		trait.id = "speak-with-small-beasts";
		trait.name = "Speak with Small Beasts";
		trait.description = "Through sound and gestures, you may communicate simple ideas with Small or smaller beasts.";
		book.register(trait);
		
		return subrace;
	}
	
	private static DndSubrace createRockGnome() {
		DndSubrace subrace = new DndSubrace();
		subrace.id = "rock-gnome";
		subrace.name = "Rock Gnome";
		subrace.description = StringUtil.convertDesc("As a rock gnome, you have a natural inventiveness and\n"
				+ "hardiness beyond that of other gnomes. Most gnomes\n"
				+ "in the worlds of D&D are rock gnomes, including the\n"
				+ "tinker gnomes of the Dragonlance setting.");
		subrace.bonuses = Map.of("con", 1);
		subrace.traits = List.of("artificers-lore", "tinker");
		return subrace;
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		book = SourceRegistry.getBooks().get("5e");
		
		System.out.println(data5e.DndSubrace.keySet());
		
		
		book.register(createHillDwarf());
		book.register(createMountainDwarf());
		book.register(createHighElf());
		book.register(createWoodElf());
		book.register(createDarkElf());
		book.register(createLightfootHalfling());
		book.register(createStoutHalfling());
		book.register(createForestGnome());
		book.register(createRockGnome());
		
		SourceRegistry.saveBooks();
	}
}
