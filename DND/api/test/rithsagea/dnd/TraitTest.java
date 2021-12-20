package test.rithsagea.dnd;

import com.rithsagea.dnd.race.elf.DarkvisionTrait;
import com.rithsagea.dnd.race.elf.FeyAncestryTrait;
import com.rithsagea.dnd.race.elf.KeenSensesTrait;
import com.rithsagea.dnd.race.elf.TranceTrait;
import com.rithsagea.dnd.traits.SuperiorDarkvisionTrait;

import api.rithsagea.dnd.types.Registry;

public class TraitTest {
	public static void registerTraits(Registry registry) {
		registry.registerTrait(new SuperiorDarkvisionTrait());
		registry.registerTrait(new DarkvisionTrait());
		registry.registerTrait(new TranceTrait());
		registry.registerTrait(new FeyAncestryTrait());
		registry.registerTrait(new KeenSensesTrait());
	}
	
	public static void main(String[] args) {
		Registry registry = Registry.getInstance();
		registerTraits(registry);
	}
}
